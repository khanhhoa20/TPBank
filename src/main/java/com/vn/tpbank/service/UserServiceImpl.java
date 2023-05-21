package com.vn.tpbank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<User> getUserForTest() {
		return	userRepository.findById(1l);
	}
}
