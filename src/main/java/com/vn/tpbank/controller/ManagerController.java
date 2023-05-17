package com.vn.tpbank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.repository.ManagerRepository;
import com.vn.tpbank.entity.Manager;
import com.vn.tpbank.entity.User;

@RestController
@RequestMapping("/tpbank/manager")
public class ManagerController {
	
	@Autowired
	ManagerRepository managerRepo;
	
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
