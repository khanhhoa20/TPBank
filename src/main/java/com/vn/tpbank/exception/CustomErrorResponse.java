package com.vn.tpbank.exception;

/**
 * @author Khánh Hòa
 *
 */
public class CustomErrorResponse {
	private int status;
	private String error;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
