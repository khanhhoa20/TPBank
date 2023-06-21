package com.vn.tpbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.TransactionRepository;
import com.vn.tpbank.repository.UserRepository;
import com.vn.tpbank.request.RegisterBankAccountRequest;
import com.vn.tpbank.request.UpdateInformationRequest;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * @author Cong Thanh
	 */
	// @Override
	// public String login(String username, String pass) {
	//     User user = userRepository.findByUserNameAndUserPass(username, pass);
	//     if (user != null) {
	//         return "Login successfully. Role: " + user.getRole();
	//     }
	//     return "Wrong username or password!";
	// }

	/**
	 * @author Cong Thanh
	 */
	@Override
	public String RegisterCreateBankAccount(RegisterBankAccountRequest request) {
		//        Customer customer = customerRepository.findById(request.getCustomerId()).get();
		//        if (customer == null) {
		//            return "Customer not found";
		//        }
		//
		//        BankAccount account = new BankAccount();
		//        account.setBankAccountId(0l);
		//        account.setBankName(request.getBankName());
		//        account.setBalance(0l);
		//        account.setLockStatus("active");
		//        account.setCustomer(customer);
		//        bankAccountRepository.save(account);
		return "Create bank account successfully";
	}

	/**
	 * Login function
	 *@author Thieu Sy Manh
	 */
	@Override
	public String login(String username, String password) {

		User user = userRepository.findByUserNameAndUserPass(username, password);
		if(user!=null && user.getRole().equalsIgnoreCase("customer")) {
			return "customer";
		}
		//		return new LoginResponse("Login failed", null);
		return "Login failed";

	}

	
	/**
	 * show information of customer
	 * @author Thieu Sy Manh
	 */
	@Override
	public Customer getCustomer(Long CustomerId) {
		// TODO Auto-generated method stub
		if (customerRepository.findByCustomerId(CustomerId)==null) {
			return null;
		}
		return customerRepository.findByCustomerId(CustomerId);
	}

	/**
	 * get bank account information
	 * @author Thieu Sy Manh
	 */
	@Override
	public BankAccount getBankAccount(Long customerId) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (bankAccountRepository.findByCustomer(customer)==null) {
			return null;
		}
		return bankAccountRepository.findByCustomer(customer);
	}

	/**
	 * get user account information
	 * @author Thieu Sy Manh
	 */
	@Override
	public Optional<User> getUser(Long UserId) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(UserId);
		if (user.isEmpty()) {
			return null;
		}
		return user;
	}

	/**
	 * edit customer information
	 * @author Thieu Sy Manh
	 */
	@Override
	public String editCustomer(String cusPhone, String cusEmail, String cusAddress, String userName) {
		Optional<User> user = userRepository.findByUserName(userName);
		Customer customer = customerRepository.findByUser(user.get());

		if (user.isPresent() && customer != null ){
			customer.setCustomerPhone(cusPhone);
			customer.setCustomerEmail(cusEmail);
			customer.setCustomerAddress(cusAddress);
//						user.setUserPass(userPass);
//						customer.setUser(user);
			customerRepository.save(customer);

			return "Update successfully !!!";
		}
		else {
			return "Update failed !!!";
		}
	}

	/**
	 * edit password of user account
	 * @author Thieu Sy Manh
	 */
	@Override
	public String editPass(Long userID, String userPass) {
		Optional<User> user = userRepository.findById(userID);

		if (user.isPresent()){
			User userget = user.get();
			
			userget.setUserPass(userPass);
			userRepository.save(userget);

			return "Change successfully !!!";
		}
		else {
			return "Change failed !!!";
		}
	}

	/**
	 * edit password of user account
	 * @author Cong Thanh
	 */
	@Override
	public String changePassword(String userName, String userPass, String newUserPass) {
		User user = userRepository.findByUserNameAndUserPass(userName, userPass);
		if (user == null) {
			return "Wrong username or password!";
		}
		user.setUserPass(newUserPass);
		userRepository.save(user);
		return "Change password successfully.";
	}

	/**
	 * get list ALl bankAccount
	 * @author Cong Thanh
	 */
	@Override
	public List<BankAccount> getAllAccount(Long customerId) {
		return bankAccountRepository.findByCustomerId(customerId);
	}

	/**
	 * @author Cong Thanh
	 */
	@Override
	public String deposit(Long accountNumber, Long amount) {
		BankAccount account = bankAccountRepository.findById(accountNumber).get();
		if (account == null) {
			return "Account not found";
		}

		account.setBalance(account.getBalance() + amount);
		bankAccountRepository.save(account);
		return "Deposit successfully";
	}

	/**
	 * @author Cong Thanh
	 */
	@Override
	public String transfer(Long accountNumber, Long accountNumber2, Long amount) {
		BankAccount account = bankAccountRepository.findById(accountNumber).get();
		if (account == null) {
			return "Account not found";
		}
		BankAccount account2 = bankAccountRepository.findById(accountNumber2).get();
		if (account2 == null) {
			return "Account not found";
		}
		if (account.getBalance() < amount) {
			return "Not enough money";
		}
		account.setBalance(account.getBalance() - amount);
		account2.setBalance(account2.getBalance() + amount);
		bankAccountRepository.save(account);
		return "Transfer successfully";

	}

	/**
	 * @author Cong Thanh
	 */
	@Override
	public String withdraw(Long accountNumber, Long amount) {
		BankAccount account = bankAccountRepository.findById(accountNumber).get();
		if (account == null) {
			return "Account not found";
		}
		if (account.getBalance() < amount) {
			return "Not enough money";
		}
		account.setBalance(account.getBalance() - amount);
		bankAccountRepository.save(account);
		return "Withdraw successfully";
	}

	/**
	 * @author Cong Thanh
	 */
	@Override
	public String updateInformation(UpdateInformationRequest request) {
		//        BankAccount account = bankAccountRepository.findById(request.getBankAccountId()).get();
		//        if (account == null) {
		//            return "Account not found";
		//        }
		//        account.setLockStatus(request.getLockStatus());
		//
		//        Customer customer = customerRepository.findById(account.getCustomer().getCustomerId()).get();
		//        if (customer == null) {
		//            return "Customer not found";
		//        }
		//        customer.setCustomerName(request.getCustomerName());
		//        customer.setCustomerAddress(request.getCustomerAddress());
		//        customer.setCustomerPhone(request.getCustomerPhone());
		//        customer.setCustomerEmail(request.getCustomerEmail());
		//        customer.setCustomerDob(request.getCustomerDob());
		//
		//        bankAccountRepository.save(account);
		//        customerRepository.save(customer);

		return "Update information successfully";
	}

}