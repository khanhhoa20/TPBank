package com.vn.tpbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IManagerService;

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
	public boolean deleteUsers(@PathVariable Long id) {
		return iManagerService.deleteAccount(id);
	}
	
//	@GetMapping("/listAllOperator")
//	public List<Operator> listAllOperator(){
//		List<Operator> operator = (List<Operator>) iManagerService.listAllOperator();
//		return operator;
//	}
}
