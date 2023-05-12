package com.vn.tpbank.entity;

import java.util.Date;

public class Transaction {
	private Long transactionId;
	private String transactionType;
	private double transactionAmount;
	private Date transactionDate;
	private double beforeTransaction;
	private double afterTransaction;
	private Long bankAccountId;

	public Transaction() {

	}

	public Transaction(Long transactionId, String transactionType, double transactionAmount, Date transactionDate,
			double beforeTransaction, double afterTransaction, Long bankAccountId) {

		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.beforeTransaction = beforeTransaction;
		this.afterTransaction = afterTransaction;
		this.bankAccountId = bankAccountId;
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

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

}
