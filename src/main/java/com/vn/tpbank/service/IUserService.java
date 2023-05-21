package com.vn.tpbank.service;

import java.util.List;
import java.util.Optional;

import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;


public interface IUserService {
		public Optional<User> getUserForTest();
		public List<Operator> getAll();
		public List<Department> getAllDe();
}
