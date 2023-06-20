package com.vn.tpbank.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Manager;
import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.SchedulePlan;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.DepartmentRepository;
import com.vn.tpbank.repository.ManagerRepository;
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
	@Autowired
	ManagerRepository managerRepository;

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
		if (userRepository.findByUserName(username).isPresent()) {
			return "Username is already existed";
		}
		else if (operatorRepository.findByEmail(email).isPresent()) {
			return "Email already in use";
		}
		else if (operatorRepository.findByOperPhone(phoneNumber).isPresent()) {
			return "Phone number already in use";
		}
		else {
			User user = new User(null, username, password, "operator");
			if (departmentRepository.findByDepartmentId(departmentId)==null) {
				return "Department doesn't exist";
			}
			Department department = departmentRepository.findByDepartmentId(departmentId);
			userRepository.save(user);
			Operator operator = new Operator(null, phoneNumber, address, email, name, userRepository.findByUserName(username).get(), "active", department);
			operatorRepository.save(operator);
			return "Create operator successfully";
		}
	}

	@Override
	public String editOperator(String username, String password, String phoneNumber, String address, String email, String name, String status, Long departmentId) {
		Optional<User> dbUser = userRepository.findByUserName(username);
		Department department = departmentRepository.findByDepartmentId(departmentId);
		if (dbUser.isPresent() && department!=null) {
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
	public String deleteOperator(String username) {
		Optional<User> dbUser = userRepository.findByUserName(username);
		if (dbUser.isPresent()) {
			User user = dbUser.get();
			Operator operator = operatorRepository.findByUser(user);
			operatorRepository.delete(operator);
			return "Delete operator successfully";
		}
		return "Username does not exist";
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
		User user = customer.getUser();
//		Optional<User> user2 = userRepository.findByUserName(user.getUserName());
		if(userRepository.findByUserName(user.getUserName()).isEmpty()) {
			userRepository.save(user);
			customerRepository.save(customer);
			BankAccount bankAccount = new BankAccount(null, balance, bankName, lockStatus, customer,
					null);
			bankAccountRepository.save(bankAccount);
			return "Create account successfully";
		}else {
			return "Account is existed";
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
		return bankAccountRepository.findAll();
	}

	@Override
	public Optional<BankAccount> findAccountByID(Long id) {
		return bankAccountRepository.findById(id);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}
	
	@Override
	public Department getDepartment(Long departmentId) {
		if (departmentRepository.findByDepartmentId(departmentId)!=null)
			return departmentRepository.findByDepartmentId(departmentId);
		return null;
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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

	@Override
	public String deleteSchedulePlan(long scheduleId) {
		SchedulePlan findS = schedulePlanRepository.findById(scheduleId).orElse(null);
		if(findS != null) {
			Department d = findS.getDepartment();
			findS.setDepartment(null);
			schedulePlanRepository.delete(findS);
			return "Delete schedule_plan by id is " +scheduleId +" successfully!";
		}
		else return "Not found schedule_plan to delete!";
	}

	@Override
	public String updateSchedulePlan(SchedulePlan s, Long departmentId, long findScheduleId) {
		SchedulePlan findS = schedulePlanRepository.findById(findScheduleId).orElse(null);
		if(findS != null) {
			Department d = departmentRepository.findByDepartmentId(departmentId);
			findS.setScheduleplandetail_info(s.getScheduleplandetail_info());
			findS.setScheduleplan_description(s.getScheduleplan_description());
			findS.setScheduleplan_name(s.getScheduleplan_name());
			findS.setStartDate(s.getStartDate());
			findS.setEndDate(s.getEndDate());
			findS.setDepartment(d);
			schedulePlanRepository.save(findS);
			return "Update schedule_plan by id is " +findScheduleId +" successfully!";
		}
		else return "Not found schedule_plan to update!";
	}

	@Override
	public List<Manager> getAllManager() {
		// TODO Auto-generated method stub
		return managerRepository.findAll();
	}

	@Override
	public Optional<Manager> getManagerById(Long id) {
		// TODO Auto-generated method stub
		if (managerRepository.findById(id)!=null)
			return managerRepository.findById(id);
		return null;
	}

	@Override
	public Manager addManager(Manager manager) {
		// TODO Auto-generated method stub
		System.out.println(manager.getUser().getUserPass());
		return managerRepository.save(manager);
	}

	@Override
	public Manager updateManager(Long id, Manager managerDetails) {
		// TODO Auto-generated method stub
		Optional<Manager> updateManager = managerRepository.findById(id);
		if (updateManager != null) {
			Manager updated = updateManager.get();
			updated.setManagerPhone(managerDetails.getManagerPhone());
			updated.setManagerAddress(managerDetails.getManagerAddress());
			updated.setManagerEmail(managerDetails.getManagerEmail());
			updated.setManagerName(managerDetails.getManagerName());
			updated.setUser(managerDetails.getUser());
			updated.setDepartment(managerDetails.getDepartment());
			updated.setManagerStatus(managerDetails.getManagerStatus());
			
			Manager updatedManager = managerRepository.save(updated);
			return updatedManager;
		}
		else
			return null;
	}

	@Override
	public boolean disableManager(Long id) {
		// TODO Auto-generated method stub
		Optional<Manager> managerTmp = managerRepository.findById(id);
		if ( managerTmp != null) {
			Manager manager = managerTmp.get();
			if (manager.getManagerStatus().equals("Inactive")) {
				return false;
			}
			else {
				manager.setManagerStatus("Inactive");
				managerRepository.save(manager);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteManager(Long id) {
		// TODO Auto-generated method stub
		if (managerRepository.existsById(id)) {
			managerRepository.deleteById(id);
			return true;
		}	
		else 
			return false;
	}

	@Override
	public List<User> userHaveNotBeenChosen() {
		// TODO Auto-generated method stub
		return managerRepository.userHaveNotBeenChosen();
	}

	@Override
	public List<Department> departmentHaveNotBeenChosen() {
		// TODO Auto-generated method stub
		return managerRepository.departmentHaveNotBeenChosen();
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(username).get();
	}
	
	
}
