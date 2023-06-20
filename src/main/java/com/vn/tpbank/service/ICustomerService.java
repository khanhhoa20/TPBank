package com.vn.tpbank.service;

import java.util.List;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.request.RegisterBankAccountRequest;
import com.vn.tpbank.request.UpdateInformationRequest;

public interface ICustomerService {

    String login(String userName, String userPass);

    String RegisterCreateBankAccount(RegisterBankAccountRequest request);

    String changePassword(String userName, String userPass, String newUserPass);

    List<BankAccount> getAllAccount(Long customerId);

    String deposit(Long accountNumber, Long amount);

    String transfer(Long accountNumber, Long accountNumber2, Long amount);

    String withdraw(Long accountNumber, Long amount);

    String updateInformation(UpdateInformationRequest request);
}
