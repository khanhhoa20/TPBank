package com.vn.tpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
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
	public ResponseEntity<?>login(@RequestBody User user) {
		return ResponseEntity.ok(iCustomerService.login(user.getUserName(), user.getUserPass()));
	}
	
	@GetMapping("/cusdetail")
	public Customer getCustomer(@RequestBody Customer customer) {
		return iCustomerService.getCustomer(customer.getCustomerId());
		
	}
	
	@GetMapping("/accdetail")
	public BankAccount getBankAccount(@RequestBody  Customer customer) {
		return iCustomerService.getBankAccount(customer.getCustomerId());
		
	}
	
	// @PutMapping("/cusupdate")
	// public String updateNewCustomer(@RequestBody Customer customer )
	// {
	// 	return iCustomerService.editCustomer(customer.getCustomerId(), customer.getCustomerPhone(), customer.getCustomerEmail(), customer.getCustomerAddress(), customer.getUser().getUserPass(), customer.getUser().getUserName());
	// }
}
