package com.vn.tpbank.service;


import java.util.List;

import java.util.Date;


import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Transaction;


public interface IOperatorService {
	public String login(String username, String pass);

	public String unlockBankAccount(String cusPhone);

	public String lockBankAccount(String cusPhone);

	public String createBankAccount(BankAccount account);
	
	public List<BankAccount> viewCustomers();

	
	public boolean updateCustomer(String email, String address,String phone);

	String depositMoney(Transaction transaction);

	String withdrawMoney(Transaction transaction);

	boolean updateCustomer(Customer customer);

	Customer viewCustomer(String customerPhone);

}
