package com.vn.tpbank.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInformationRequest {
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private Date customerDob;
    private String lockStatus;
    private Long bankAccountId;
}
