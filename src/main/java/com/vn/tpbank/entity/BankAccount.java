package com.vn.tpbank.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_ACCOUNT_SEQ")
    @SequenceGenerator(name = "BANK_ACCOUNT_SEQ", sequenceName = "BANK_ACCOUNT_SEQ",allocationSize = 1)
	private Long bankAccountId;

	@Column(name = "balance")
	private Long balance;

	@Column(name = "bank_name")
	private String bankName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cus_id", referencedColumnName = "id")
	private Customer customer;

	@OneToMany(targetEntity = Transaction.class, mappedBy = "bankAccount", cascade = CascadeType.ALL)
	private List<Transaction> listTransactions;

	public BankAccount() {

	}

	public BankAccount(Long bankAccountId, Long balance, String bankName, Customer customer,
			List<Transaction> listTransactions) {
		super();
		this.bankAccountId = bankAccountId;
		this.balance = balance;
		this.bankName = bankName;
		this.customer = customer;
		this.listTransactions = listTransactions;
	}

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getListTransactions() {
		return listTransactions;
	}

	public void setListTransactions(List<Transaction> listTransactions) {
		this.listTransactions = listTransactions;
	}

}
