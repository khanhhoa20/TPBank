package com.vn.tpbank.service;

import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;

public interface IManagerService {
	public String login(String username, String password);
	public void createOperator(User user, Operator operator);
	public void editOperator(Operator operator);
}
