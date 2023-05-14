package com.vn.tpbank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.UserRepository;
import com.vn.tpbank.service.IUserService;

@RestController
@RequestMapping("tpbank/operator")
public class UserController {

	@Autowired
	IUserService iUserService;
	


	@GetMapping("test-get-user")
	
	public Optional<User> getUserTest() {
		return iUserService.getUserForTest();

	}
}
