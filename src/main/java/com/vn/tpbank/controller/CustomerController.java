package com.vn.tpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.UserRepository;
import com.vn.tpbank.service.ICustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("tpbank/customer")
public class CustomerController {
	@Autowired
	ICustomerService iCustomerService;
	
	CustomerRepository customerRepository;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return iCustomerService.login(user.getUserName(), user.getUserPass());
		
	}
}
