package com.vn.tpbank.service;

import java.util.List;

import com.vn.tpbank.entity.BankAccount;

public interface IBankAccount {
public List<BankAccount> getAllBankAccount();
public BankAccount findById();
}
