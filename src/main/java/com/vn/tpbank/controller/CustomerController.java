package com.vn.tpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.request.ChangePasswordRequest;
import com.vn.tpbank.request.RegisterBankAccountRequest;
import com.vn.tpbank.request.TransactionRequest;
import com.vn.tpbank.request.UpdateInformationRequest;
import com.vn.tpbank.service.ICustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tpbank/customer")
public class CustomerController {
	@Autowired
	ICustomerService iCustomerService;
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return iCustomerService.login(user.getUserName(), user.getUserPass());
	}
	
	@PutMapping("/cusdetail")
	public Customer getCustomer(@RequestBody Customer customer) {
		return iCustomerService.getCustomer(customer.getCustomerId());
		
	}
	
	@PutMapping("/accdetail")
	public BankAccount getBankAccount(@RequestBody  Customer customer) {
		return iCustomerService.getBankAccount(customer.getCustomerId());
		
	}
	
	@PutMapping("/cusupdate")
	public String updateNewCustomer(@RequestBody Customer customer )
	{
		return iCustomerService.editCustomer(customer.getCustomerPhone(), customer.getCustomerEmail(), customer.getCustomerAddress(), customer.getUser().getUserName());
	}
    

//     @PostMapping("/login")
//     public String login(@RequestBody User user) {
//         return iCustomerService.login(user.getUserName(), user.getUserPass());
//     }

    @PutMapping("change-password")
    public String changePassword(@RequestBody ChangePasswordRequest request) {
//        return iCustomerService.changePassword(request.getUserName(), request.getUserPass(), request.getNewUserPass());
    	return null;
    }

    @GetMapping("/{id}/get-all-account")
    public List<BankAccount> getAllAccount(@PathVariable("id") Long id) {
        return iCustomerService.getAllAccount(id);
    }

    @PostMapping("/create-account")
    public String createAccount(@RequestBody RegisterBankAccountRequest account) {
        // Customer customer = new Customer();
        // customer.setCustomerId(id);
        // account.setCustomer(customer);
        return iCustomerService.RegisterCreateBankAccount(account);
    }

    @PutMapping("/deposit")
    public String deposit(@RequestBody TransactionRequest request) {
//        return iCustomerService.deposit(request.getAccountNumber(), request.getAmount());
    	return null;
    }

    @PutMapping("/withdraw")
    public String withdraw(@RequestBody TransactionRequest request) {
//        return iCustomerService.withdraw(request.getAccountNumber(), request.getAmount());
        return null;
    }

    @PutMapping("/transfer")
    public String transfer(@RequestBody TransactionRequest request) {
//        return iCustomerService.transfer(request.getAccountNumber(), request.getAccountNumber2(), request.getAmount());
    	return null;
    }

    @PutMapping("/update-information")
    public String updateInformation(@RequestBody UpdateInformationRequest customer) {
//        if (customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
//            return "The name is empty";
//        }
//        if (customer.getCustomerAddress() == null || customer.getCustomerAddress().isEmpty()) {
//            return "The address is empty";
//        }
//        if (customer.getCustomerPhone() == null || customer.getCustomerPhone().isEmpty()) {
//            return "The phone is empty";
//        }
//        if (customer.getCustomerEmail() == null || customer.getCustomerEmail().isEmpty()) {
//            return "The email is empty";
//        }
//        if (customer.getCustomerDob() == null) {
//            return "The dob is empty";
//        }
//        return iCustomerService.updateInformation(customer);
    	return null;
    }

    // @GetMapping("/cusdetail")
    // public Customer getCustomer(@RequestBody Customer customer) {
    // return iCustomerService.getCustomer(customer.getCustomerId());

    // }

    // @GetMapping("/accdetail")
    // public BankAccount getBankAccount(@RequestBody Customer customer) {
    // return iCustomerService.getBankAccount(customer.getCustomerId());

    // }

    // @PutMapping("/cusupdate")
    // public String updateNewCustomer(@RequestBody Customer customer )
    // {
    // return iCustomerService.editCustomer(customer.getCustomerId(),
    // customer.getCustomerPhone(), customer.getCustomerEmail(),
    // customer.getCustomerAddress(), customer.getUser().getUserPass(),
    // customer.getUser().getUserName());
    // }
}
