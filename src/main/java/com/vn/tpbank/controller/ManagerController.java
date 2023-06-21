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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Manager;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.SchedulePlan;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.service.IManagerService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tpbank/manager")
public class ManagerController {
	@Autowired
	IManagerService iManagerService;
	
	/**
	 * @author Truong Huu Tai
	 */
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return iManagerService.login(user.getUserName(), user.getUserPass());
	}
	
	/**
	 * @author Truong Huu Tai
	 */
	@PostMapping("/create-operator")
	public String createOperator(@RequestBody Operator operator) {
		return iManagerService.createOperator(operator.getUser().getUserName(), operator.getUser().getUserPass(), operator.getOperPhone(), operator.getOperAddress(), operator.getEmail(), operator.getOperName(), operator.getOperatorStatus(), operator.getDepartment().getDepartmentId());
	}
	
	/**
	 * @author Truong Huu Tai
	 */
	@PostMapping("/edit-operator")
	public String editOperator(@RequestBody Operator operator) {
		return iManagerService.editOperator(operator.getUser().getUserName(), operator.getUser().getUserPass(), operator.getOperPhone(), operator.getOperAddress(), operator.getEmail(), operator.getOperName(), operator.getOperatorStatus(), operator.getDepartment().getDepartmentId());
	}
	
	/**
	 * @author Truong Huu Tai
	 */
	@DeleteMapping("/delete-operator/{username}")
	public String deleteOperator(@PathVariable("username") String username) {
		return iManagerService.deleteOperator(username);
	}
	
	/**
	 * @author Truong Huu Tai
	 */
	@PostMapping("/disable-operator/{username}")
	public String disableOperator(@PathVariable("username") String username) {
		return iManagerService.disableOperator(username);
	}
	
	/**
	 * @param bankAccount
	 * 
	 * @return message Create account successfully or not
	 * @author ThanhPhuc
	 */
	@PostMapping("/createBankAccount")
	public String CreateAccount(@RequestBody BankAccount bankAccount) {
		return iManagerService.createAccount(bankAccount.getBalance(), bankAccount.getBankName(),
				bankAccount.getLockStatus(), bankAccount.getCustomer());
	}
		
	/**
	 * Deletes a bank account based on the provided ID.
	 *
	 * @param id the ID of the bank account to delete
	 * @return a ResponseEntity indicating the status of the deletion
	 * @author ThanhPhuc
	 */

	@DeleteMapping("/deleteBankAccount/{id}")
	public ResponseEntity<String> deleteBankAccount(@PathVariable Long id) {
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
	
	/**
	 * Retrieves all bank accounts.
	 *
	 * @return a list of all bank accounts
	 * @author ThanhPhuc
	 */
	@GetMapping("getAllBankAccount")
	public List<BankAccount> getAllBankAccount() {
		return iManagerService.getAllBankAccount();
	}
	
	/**
	 * Retrieves a bank account by ID.
	 *
	 * @param id the ID of the bank account to retrieve
	 * @return a ResponseEntity containing the bank account if found, or a not found response if not found
	 * @author ThanhPhuc
	 */
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
	
	/**
	 * Updates a bank account.
	 *
	 * @param bankAccount the bank account object containing the updated information
	 * @return a String indicating the status of the update operation
	 * @author ThanhPhuc
	 */
	@PutMapping("/updateBankAccount")
	public String updateBankAccount(@RequestBody BankAccount bankAccount) {
		return iManagerService.updateBankAccount(bankAccount.getBankAccountId(), bankAccount.getBalance(), bankAccount.getBankName(), bankAccount.getLockStatus(), bankAccount.getCustomer());
	}
//	@GetMapping("/listAllOperator")
//	public List<Operator> listAllOperator(){
//		List<Operator> operator = (List<Operator>) iManagerService.listAllOperator();
//		return operator;
//	}
	@GetMapping("/listAllOperator")
	public List<Operator> listAllOperator(){
		List<Operator> operator = (List<Operator>) iManagerService.listAllOperator();
		return operator;
	}

	
	/**
	 * @author Nguyen Manh Hai
	 */
	@GetMapping
	public List<Manager> getAllManager() {
		return iManagerService.getAllManager();
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@GetMapping("/{id}")
	public Optional<Manager> getManagerById(@PathVariable Long id) {
		return iManagerService.getManagerById(id);
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@PostMapping("/add-manager")
	public Manager addManager(@RequestBody Manager manager) {
		return iManagerService.addManager(manager);
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@PutMapping("/update-manager/{id}")
	public Manager updateManager(@PathVariable Long id, @RequestBody Manager manager) {
		return iManagerService.updateManager(id, manager);
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@PutMapping("/disable-manager/{id}")
	public boolean disableManager(@PathVariable Long id) {
		return iManagerService.disableManager(id);
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@DeleteMapping("/delete-manager/{id}")
	public boolean deleteManager(@PathVariable Long id) {
		return iManagerService.deleteManager(id);
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@GetMapping("/user-have-not-been-chosen")
	public List<User> userHaveNotBeenChosen() {
		return iManagerService.userHaveNotBeenChosen();
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@GetMapping("/department-have-not-been-chosen")
	public List<Department> departmentHaveNotBeenChosen() {
		return iManagerService.departmentHaveNotBeenChosen();
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@GetMapping("/showUser/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return iManagerService.getUserByUsername(username);
	}

	/**
	 * @author Nguyen Manh Hai
	 */
	@GetMapping("/showDepartment/{id}")
	public Department getDepartment(@PathVariable Long id) {
		return iManagerService.getDepartment(id);
	}
	

	/**
	 * @URL http://localhost:9090/tpbank/manager/listAllDepartments
	 * @param
	 * @return list all departments
	 * @author Dinh Quang Tuan
	 */
	@GetMapping("/listAllDepartments")
	public List<Department> getAllDepartments() {
		return iManagerService.getAllDepartments();
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/manager/addDepartment
	 * @param d
	 * @return inserted a department
	 * @author Dinh Quang Tuan
	 */
	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department d) {
		return iManagerService.insertDepartment(d);
	}
		
	/**
	 * @URL http://localhost:9090/tpbank/manager/listAllSchedulePlans
	 * @param
	 * @return list all scheduleplans
	 * @author Dinh Quang Tuan
	 */
	@GetMapping("/listAllSchedulePlans")
	public List<SchedulePlan> getAllSchedulePlans() {
		return iManagerService.getAllSchedulePlans();
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/manager/addSchedulePlan
	 * @param s
	 * @return notification string
	 * @author Dinh Quang Tuan
	 */
	@PostMapping("/addSchedulePlan")
	public String addSchedulePlan(@RequestBody SchedulePlan s) {
		return iManagerService.insertSchedulePlan(s, Long.valueOf(s.getDepartment().getDepartmentId()));
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/manager/deleteSchedulePlan/{id}
	 * @param id
	 * @return notification string
	 * @author Dinh Quang Tuan
	 */
	@DeleteMapping("/deleteSchedulePlan/{id}")
	public String deleteSchedulePlan(@PathVariable long id) {
		return iManagerService.deleteSchedulePlan(id);
	}
	
	/**
	 * @URL http://localhost:9090/tpbank/manager/updateSchedulePlan/{id}
	 * @param s, departmentId, scheduleId
	 * @return notification string
	 * @author Dinh Quang Tuan
	 */
	@PutMapping("/updateSchedulePlan/{id}")
	public String updateSchedulePlan(@RequestBody SchedulePlan s, @PathVariable long id) {
		return iManagerService.updateSchedulePlan(s, Long.valueOf(s.getDepartment().getDepartmentId()), id);
	}
}
