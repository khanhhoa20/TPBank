package com.vn.tpbank.service;

import java.util.List;
import java.util.ArrayList;
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

	
	/**
	 * @author Khánh Hòa
	 */
	@Override
	public String login(String username, String pass) {
		User user = userRepository.findByUserNameAndUserPass(username, pass);
		if (user != null) {
			return "Login successfully. Role: " + user.getRole();
		}
		return "Wrong username or password!";
	}

	/**
	 * @author Khánh Hòa
	 */
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
			transaction.setTransactionType("deposit");
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
	public List<Transaction> viewTransactions(String transactionType) {
		List<Transaction> transactions = new ArrayList<>();
		transactions = transactionRepository.findByTransactionType(transactionType);
		return transactions;
	}

	@Override
	public String deleteCustomer(long cusId) {
		Customer customer = customerRepository.findByCustomerId(cusId);

		String check = null;
		if (customer == null) {
			check = "Customer Doesn't Exits";

		} else {
			BankAccount account = bankAccountRepository.findByCustomer(customer);
			bankAccountRepository.deleteById(account.getBankAccountId());
			check = "Delete Success";

			customerRepository.deleteById(cusId);
		}
		return check;
	}

	@Override
	public String updateCustomer(String phone, String name, String address, String pass) {
		Customer customer2 = customerRepository.findByCustomerPhone(phone);
		if (customer2 == null) {
			return "Customer Doesn't Exits";
		} else {
			BankAccount account = bankAccountRepository.findByCustomer(customer2);
			if (account.getLockStatus().equals("inactive")) {
				return "Sorry your Account has been Locked, You can't update anything";
			} else {
				customer2.setCustomerPhone(phone);
				customer2.setCustomerName(name);
				customer2.setCustomerAddress(address);
				customer2.getUser().setUserPass(pass);
				customerRepository.save(customer2);
				return "Update Succesful";
			}

		}

	}


}
