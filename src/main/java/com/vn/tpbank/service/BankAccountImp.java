package com.vn.tpbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.repository.BankAccountRepository;
@Service
public class BankAccountImp implements IBankAccount {
@Autowired
BankAccountRepository accountRepository;
	@Override
	public List<BankAccount> getAllBankAccount() {
		return accountRepository.findAll();
	}
	@Override
	public BankAccount findById() {
		// TODO Auto-generated method stub
		return null;
	}

}
