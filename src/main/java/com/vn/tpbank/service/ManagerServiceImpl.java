package com.vn.tpbank.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.SchedulePlan;
import com.vn.tpbank.entity.Transaction;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.DepartmentRepository;
import com.vn.tpbank.repository.OperatorRepository;
import com.vn.tpbank.repository.SchedulePlanRepository;
import com.vn.tpbank.repository.UserRepository;

@Service
public class ManagerServiceImpl implements IManagerService {
	@Autowired
	OperatorRepository operatorRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	BankAccountRepository bankAccountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	SchedulePlanRepository schedulePlanRepository;

	@Override
	public String login(String username, String password) {
		User user = userRepository.findByUserNameAndUserPass(username, password);
		if (user!=null && user.getRole().equalsIgnoreCase("manager")) {
			return "Login successfully with role manager";
		}
		return "Wrong username or password";
	}

	@Override
	public String createOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId) {
		if (userRepository.findByUserName(username).isEmpty()) {
			User user = new User(null, username, password, "operator");
			Department department = departmentRepository.findByDepartmentId(departmentId);
			userRepository.save(user);
			Operator operator = new Operator(null, phoneNumber, address, email, name, userRepository.findByUserName(username).get(), "active", department);
			operatorRepository.save(operator);
			return "Create operator successfully";
		}
		else {
			return "Operator is existed";
		}
		
	}

	@Override
	public String editOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId) {
		Optional<User> dbUser = userRepository.findByUserName(username);
		Department department = departmentRepository.findByDepartmentId(departmentId);
		if (dbUser.isPresent()) {
			User user = dbUser.get();
			Operator operator = operatorRepository.findByUser(user);
			operator.setOperPhone(phoneNumber);
			operator.setOperAddress(address);
			operator.setEmail(email);
			operator.setOperName(name);
			operator.setOperatorStatus(status);
			operator.setDepartment(department);
			operator.setUser(user);
			operatorRepository.save(operator);
			return "Edit operator successfully";
		}
		else {
			return "Operator does not exist";
		}
		
	}

	@Override
	public String disableOperator(String username) {
		Optional<User> dbUser = userRepository.findByUserName(username);
		if (dbUser.isPresent()) {
			Operator operator = operatorRepository.findByUser(dbUser.get());
			if (operator.getOperatorStatus().equals("inactive")) {
				return "Operator is already inactive";
			}
			else {
				operator.setOperatorStatus("inactive");
				operatorRepository.save(operator);
				return "Operator is inactive";
			}
		}
		return "User does not exist";
	}
	
	@Override
	public List<Operator> listAllOperator() {
		List<Operator> listOperator = (List<Operator>) operatorRepository.findAll();
		
		return listOperator;
	}
	
	@Override
	public String createAccount(Long balance, String bankName, String lockStatus, Customer customer) {
		// TODO Auto-generated method stub
	    try {
	        User user = customer.getUser();
	        if (userRepository.findByUserName(user.getUserName()).isEmpty()) {
	            BankAccount bankAccount = new BankAccount(null, balance, bankName, lockStatus, customer, null);
	            bankAccountRepository.save(bankAccount);
	            return "Create account successfully";
	        } else {
	            return "Account already exists";
	        }
	    } catch (Exception e) {
	        return "Error creating account: " + e.getMessage();
	    }
	}

	@Override
	public boolean deleteAccount(Long id) {
		if (bankAccountRepository.existsById(id)) {
			bankAccountRepository.deleteById(id);
			return true;
		}	
		else 
			return false;
	}

	@Override
	public List<BankAccount> getAllBankAccount() {
		// TODO Auto-generated method stub
		return bankAccountRepository.findAll();
	}

	@Override
	public Optional<BankAccount> findAccountByID(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(bankAccountRepository.findById(id)
				.orElseThrow(()-> new ResourceAccessException("Cann't find bank account with ID = "+id)));
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department insertDepartment(Department d) {
		Department findD = departmentRepository.findByDepartmentId(d.getDepartmentId());
		if(d!=null && d.getDepartmentName()!=null && findD==null)
			return departmentRepository.save(d);
		return null;
	}

	@Override
	public List<SchedulePlan> getAllSchedulePlans() {
		return schedulePlanRepository.findAll();
	}

	//get current-date
	Date createDate = Date.from((LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String insertSchedulePlan(SchedulePlan s, Long departmentId) {
		Department d = departmentRepository.findByDepartmentId(departmentId);
		if(d==null)
			return "Not found department to add scheduleplan!";
		if(s.getCreateDate()==null) {				
			schedulePlanRepository.save(new SchedulePlan(s.getScheduleplandetail_info(), s.getScheduleplan_description(), s.getScheduleplan_name(), 
														s.getStartDate(), s.getEndDate(), createDate, d));
		}
		return "Add a schedule_plan successfully";
	}

}
