package com.vn.tpbank.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
    private Long accountNumber;
    private Long accountNumber2;
    private Long amount;
}
