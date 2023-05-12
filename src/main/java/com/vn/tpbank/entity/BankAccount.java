package com.vn.tpbank.entity;

public class BankAccount {
	private Long bankAccountId;
	private Long balance;
	private String bankName;
	private Long cusId;

	public BankAccount() {

	}

	public BankAccount(Long bankAccountId, Long balance, String bankName, Long cusId) {

		this.bankAccountId = bankAccountId;
		this.balance = balance;
		this.bankName = bankName;
		this.cusId = cusId;
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

	public Long getCusId() {
		return cusId;
	}

	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}

}
