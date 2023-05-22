package com.vn.tpbank.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.Transaction;

public interface IManagerService {
	public String login(String username, String password);
	public String createOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentName);
	public String editOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId);
	public String disableOperator(String usernam);
	public List<Operator> listAllOperator();
	public String createAccount(Long balance, String bankName, String lockStatus, Customer customer);
	public boolean deleteAccount(Long id);
}
