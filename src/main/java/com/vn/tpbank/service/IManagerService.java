package com.vn.tpbank.service;

import java.util.List;

import com.vn.tpbank.entity.Operator;

public interface IManagerService {
	public String login(String username, String password);
	public String createOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentName);
	public String editOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId);
	public String disableOperator(String usernam);
	public List<Operator> listAllOperator();
}
