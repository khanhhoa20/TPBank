package com.vn.tpbank.service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Transaction;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.TransactionRepository;
import com.vn.tpbank.repository.UserRepository;

@Service
public class OperatorServiceImpl implements IOperatorService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public String login(String username, String pass) {
		User user = userRepository.findByUserNameAndUserPass(username, pass);
		if (user != null) {
			return "Login successfully. Role: " + user.getRole();
		}
		return "Wrong username or password!";
	}

	@Override
	public String unlockBankAccount(String cusPhone) {
		Customer cus = customerRepository.findByCustomerPhone(cusPhone);
		if (cus != null) {
			BankAccount bankAccount = bankAccountRepository.findByCustomer(cus);
			if (bankAccount.getLockStatus().equals("active")) {
				return "This bank account is currently active. No need to unlock.";
			} else {
				bankAccount.setLockStatus("active");
				bankAccountRepository.save(bankAccount);
				return "Unlock successfully.";
			}
		} else {
			return "Phone is not in the system";
		}

	}

	@Override
	public String lockBankAccount(String cusPhone) {
		String check = null;
		Customer customer = customerRepository.findByCustomerPhone(cusPhone);
		if (customer != null) {
			BankAccount account = bankAccountRepository.findByCustomer(customer);
			if (account.getLockStatus().equalsIgnoreCase("inactive")) {
				check = "Account has Locked Before";
			} else {
				account.setLockStatus("inactive");
				bankAccountRepository.save(account);
				if (account.getLockStatus().equals("inactive")) {
					check = "Account is now Locked";
				}
			}
		} else {
			check = "Can't find the Customer";
		}

		return check;
	}

	@Override
	public String createBankAccount(BankAccount account) {
		Customer customer = null;
		customer = account.getCustomer();
		customer = customerRepository.findByCustomerEmailAndCustomerNationalIdAndCustomerPhone(
				customer.getCustomerEmail(), customer.getCustomerNationalId(), customer.getCustomerPhone());
		Optional<User> user = userRepository.findByUserName(account.getCustomer().getUser().getUserName());
		String check = null;
		if (customer == null && user.isEmpty()) {
			bankAccountRepository.save(account);
			check = "Bank Account Create Susscess";
		} else {
			check = "Bank Account Already Exits or Some Detail Not Right ";
		}

		return check;

	}

	@Override
	public List<BankAccount> viewCustomers() {
		return bankAccountRepository.findAll();
	}

	public boolean updateCustomer(String email, String address, String phone) {
		Customer cus = null;
		cus = customerRepository.findByCustomerPhone(phone);
		Customer customer = customerRepository.findByCustomerEmail(email);
		if (cus == null || customer != null) {
			return false;
		} else {
			BankAccount account = null;
			account = bankAccountRepository.findByCustomer(cus);
			if (account != null) {
				if (account.getLockStatus().equalsIgnoreCase("Inactive")) {
					return false;
				} else {

					cus.setCustomerAddress(address);
					cus.setCustomerEmail(email);
					cus = customerRepository.save(cus);
					return true;

				}
			} else {
				return false;
			}
		}
	}

	@Override
	public String depositMoney(Transaction transaction) {
		BankAccount account = bankAccountRepository.findByCustomer(
				customerRepository.findByCustomerPhone(transaction.getBankAccount().getCustomer().getCustomerPhone()));
		if (account == null)
			return "Account not found.";
		if (account.getLockStatus().equalsIgnoreCase("inactive"))
			return "Your bank account is locked (INACTIVE).";
		if (account != null && !account.getLockStatus().equalsIgnoreCase("inactive")) {
			transaction.setTransactionType("Deposit");
			transaction.setTransactionDate(new Date());
			transaction.setBeforeTransaction(account.getBalance());
			account.setBalance((account.getBalance() + transaction.getTransactionAmount()));
			bankAccountRepository.save(account);
			transaction.setAfterTransaction(account.getBalance());
			transaction.setBankAccount(account);
			transactionRepository.save(transaction);
			return "Transaction has been made successfully.";
		}

		return "Account is not available.";
	}

	@Override
	public String withdrawMoney(Transaction transaction) {
		if (transaction.getTransactionAmount() <= 0)
			return "Invalid input amount.";
		BankAccount account = bankAccountRepository.findByCustomer(
				customerRepository.findByCustomerPhone(transaction.getBankAccount().getCustomer().getCustomerPhone()));
		if (account == null)
			return "Account not found.";
		if (account.getLockStatus().equalsIgnoreCase("inactive"))
			return "Your bank account is locked (INACTIVE).";
		if (account != null && !account.getLockStatus().equalsIgnoreCase("inactive")) {
			if (account.getBalance() < 50000 || (account.getBalance() - transaction.getTransactionAmount()) < 50000)
				return "Balance is not enough for this transaction.";
			transaction.setTransactionType("withdraw");
			transaction.setTransactionDate(new Date());
			transaction.setBeforeTransaction(account.getBalance());
			long tempbalance = account.getBalance() - transaction.getTransactionAmount();
			account.setBalance(tempbalance);
			bankAccountRepository.save(account);
			transaction.setAfterTransaction(account.getBalance());
			transaction.setBankAccount(account);
			transactionRepository.save(transaction);
			return "Transaction has been made successfully.";
		}
		return "Account is not available.";
	}

	@Override
	public List<Transaction> findTransactionByPhone(String phoneNumber) {
		List<Transaction> transactions = transactionRepository.findAll();
		for (Transaction transaction : transactions) {
			if (!transaction.getBankAccount().getCustomer().getCustomerPhone().equals(phoneNumber))
				transactions.remove(transaction);
		}
		return transactions;
	}

	@Override
	public String transferMoney(Transaction sendTransaction, Transaction recieveTransaction) {
		if (sendTransaction.getTransactionAmount() <= 0)
			return "Invalid input amount.";
		BankAccount senderAccount = bankAccountRepository.findByCustomer(customerRepository
				.findByCustomerPhone(sendTransaction.getBankAccount().getCustomer().getCustomerPhone()));
		BankAccount recieverAccount = bankAccountRepository.findByCustomer(customerRepository
				.findByCustomerPhone(recieveTransaction.getBankAccount().getCustomer().getCustomerPhone()));
		if (senderAccount == null)
			return "Sender account not found.";
		if(recieverAccount==null)
			return "Reciever account not found.";
		if (recieverAccount.getLockStatus().equalsIgnoreCase("inactive"))
			return "Reciever bank account is locked (INACTIVE).";
		if (senderAccount.getLockStatus().equalsIgnoreCase("inactive"))
			return "Your bank account is locked (INACTIVE).";
		if (senderAccount != null && !senderAccount.getLockStatus().equalsIgnoreCase("inactive")) {
			if (senderAccount.getBalance() < 50000
					|| (senderAccount.getBalance() - sendTransaction.getTransactionAmount()) < 50000)
				return "Sender's balance is not enough for this transaction.";
			
			sendTransaction.setTransactionType("send"+recieverAccount.getCustomer().getCustomerPhone());
			sendTransaction.setTransactionDate(new Date());
			sendTransaction.setBeforeTransaction(senderAccount.getBalance());
			long tempbalance = senderAccount.getBalance() - sendTransaction.getTransactionAmount();
			senderAccount.setBalance(tempbalance);

			recieveTransaction.setTransactionType("recieve"+senderAccount.getCustomer().getCustomerPhone());
			recieveTransaction.setTransactionDate(new Date());
			recieveTransaction.setBeforeTransaction(recieverAccount.getBalance());
			long temprecievebalance = recieverAccount.getBalance()+recieveTransaction.getTransactionAmount();
			recieverAccount.setBalance(temprecievebalance);
			
			bankAccountRepository.save(senderAccount);
			bankAccountRepository.save(recieverAccount);
			sendTransaction.setAfterTransaction(senderAccount.getBalance());
			sendTransaction.setBankAccount(senderAccount);
			recieveTransaction.setAfterTransaction(recieverAccount.getBalance());
			recieveTransaction.setBankAccount(recieverAccount);
			transactionRepository.save(sendTransaction);
			transactionRepository.save(recieveTransaction);
			return "Transaction has been made successfully.";
		}
		return "Account is not available.";
	}

	@Override
	public BankAccount findBankAccountThroughTransaction(Transaction transaction) {
		String type=transaction.getTransactionType();
		if(type.contains("send")) {
			String num=type.substring(4);
			return bankAccountRepository.findByCustomer(customerRepository.findByCustomerPhone(num));
		} else if(type.contains("recieve")) {
			String num=type.substring(7);
			return bankAccountRepository.findByCustomer(customerRepository.findByCustomerPhone(num));
		}
		return null;
	}
	

}
