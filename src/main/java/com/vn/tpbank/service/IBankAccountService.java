package com.vn.tpbank.service;

import java.util.List;

import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Transaction;

public interface IBankAccountService{
	public String createAccount(Long bankAccountId, Long balance, String bankName, String lockStatus, Customer customer,
			List<Transaction> listTransactions);
}
