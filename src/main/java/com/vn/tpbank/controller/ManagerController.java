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

import com.vn.tpbank.entity.Manager;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.ManagerRepository;
import com.vn.tpbank.service.IManagerService;

@RestController
@RequestMapping("/tpbank/manager")
@CrossOrigin
public class ManagerController {
	@Autowired
	IManagerService iManagerService; 
	ManagerRepository managerRepo;
	
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
	
	@GetMapping("/listAllOperator")
	public List<Operator> listAllOperator(){
		List<Operator> operator = (List<Operator>) iManagerService.listAllOperator();
		return operator;
	}
	
	@GetMapping("/list")
	public List<Manager> getAllManager() {
		return (List<Manager>) managerRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Manager> getManagerById(@PathVariable Long id) {
		return managerRepo.findById(id);
	}
	
	@PostMapping("/add")
	public Manager addManager(@RequestBody Manager manager) {
		return managerRepo.save(manager);
	}
	
	@PutMapping("/update/{id}")
	public Manager updateManager(@PathVariable Long id, @RequestBody Manager manager) {
		if (managerRepo.existsById(id))
			return managerRepo.save(manager);
		else 
			return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteUsers(@PathVariable Long id) {
		if (managerRepo.existsById(id)) {
			managerRepo.deleteById(id);
			return true;
		}	
		else 
			return false;
	}
}
