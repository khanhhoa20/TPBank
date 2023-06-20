package com.vn.tpbank.controller;

import java.util.List;
import java.util.Optional;

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
import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Manager;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.SchedulePlan;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.SchedulePlanRepository;
import com.vn.tpbank.service.IManagerService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tpbank/manager")
public class ManagerController {
	@Autowired
	IManagerService iManagerService;
	
	@Autowired
	SchedulePlanRepository schedulePlanRepo;
	
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
	
	@PostMapping("createBankAccount")
	public String CreateAccount(@RequestBody BankAccount bankAccount) {
		return iManagerService.createAccount(bankAccount.getBalance(), bankAccount.getBankName(),
				bankAccount.getLockStatus(), bankAccount.getCustomer());
	}
	@DeleteMapping("/deleteBankAccount/{id}")
	public boolean deleteUsers(@PathVariable Long id) {
		return iManagerService.deleteAccount(id);
	}
	
	@GetMapping("getAllBankAccount")
	public List<BankAccount> getAllBankAccount() {
		return iManagerService.getAllBankAccount();
	}
	
	@GetMapping("findAccountById/{id}")
	public Optional<BankAccount> findBankAccountById(@PathVariable Long id) {
		return iManagerService.findAccountByID(id);
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
	
	@GetMapping
	public List<Manager> getAllManager() {
		return iManagerService.getAllManager();
	}
	
	@GetMapping("/{id}")
	public Optional<Manager> getManagerById(@PathVariable Long id) {
		return iManagerService.getManagerById(id);
	}
	
	@PostMapping("/add-manager")
	public Manager addManager(@RequestBody Manager manager) {
		return iManagerService.addManager(manager);
	}
	
	@PutMapping("/update-manager/{id}")
	public Manager updateManager(@PathVariable Long id, @RequestBody Manager manager) {
		return iManagerService.updateManager(id, manager);
	}
	
	@PutMapping("/disable-manager/{id}")
	public boolean disableManager(@PathVariable Long id) {
		return iManagerService.disableManager(id);
	}
	
	@DeleteMapping("/delete-manager/{id}")
	public boolean deleteManager(@PathVariable Long id) {
		return iManagerService.deleteManager(id);
	}
	
	@GetMapping("/user-have-not-been-chosen")
	public List<User> userHaveNotBeenChosen() {
		return iManagerService.userHaveNotBeenChosen();
	}
	
	@GetMapping("/department-have-not-been-chosen")
	public List<Department> departmentHaveNotBeenChosen() {
		return iManagerService.departmentHaveNotBeenChosen();
	}
	
	@GetMapping("/showUser/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return iManagerService.getUserByUsername(username);
	}
	
	@GetMapping("/showDepartment/{id}")
	public Department getDepartment(@PathVariable Long id) {
		return iManagerService.getDepartment(id);
	}
	

	//**** Department-controller ****
	@GetMapping("/listAllDepartments")
	public List<Department> getAllDepartments() {
		return iManagerService.getAllDepartments();
	}
	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department d) {
		return iManagerService.insertDepartment(d);
	}
	//**** Department-controller ****
		
	// **** SchedulePlan-controller ****
	@GetMapping("/listAllSchedulePlans")
	public List<SchedulePlan> getAllSchedulePlans() {
		return iManagerService.getAllSchedulePlans();
	}
	@PostMapping("/addSchedulePlan")
	public String addSchedulePlan(@RequestBody SchedulePlan s) {
		return iManagerService.insertSchedulePlan(s, Long.valueOf(s.getDepartment().getDepartmentId()));
	}
	@DeleteMapping("/deleteSchedulePlan/{id}")
	public String deleteSchedulePlan(@PathVariable long id) {
		return iManagerService.deleteSchedulePlan(id);
	}
	@PutMapping("/updateSchedulePlan/{id}")
	public String updateSchedulePlan(@RequestBody SchedulePlan s, @PathVariable long id) {
		return iManagerService.updateSchedulePlan(s, Long.valueOf(s.getDepartment().getDepartmentId()), id);
	}
	// **** SchedulePlan-controller ****
}
