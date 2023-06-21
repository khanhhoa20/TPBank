package com.vn.tpbank.service;

import java.util.List;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.request.RegisterBankAccountRequest;
import com.vn.tpbank.request.UpdateInformationRequest;

public interface ICustomerService{
	
	public String login(String username, String password);
	public Customer getCustomer(Long CustomerId);
	
	public BankAccount getBankAccount(Long customerId);
	
	public String editCustomer(String cusPhone, String cusEmail, String cusAddress, String userName);

	
	public User getUser(Long UserId);
	

    /**
     * @author: Cong thanh
     */
    // String login(String userName, String userPass);

    String RegisterCreateBankAccount(RegisterBankAccountRequest request);

    String changePassword(String userName, String userPass, String newUserPass);

    List<BankAccount> getAllAccount(Long customerId);

    String deposit(Long accountNumber, Long amount);

    String transfer(Long accountNumber, Long accountNumber2, Long amount);

    String withdraw(Long accountNumber, Long amount);

    String updateInformation(UpdateInformationRequest request);
}
