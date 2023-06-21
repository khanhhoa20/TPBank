package com.vn.tpbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Manager;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.SchedulePlan;
import com.vn.tpbank.entity.Transaction;
import com.vn.tpbank.entity.User;

public interface IManagerService {
	public String login(String username, String password);
	public String createOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId);
	public String editOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId);
	public String disableOperator(String username);
	public String deleteOperator(String username);
	public List<Operator> listAllOperator();
	public Department getDepartment(Long departmentId);
	public String createAccount(Long balance, String bankName, String lockStatus, Customer customer);
	public boolean deleteAccount(Long id);
	public List<BankAccount> getAllBankAccount();
	public Optional<BankAccount> findAccountByID(Long id);
	public String updateBankAccount(Long id, Long balance, String bankName, String lockStatus, Customer customer);
	
	public List<Department> getAllDepartments();
	public Department insertDepartment(Department d);
	
	public List<SchedulePlan> getAllSchedulePlans();
	public String insertSchedulePlan(SchedulePlan s, Long departmentId);
	public String deleteSchedulePlan(long scheduleId);
	public String updateSchedulePlan(SchedulePlan s, Long departmentId, long findScheduleId);

	
	public List<Manager> getAllManager();
	public Optional<Manager> getManagerById(Long id);
	public Manager addManager(Manager manager);
	public Manager updateManager(Long id, Manager manager);
	public User getUserByUsername(String username);
	public boolean disableManager(Long id);
	public boolean deleteManager(Long id);
	public List<User> userHaveNotBeenChosen();
	public List<Department> departmentHaveNotBeenChosen();
	
}
