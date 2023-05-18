package com.vn.tpbank.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "operator")
public class Operator {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATOR_SEQ")
	@SequenceGenerator(name = "OPERATOR_SEQ", sequenceName = "OPERATOR_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long operatorID;

	@Column(name = "oper_phone")
	private String operPhone;

	@Column(name = "oper_address")
	private String operAddress;

	@Column(name = "oper_name")
	private String operName;
	
	@Column(name="operator_status")
	private String operatorStatus;
	
	@Column(name="oper_email")
	private String email;
	

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(targetEntity = Department.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	public Operator() {

	}

	public Operator(Long operatorID, String operPhone, String operAddress, String email, String operName, User user, String operatorStatus,
			Department department) {
		super();
		this.operatorID = operatorID;
		this.operPhone = operPhone;
		this.operAddress = operAddress;
		this.email = email;
		this.operName = operName;
		this.user = user;
		this.department = department;
		this.operatorStatus = operatorStatus;
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

	public String getOperatorStatus() {
		return operatorStatus;
	}

	public void setOperatorStatus(String operatorStatus) {
		this.operatorStatus = operatorStatus;
	}

}
