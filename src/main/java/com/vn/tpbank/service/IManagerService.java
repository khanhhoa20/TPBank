package com.vn.tpbank.service;

import java.util.List;
import java.util.Optional;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.SchedulePlan;

public interface IManagerService {
	public String login(String username, String password);
	public String createOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId);
	public String editOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId);
	public String disableOperator(String usernam);
	public List<Operator> listAllOperator();
	public Department getDepartment(Long departmentId);
	public String createAccount(Long balance, String bankName, String lockStatus, Customer customer);
	public boolean deleteAccount(Long id);
	public List<BankAccount> getAllBankAccount();
	public Optional<BankAccount> findAccountByID(Long id);
	
	public List<Department> getAllDepartments();
	public Department insertDepartment(Department d);
	
	public List<SchedulePlan> getAllSchedulePlans();
	public String insertSchedulePlan(SchedulePlan s, Long departmentId);
}
