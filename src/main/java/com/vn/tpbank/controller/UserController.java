package com.vn.tpbank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.UserRepository;
import com.vn.tpbank.service.IUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("tpbank/user")
public class UserController {

	@Autowired
	IUserService iUserService;
	@Autowired
	UserRepository userRepo;

	@GetMapping("test-get-user")

	public Optional<User> getUserTest() {
		return iUserService.getUserForTest();

	}
	@GetMapping("getAll")

	public List<Operator> getAll() {
		return iUserService.getAllOpers();

	}
	
	@GetMapping("/{username}")
	public Optional<User> getUserByUsername(@PathVariable String username) {
		return userRepo.findByUserName(username);
	}
}
