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
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	OperatorRepository operatorRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Override
	public Optional<User> getUserForTest() {
		return	userRepository.findById(1l);
	}
	@Override
	public List<Operator> getAll() {
		// TODO Auto-generated method stub
		return operatorRepository.findAll();
	}
	@Override
	public List<Department> getAllDe() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

}
