package com.vn.tpbank.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "transaction_bank")
public class Transaction {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_BANK_SEQ")
	@SequenceGenerator(name = "TRANSACTION_BANK_SEQ", sequenceName = "TRANSACTION_BANK_SEQ", allocationSize = 1)
	private Long transactionId;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "transaction_amount")
	private double transactionAmount;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "before_transaction")
	private double beforeTransaction;

	@Column(name = "after_transaction")
	private double afterTransaction;

	@ManyToOne(targetEntity = BankAccount.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_account_id", referencedColumnName = "id")
	private BankAccount bankAccount;

	public Transaction() {

	}

	public Transaction(Long transactionId, String transactionType, double transactionAmount, Date transactionDate,
			double beforeTransaction, double afterTransaction, BankAccount bankAccount) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.beforeTransaction = beforeTransaction;
		this.afterTransaction = afterTransaction;
		this.bankAccount = bankAccount;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getBeforeTransaction() {
		return beforeTransaction;
	}

	public void setBeforeTransaction(double beforeTransaction) {
		this.beforeTransaction = beforeTransaction;
	}

	public double getAfterTransaction() {
		return afterTransaction;
	}

	public void setAfterTransaction(double afterTransaction) {
		this.afterTransaction = afterTransaction;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

}
