package com.vn.tpbank.service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.response.LoginResponse;

public interface ICustomerService{
	
	public LoginResponse login(String username, String password);
	public Customer getCustomer(Long CustomerId);
	public BankAccount getBankAccount(Long customerId);
	public String editCustomer(Long customerId, String cusPhone, String cusEmail, String cusAddress, String userPass, String userName);

	
	public User getUser(Long UserId);
	public String createAccount(Long balance, String bankName, String lockStatus, Customer customer);
	
}
