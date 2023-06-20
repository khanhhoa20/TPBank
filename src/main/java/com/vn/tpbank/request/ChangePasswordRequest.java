package com.vn.tpbank.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String userName;
    private String userPass;
    private String newUserPass;
}
