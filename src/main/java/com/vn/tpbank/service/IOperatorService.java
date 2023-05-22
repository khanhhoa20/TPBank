package com.vn.tpbank.service;

import java.util.List;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;


public interface IOperatorService {
	public String login(String username, String pass);

	public String unlockBankAccount(String cusPhone);

	public String lockBankAccount(String cusPhone);

	public String createBankAccount(BankAccount account);
	
	public List<Customer> viewCustomers();
	
	public boolean updateCustomer(Customer customer);

}
