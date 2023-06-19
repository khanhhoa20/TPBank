package com.vn.tpbank.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MANAGER_SEQ")
	@SequenceGenerator(name = "MANAGER_SEQ", sequenceName = "MANAGER_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long managerID;

	@Column(name = "man_phone")
	private String managerPhone;

	@Column(name = "man_address")
	private String managerAddress;

	@Column(name = "man_email")
	private String managerEmail;

	@Column(name = "man_name")
	private String managerName;
	
	@Column(name="manager_status")
	private String managerStatus;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	public Manager() {

	}

	public Manager(Long managerID, String managerPhone, String managerAddress, String managerEmail, String managerName, String managerStatus,
			User user, Department department) {
		super();
		this.managerID = managerID;
		this.managerPhone = managerPhone;
		this.managerAddress = managerAddress;
		this.managerEmail = managerEmail;
		this.managerName = managerName;
		this.user = user;
		this.department = department;
		this.managerStatus = managerStatus;
	}

	public Long getManagerID() {
		return managerID;
	}

	public void setManagerID(Long managerID) {
		this.managerID = managerID;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getManagerAddress() {
		return managerAddress;
	}

	public void setManagerAddress(String managerAddress) {
		this.managerAddress = managerAddress;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}

}
