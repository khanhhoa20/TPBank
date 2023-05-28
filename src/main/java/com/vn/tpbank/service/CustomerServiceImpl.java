package com.vn.tpbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.UserRepository;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CustomerRepository customerRepository;
//	
//	@Autowired
//	BankAccountRepository bankAccountRepository;

	@Override
	public String login(String username, String password) {
		
		User user = userRepository.findByUserNameAndUserPass(username, password);
		if(user!=null && user.getRole().equalsIgnoreCase("customer")) {
			return "Hello"+user.getUserName();
		}
		// TODO Auto-generated method stub
		return "Login failed";
	}

	@Override
	public BankAccount getBankAccount(Long bankAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(Long CustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Long UserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createAccount(Long balance, String bankName, String lockStatus, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
