package com.vn.tpbank.response;

import com.vn.tpbank.entity.User;

public class LoginResponse {
	private String data;
	private User user;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LoginResponse(String data, User user) {
		super();
		this.data = data;
		this.user = user;
	}
	
	
}
