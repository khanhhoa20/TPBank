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

	public String updateCustomer(String phone, String name, String address, String pass);
	
	public String withdrawMoney(Transaction transaction);
	public String depositMoney(Transaction transaction);
	public List<Transaction> viewTransactions (String transistonType);
	public String deleteCustomer(long cusId);
}
