package com.vn.tpbank.service;

import java.util.List;
import java.util.Optional;

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
		if (userRepository.findByUserName(username).isPresent()) {
			return "Username is already existed";
		}
		else if (operatorRepository.findByEmail(email).isPresent()) {
			return "Email already in use";
		}
		else if (operatorRepository.findByOperPhone(phoneNumber).isPresent()) {
			return "Phone number already in use";
		}
		else {
			User user = new User(null, username, password, "operator");
			if (departmentRepository.findByDepartmentId(departmentId).isEmpty()) {
				return "Department doesn't exist";
			}
			Optional<Department> department = departmentRepository.findByDepartmentId(departmentId);
			userRepository.save(user);
			Operator operator = new Operator(null, phoneNumber, address, email, name, userRepository.findByUserName(username).get(), "active", department.get());
			operatorRepository.save(operator);
			return "Create operator successfully";
		}
	}

	@Override
	public String editOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId) {
		Optional<User> dbUser = userRepository.findByUserName(username);
		Optional<Department> department = departmentRepository.findByDepartmentId(departmentId);
		if (dbUser.isPresent()) {
			User user = dbUser.get();
			Operator operator = operatorRepository.findByUser(user);
			operator.setOperPhone(phoneNumber);
			operator.setOperAddress(address);
			operator.setEmail(email);
			operator.setOperName(name);
			operator.setOperatorStatus(status);
			operator.setDepartment(department.get());
			operator.setUser(user);
			operatorRepository.save(operator);
			return "Edit operator successfully";
		}
		else {
			return "Operator does not exist";
		}
		
	}

	@Override
	public String disableOperator(String username) {
		Optional<User> dbUser = userRepository.findByUserName(username);
		if (dbUser.isPresent()) {
			Operator operator = operatorRepository.findByUser(dbUser.get());
			if (operator.getOperatorStatus().equals("inactive")) {
				return "Operator is already inactive";
			}
			else {
				operator.setOperatorStatus("inactive");
				operatorRepository.save(operator);
				return "Operator is inactive";
			}
		}
		return "User does not exist";
	}
	
	@Override
	public List<Operator> listAllOperator() {
		List<Operator> listOperator = (List<Operator>) operatorRepository.findAll();
		
		return listOperator;
	}

	@Override
	public Department getDepartment(Long departmentId) {
		if (departmentRepository.findByDepartmentId(departmentId).isEmpty()) {
			return null;
		}
		return departmentRepository.findByDepartmentId(departmentId).get();
	}
}
