package com.vn.tpbank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tpbank.entity.Department;
import com.vn.tpbank.repository.DepartmentRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tpbank/department")
public class DepartmentController {
	
	@Autowired
	DepartmentRepository departmentRepo;
	
	@GetMapping("/{id}")
	public Optional<Department> geDepartmentById(@PathVariable Long id) {
		return departmentRepo.findById(id);
	}
}
