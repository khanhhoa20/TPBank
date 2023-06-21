package com.vn.tpbank.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterBankAccountRequest {
	private String bankName;
	private Long customerId;
}
