package com.vn.tpbank.service;

import java.util.Date;

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
			if (account.getLockStatus().equals("Locked")) {
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
		customer = customerRepository.findByCustomerPhoneOrCustomerEmailOrCustomerNationalId(
				account.getCustomer().getCustomerPhone(), account.getCustomer().getCustomerEmail(),
				account.getCustomer().getCustomerNationalId());
		User user = null;
		user = userRepository.findByUserName(account.getCustomer().getUser().getUserName());
		String check = null;
		if (customer == null && user == null) {
			account.setLockStatus("Active");
			bankAccountRepository.save(account);
			check = "Bank Account Create Susscess";
		} else {
			check = "Bank Account Already Exits";
		}

		return check;

	}
	
	@Override
	public Customer viewCustomer(String customerPhone)
	{
		return customerRepository.findByCustomerPhone(customerPhone);
	}
	
	@Override 
	public boolean updateCustomer(Customer customer)
	{
		Customer cus = null;
		cus = customerRepository.findByCustomerPhone(customer.getCustomerPhone());
		if(cus == null)
		{
			return false;
		}
		else
		{
			BankAccount account = null;
			account = bankAccountRepository.findByCustomer(cus);
			if(account!=null)
			{
				if(account.getLockStatus().equalsIgnoreCase("Locked"))
				{
					return false;
				}
				else
				{
					Customer newCus = cus;
					newCus.setCustomerAddress(customer.getCustomerAddress());
					newCus.setCustomerEmail(customer.getCustomerEmail());
					newCus.setCustomerDob(customer.getCustomerDob());
					customerRepository.save(newCus);
					return true;
				}
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	public boolean depositMoney(String cusPhone, long amount) {
		BankAccount account = bankAccountRepository.findByCustomer(customerRepository.findByCustomerPhone(cusPhone));
		if(account!=null && !account.getLockStatus().equalsIgnoreCase("inactive")) {
			Transaction transaction = new Transaction();
			transaction.setTransactionType("Deposit");
			transaction.setTransactionDate(new Date());
			transaction.setTransactionAmount(amount);
			transaction.setBeforeTransaction(account.getBalance());
			account.setBalance(account.getBalance()+amount);
			bankAccountRepository.save(account);
			transaction.setAfterTransaction(account.getBalance());
			transaction.setBankAccount(account);
			transactionRepository.save(transaction);
			return true;
		}
		return false;
	}

	@Override
	public boolean withdrawMoney(String cusPhone, long amount) {
		BankAccount account = bankAccountRepository.findByCustomer(customerRepository.findByCustomerPhone(cusPhone));
		if(account!=null && !account.getLockStatus().equalsIgnoreCase("inactive")) {
			if(account.getBalance()<50000 || account.getBalance()<50000)
				return false;
			Transaction transaction = new Transaction();
			transaction.setTransactionType("Deposit");
			transaction.setTransactionDate(new Date());
			transaction.setTransactionAmount(amount);
			transaction.setBeforeTransaction(account.getBalance());
			account.setBalance(account.getBalance()-amount);
			bankAccountRepository.save(account);
			transaction.setAfterTransaction(account.getBalance());
			transaction.setBankAccount(account);
			transactionRepository.save(transaction);
			return true;
		}
		return false;
	}


}
