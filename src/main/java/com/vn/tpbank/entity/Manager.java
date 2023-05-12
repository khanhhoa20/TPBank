package com.vn.tpbank.entity;

public class Manager {
	private Long managerID;
	private String managerPhone;
	private String managerAddress;
	private String managerEmail;
	private String managerName;
	private Long userId;
	private Long departmentId;

	public Manager() {

	}

	public Manager(Long managerID, String managerPhone, String managerAddress, String managerEmail, String managerName,
			Long userId, Long departmentId) {

		this.managerID = managerID;
		this.managerPhone = managerPhone;
		this.managerAddress = managerAddress;
		this.managerEmail = managerEmail;
		this.managerName = managerName;
		this.userId = userId;
		this.departmentId = departmentId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
