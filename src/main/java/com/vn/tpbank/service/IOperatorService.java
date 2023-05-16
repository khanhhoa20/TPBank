package com.vn.tpbank.service;

import com.vn.tpbank.entity.BankAccount;

public interface IOperatorService {
	public String login(String username, String pass);

	public String unlockBankAccount(String cusPhone);
	public String lockBankAccount(String cusPhone);
	public String createBankAccount(BankAccount account);
}
