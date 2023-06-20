package com.vn.tpbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.UserRepository;
import com.vn.tpbank.response.LoginResponse;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public LoginResponse login(String username, String password) {

		User user = userRepository.findByUserNameAndUserPass(username, password);
		if(user!=null && user.getRole().equalsIgnoreCase("customer")) {
			return new LoginResponse("", user);
		}
		return new LoginResponse("Login failed", null);
	
	}


	@Override
	public Customer getCustomer(Long CustomerId) {
		// TODO Auto-generated method stub
		if (customerRepository.findByCustomerId(CustomerId)==null) {
			return null;
		}
		return customerRepository.findByCustomerId(CustomerId);
	}

	@Override
	public BankAccount getBankAccount(Long customerId) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (bankAccountRepository.findByCustomer(customer)==null) {
			return null;
		}
		return bankAccountRepository.findByCustomer(customer);
	}
	
	@Override
	public String editCustomer(String cusPhone, String cusEmail, String cusAddress, String userName) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserName(userName);
		Customer customer = customerRepository.findByUser(user);

		if (user!=null && customer != null ){
			customer.setCustomerPhone(cusPhone);
			customer.setCustomerEmail(cusEmail);
			customer.setCustomerAddress(cusAddress);
			//			user.setUserPass(userPass);
			//			customer.setUser(user);
			customerRepository.save(customer);

			return "Update successfully !!!";
		}
		else {
			return "Update failed !!!";
		}
	}

	@Override
	public User getUser(Long UserId) {
		// TODO Auto-generated method stub
		return null;
	}





}
