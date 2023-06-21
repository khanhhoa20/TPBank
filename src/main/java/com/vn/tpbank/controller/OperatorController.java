package com.vn.tpbank.controller;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.vn.tpbank.entity.Transaction;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IOperatorService;

@CrossOrigin
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
	 * @URL http://localhost:9090/tpbank/operator/dang-nhap
	 * @param userName, userPass
	 * @return message login success or not
	 * @author khánh hòa
	 */
	@PostMapping("dang-nhap")
	public String dangNhap(@RequestBody User user) {
		return iOperatorService.dangNhap(user.getUserName(), user.getUserPass());

	}

	/**
	 * @URL http://localhost:9090/tpbank/operator/unlock-bank-account
	 * @param customerPhone
	 * @return message unlock success or not
	 * @author khánh hòa
	 */
	@PutMapping("unlock-bank-account")
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
	 *                    "customerDob": "2023-05-25",
	 *                    "user": {
	 * 
	 *                    "userName":"9152224",
	 *                    "userPass":"123",
	 *                    "role" :"customer"
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
	public List<BankAccount> viewListCustomer()
	{
		return iOperatorService.viewCustomers();
	}
	@DeleteMapping("delete-customer/{id}")
	public String deleteCustomer (@PathVariable long id)
	{
		return  iOperatorService.deleteCustomer(id);
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/operator/update-customer
	 * @param customer
	 * @return trueOrFalse
	 * @author Phuoc Sang
	 */
	@PutMapping("/update-customer")
	public String updateNewCustomer(@RequestBody Customer customer )
	{
		return iOperatorService.updateCustomer(customer.getCustomerPhone(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getUser().getUserPass());	}

/**
 * @URL http://localhost:9090/tpbank/operator/deposit
 * @param transaction
 * @return result string
 * @author ngoc huan
 * */	
	
	@PutMapping(value="/deposit")
	public String depositMoney(@RequestBody Transaction transaction) {
		return iOperatorService.depositMoney(transaction);
	}

	/**
	 * @URL http://localhost:9090/tpbank/operator/withdraw
	 * @param transaction
	 * @return result string
	 * @author ngoc huan
	 * */
	
	@PutMapping(value="/withdraw")
	public String withdrawMoney(@RequestBody Transaction transaction) {
		return iOperatorService.withdrawMoney(transaction);
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/operator/transfer
	 * @param senderTransaction, recieveTransaction
	 * @return result string
	 * @author ngoc huan
	 * */
	
	@PutMapping(value="/transfer")
	public String transferMoney(@RequestBody Transaction senderTransaction, Transaction recieveTransaction) {
		return iOperatorService.transferMoney(senderTransaction, recieveTransaction);
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/operator/view-transaction
	 * @param phoneNumber
	 * @return transaction list
	 * @author ngoc huan
	 * */
	
	@PutMapping(value = "/view-transactions")
	public List<Transaction> findTransactionsByPhone(@RequestBody String phoneNumber){
		return iOperatorService.findTransactionByPhone(phoneNumber);
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/operator/find-sender-and-reciever
	 * @param transaction
	 * @return sender/reciever bankaccount detail
	 * @author ngoc huan
	 * */
	
	@PutMapping(value="/find-sender-and-reciever")
	public BankAccount findBankAccountThroughTransaction(Transaction transaction) {
		return iOperatorService.findBankAccountThroughTransaction(transaction);
	}
}
