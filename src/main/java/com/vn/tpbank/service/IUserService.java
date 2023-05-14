package com.vn.tpbank.service;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.User;


public interface IUserService {
		public Optional<User> getUserForTest();
}
