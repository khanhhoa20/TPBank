package com.vn.tpbank.service;


import java.util.List;

import java.util.Date;


import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Transaction;


public interface IOperatorService {
	/**
	 * @author Khánh Hòa
	 */
	public String login(String username, String pass);

	/**
	 * @author Khánh Hòa
	 */
	public String unlockBankAccount(String cusPhone);

	public String lockBankAccount(String cusPhone);

	public String createBankAccount(BankAccount account);
	
	/**
	 * @author Khánh Hòa
	 */
	public List<BankAccount> viewCustomers();

	public String updateCustomer(String phone, String name, String address, String pass);
	
	public String withdrawMoney(Transaction transaction);
	public String depositMoney(Transaction transaction);
	public List<Transaction> viewTransactions (String transistonType);
	public String deleteCustomer(long cusId);

	/**
	 * @author Khánh Hòa
	 */
	String dangNhap(String username, String pass);

	List<Transaction> findTransactionByPhone(String phoneNumber);

	BankAccount findBankAccountThroughTransaction(Transaction transaction);

	String transferMoney(Transaction sendTransaction, Transaction recieveTransaction);
}
