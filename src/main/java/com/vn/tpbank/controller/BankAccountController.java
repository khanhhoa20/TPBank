package com.vn.tpbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.service.IBankAccountService;
import com.vn.tpbank.entity.*;
import com.vn.tpbank.repository.BankAccountRepository;

@RestController
@RequestMapping("/tpbank/bankAccount")
public class BankAccountController {
	@Autowired
	IBankAccountService iAccountService;
	
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@PostMapping("create")
	public String CreateAccount(@RequestBody BankAccount bankAccount) {
		return iAccountService.createAccount(bankAccount.getBankAccountId(), bankAccount.getBalance(), bankAccount.getBankName(),
				bankAccount.getLockStatus(), bankAccount.getCustomer(), bankAccount.getListTransactions());
	}
	@DeleteMapping("/delete/{id}")
	public boolean deleteUsers(@PathVariable Long id) {
		if (bankAccountRepository.existsById(id)) {
			bankAccountRepository.deleteById(id);
			return true;
		}	
		else 
			return false;
	}
}
