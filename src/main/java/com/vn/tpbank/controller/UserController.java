package com.vn.tpbank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IUserService;

@RestController
@RequestMapping("tpbank/userTest")
public class UserController {

	@Autowired
	IUserService iUserService;
//	@Autowired
//	UserRepository userRepository;

	@GetMapping("test-get-user")

	public Optional<User> getUserTest() {
		return iUserService.getUserForTest();

	}
	
	@GetMapping("getAll")

	public List<Operator> getAllOperators() {
		return iUserService.getAll();

	}
	
	@GetMapping("getAllDe")

	public List<Department> getAllDepartments() {
		return iUserService.getAllDe();

	}

//	@GetMapping("add-user")
//
//	public User addUser() {
//		User u = new User(null, "test3", "123", "operator");
//		return userRepository.save(u);
//
//	}
}
