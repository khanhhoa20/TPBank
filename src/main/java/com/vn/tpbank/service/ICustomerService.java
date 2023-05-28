package com.vn.tpbank.service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;

public interface ICustomerService{
	
	public String login(String username, String password);
	public BankAccount getBankAccount(Long bankAccountId);
	public Customer getCustomer(Long CustomerId);
	public User getUser(Long UserId);
	public String createAccount(Long balance, String bankName, String lockStatus, Customer customer);
	
}
