package com.vn.tpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IOperatorService;

@RestController
@RequestMapping("/tpbank/operator")
public class OperatorController {
	@Autowired
	IOperatorService iOperatorService;

	/**
	 * @URL http://localhost:9090/tpbank/operator/login
	 * @param userName, userPass
	 * @return message login success or not
	 * @author khánh hòa
	 */
	@PostMapping("login")
	public String login(@RequestBody User user) {
		return iOperatorService.login(user.getUserName(),user.getUserPass());
		
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/operator/unlock-bank-account
	 * @param customerPhone
	 * @return message unlock success or not
	 * @author khánh hòa
	 */
	@PostMapping("unlock-bank-account")
	public String login(@RequestBody Customer customer) {
		 return iOperatorService.unlockBankAccount(customer.getCustomerPhone());
	}
	
}
