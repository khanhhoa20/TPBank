package com.vn.tpbank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author khanh hoa
 *
 */
@Entity
@Table(name = "\"user\"")
public class User {
	@Id
	@Column(name="id")
	private Long userID;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_pass")
	private String userPass;
	
	@Column(name="role")
	private String role;

	public User() {
		
	}

	public User(Long userID, String userName, String userPass, String role) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPass = userPass;
		this.role = role;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userPass=" + userPass + ", role=" + role + "]";
	}

	

	

}
