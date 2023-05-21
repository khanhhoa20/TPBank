package com.vn.tpbank.service;

import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vn.tpbank.controller.BankAccountController;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.Transaction;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.entity.BankAccount;
@Service
public class BankAccountServiceImpl implements IBankAccountService{

	BankAccountController bankAccountController;
	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
	    this.bankAccountRepository = bankAccountRepository;
	}
	@Override
	public String createAccount(Long bankAccountId, Long balance, String bankName, String lockStatus, Customer customer,
			List<Transaction> listTransactions) {
		// TODO Auto-generated method stub
		if (bankAccountRepository.findByCustomer(customer)!=null) {
			BankAccount bankAccount = new BankAccount(null, balance, bankName, lockStatus, customer, listTransactions);
			bankAccountRepository.save(bankAccount);
			return "Create account successfully";
		}
		else {
			return "Account is existed";
		}
		
	}
	
}
