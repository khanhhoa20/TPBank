package com.vn.tpbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.DepartmentRepository;
import com.vn.tpbank.repository.OperatorRepository;
import com.vn.tpbank.repository.UserRepository;

@Service
public class ManagerServiceImpl implements IManagerService {
	@Autowired
	OperatorRepository operatorRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public String login(String username, String password) {
		User user = userRepository.findByUserNameAndUserPass(username, password);
		if (user!=null && user.getRole().equalsIgnoreCase("manager")) {
			return "Login successfully with role manager";
		}
		return "Wrong username or password";
	}

	@Override
	public String createOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId) {
		if (userRepository.findByUserName(username)!=null) {
			User user = new User(null, username, password, "operator");
			Department department = departmentRepository.findByDepartmentId(departmentId);
			userRepository.save(user);
			Operator operator = new Operator(null, phoneNumber, address, email, name, user, "active", department);
			operatorRepository.save(operator);
			return "Create operator successfully";
		}
		else {
			return "Operator is existed";
		}
		
	}

	@Override
	public void editOperator(Operator operator) {
		// TODO Auto-generated method stub
		
	}

}
