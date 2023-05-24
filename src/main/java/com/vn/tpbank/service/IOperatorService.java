package com.vn.tpbank.service;

import java.util.Date;

import com.vn.tpbank.entity.BankAccount;


public interface IOperatorService {
	public String login(String username, String pass);

	public String unlockBankAccount(String cusPhone);

	public String lockBankAccount(String cusPhone);

	public String createBankAccount(BankAccount account);
	
	public String viewCustomer(String customerPhone);
	
	public boolean updateCustomer(String name,Date birth, String address,String phone);

}
