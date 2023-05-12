package com.vn.tpbank.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operator")
public class Operator {
	@Id
	@Column(name = "id")
	private Long operatorID;

	@Column(name = "oper_phone")
	private String operPhone;

	@Column(name = "oper_address")
	private String operAddress;

	@Column(name = "oper_name")
	private String operName;

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private String userId;

	@OneToOne(targetEntity = Department.class, cascade = CascadeType.ALL)
	@JoinColumn(name="department_id", referencedColumnName = "id")
	private Long departmentId;

	public Operator() {

	}

	public Operator(Long operatorID, String operPhone, String operAddress, String operName, String userId,
			Long departmentId) {

		this.operatorID = operatorID;
		this.operPhone = operPhone;
		this.operAddress = operAddress;
		this.operName = operName;
		this.userId = userId;
		this.departmentId = departmentId;
	}

	public Long getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(Long operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperPhone() {
		return operPhone;
	}

	public void setOperPhone(String operPhone) {
		this.operPhone = operPhone;
	}

	public String getOperAddress() {
		return operAddress;
	}

	public void setOperAddress(String operAddress) {
		this.operAddress = operAddress;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
