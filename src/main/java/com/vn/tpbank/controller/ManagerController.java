package com.vn.tpbank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IManagerService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tpbank/manager")
public class ManagerController {
	@Autowired
	IManagerService iManagerService; 
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return iManagerService.login(user.getUserName(), user.getUserPass());
	}
	
	@PostMapping("/create-operator")
	public String createOperator(@RequestBody Operator operator) {
		return iManagerService.createOperator(operator.getUser().getUserName(), operator.getUser().getUserPass(), operator.getOperPhone(), operator.getOperAddress(), operator.getEmail(), operator.getOperName(), operator.getOperatorStatus(), operator.getDepartment().getDepartmentId());
	}
	
	@PostMapping("/edit-operator")
	public String editOperator(@RequestBody Operator operator) {
		return iManagerService.editOperator(operator.getUser().getUserName(), operator.getUser().getUserPass(), operator.getOperPhone(), operator.getOperAddress(), operator.getEmail(), operator.getOperName(), operator.getOperatorStatus(), operator.getDepartment().getDepartmentId());
	}
	
	@PostMapping("/disable-operator/{username}")
	public String disableOperator(@PathVariable String username) {
		return iManagerService.disableOperator(username);
	}
	
	@PostMapping("createBankAccount")
	public String CreateAccount(@RequestBody BankAccount bankAccount) {
		return iManagerService.createAccount(bankAccount.getBalance(), bankAccount.getBankName(),
				bankAccount.getLockStatus(), bankAccount.getCustomer());
	}
	
	@DeleteMapping("/deleteBankAccount/{id}")
	public ResponseEntity<String> deleteUsers(@PathVariable Long id) {
	    try {
	        boolean deletionStatus = iManagerService.deleteAccount(id);
	        if (deletionStatus) {
	            return ResponseEntity.ok("Bank account deleted successfully");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting bank account: " + e.getMessage());
	    }
	}
	
	@GetMapping("getAllBankAccount")
	public List<BankAccount> getAllBankAccount() {
		return iManagerService.getAllBankAccount();
	}
	
	@GetMapping("findAccountById/{id}")
	public ResponseEntity<BankAccount> findBankAccountById(@PathVariable Long id) {
	    try {
	        Optional<BankAccount> bankAccount = iManagerService.findAccountByID(id);
	        if (bankAccount.isPresent()) {
	            return ResponseEntity.ok(bankAccount.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (ResourceAccessException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
//	@GetMapping("/listAllOperator")
//	public List<Operator> listAllOperator(){
//		List<Operator> operator = (List<Operator>) iManagerService.listAllOperator();
//		return operator;
//	}
}
