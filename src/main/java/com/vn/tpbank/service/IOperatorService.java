package com.vn.tpbank.service;

public interface IOperatorService {
	public String login(String username, String pass);

	public String unlockBankAccount(String cusPhone);
}
