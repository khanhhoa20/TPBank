package com.vn.tpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IOperatorService;

@RestController
@RequestMapping("/tpbank/operator")
public class OperatorController {
	@Autowired
	IOperatorService iOperatorService;

	/**
	 * @URL http://localhoat:9090/tpbank/operator/login
	 * @param user
	 * @return message login success or not
	 * @author khánh hòa
	 */
	@PostMapping("login")
	public String login(@RequestBody User user) {
		System.out.println("user "+user.getUserName());
		return iOperatorService.login(user.getUserName(),user.getUserPass());
		
	}
}
