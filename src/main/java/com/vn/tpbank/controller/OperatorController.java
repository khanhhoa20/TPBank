package com.vn.tpbank.controller;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IOperatorService;

@RestController
@RequestMapping("/tpbank/operator")
public class OperatorController {
	@Autowired
	IOperatorService iOperatorService;

	/**
	 * @URL http://localhost:9090/tpbank/operator/login
	 * @param userName, userPass
	 * @return message login success or not
	 * @author khánh hòa
	 */
	@PostMapping("login")
	public String login(@RequestBody User user) {
		return iOperatorService.login(user.getUserName(), user.getUserPass());

	}

	/**
	 * @URL http://localhost:9090/tpbank/operator/unlock-bank-account
	 * @param customerPhone
	 * @return message unlock success or not
	 * @author khánh hòa
	 */
	@PostMapping("unlock-bank-account")
	public String login(@RequestBody Customer customer) {
		return iOperatorService.unlockBankAccount(customer.getCustomerPhone());
	}

	/**
	 * @URL http://localhost:9090/tpbank/operator/create-bank-account
	 * @param BankAccount property
	 *                    {
	 *                    "balance":5000,
	 *                    "bankName":"TPBank",
	 *                    "lockStatus":"Active",
	 *                    "customer":{
	 *                    "customerName":"abc",
	 *                    "customerAddress":"xyz",
	 *                    "customerPhone":"9152224",
	 *                    "customerEmail":"1224@gmail.com",
	 *                    "customerNationalId":602254,
	 *                    "customerDob": null,
	 *                    "user": {
	 * 
	 *                    "userName":"du43",
	 *                    "userPass":"1234",
	 *                    "role" :"user"
	 *                    }
	 *                    }
	 * 
	 *                    }
	 * @return true?Bank Account Create Susscess:Bank Account Already Exits
	 * @author Dat
	 */
	@PostMapping("create-bank-account")
	public String create(@RequestBody BankAccount account) {
		return iOperatorService.createBankAccount(account);
	}

	/**
	 * @URL http://localhost:9090/tpbank/operator/lock-bank-account
	 * @param customerPhone
	 * @return Account has Locked Before if account is lock before else Account is
	 *         now Locked.
	 * @author Dat
	 */
	@PostMapping("lock-bank-account")
	public String lockBank(@RequestBody Customer cusPhone) {
		return iOperatorService.lockBankAccount(cusPhone.getCustomerPhone());
	}
	
	
	/**
	 * @URL http://localhost:9090/tpbank/operator/view-customer-list
	 * @param 
	 * @return 
	 * @author Khánh Hòa
	 */
	@GetMapping("/view-customer-list")
	public List<Customer> viewListCustomer()
	{
		return iOperatorService.viewCustomers();
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/operator/update-customer
	 * @param customer
	 * @return trueOrFalse
	 * @author Phuoc Sang
	 */
	@PutMapping("/update-customer")
	public boolean updateNewCustomer(@RequestBody Customer customer )
	{
		return iOperatorService.updateCustomer(customer.getCustomerName(),customer.getCustomerDob(),  customer.getCustomerAddress(),customer.getCustomerPhone());
	}

}
