package com.vn.tpbank.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name="customer")
public class Customer {
	@Id
	@Column(name="id")
	private Long customerId;
	
	@Column(name="cus_name")
	private String customerName;
	
	@Column(name="cus_address")
	private String customerAddress;
	
	@Column(name="cus_phone")
	private String customerPhone;
	
	@Column(name="cus_email")
	private String customerEmail;
	
	@Column(name="cus_national_id")
	private Long customerNationalId;
	
	@Column(name="cus_date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date customerDob;
	
	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;

	public Customer() {

	}

	public Customer(Long customerId, String customerName, String customerAddress, String customerPhone,
			String customerEmail, Long customerNationalId, Date customerDob) {

		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerNationalId = customerNationalId;
		this.customerDob = customerDob;
	}

	public Customer(Long customerId, String customerName, String customerAddress, String customerPhone,
			String customerEmail, Long customerNationalId, Date customerDob, User user) {

		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerNationalId = customerNationalId;
		this.customerDob = customerDob;
		this.user = user;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Long getCustomerNationalId() {
		return customerNationalId;
	}

	public void setCustomerNationalId(Long customerNationalId) {
		this.customerNationalId = customerNationalId;
	}

	public Date getCustomerDob() {
		return customerDob;
	}

	public void setCustomerDob(Date customerDob) {
		this.customerDob = customerDob;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
