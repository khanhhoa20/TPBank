package com.vn.tpbank.service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.BankAccount;
import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;
import com.vn.tpbank.repository.BankAccountRepository;
import com.vn.tpbank.repository.CustomerRepository;
import com.vn.tpbank.repository.UserRepository;

@Service
public class OperatorServiceImpl implements IOperatorService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Override
	public String login(String username, String pass) {
		User user = userRepository.findByUserNameAndUserPass(username, pass);
		if (user != null) {
			return "Login successfully. Role: " + user.getRole();
		}
		return "Wrong username or password!";
	}

	@Override
	public String unlockBankAccount(String cusPhone) {
		Customer cus = customerRepository.findByCustomerPhone(cusPhone);
		if (cus != null) {
			BankAccount bankAccount = bankAccountRepository.findByCustomer(cus);
			if (bankAccount.getLockStatus().equals("active")) {
				return "This bank account is currently active. No need to unlock.";
			} else {
				bankAccount.setLockStatus("active");
				bankAccountRepository.save(bankAccount);
				return "Unlock successfully.";
			}
		} else {
			return "Phone is not in the system";
		}

	}

	@Override
	public String lockBankAccount(String cusPhone) {
		String check = null;
		Customer customer = customerRepository.findByCustomerPhone(cusPhone);
		if (customer != null) {
			BankAccount account = bankAccountRepository.findByCustomer(customer);
			if (account.getLockStatus().equalsIgnoreCase("inactive")) {
				check = "Account has Locked Before";
			} else {
				account.setLockStatus("inactive");
				bankAccountRepository.save(account);
				if (account.getLockStatus().equals("inactive")) {
					check = "Account is now Locked";
				}
			}
		} else {
			check = "Can't find the Customer";
		}

		return check;
	}

	@Override
	public String createBankAccount(BankAccount account) {
		Customer customer = null;
		customer = account.getCustomer();
		customer = customerRepository.findByCustomerEmailAndCustomerNationalIdAndCustomerPhone(
				customer.getCustomerEmail(), customer.getCustomerNationalId(), customer.getCustomerPhone());
		Optional<User> user = userRepository.findByUserName(account.getCustomer().getUser().getUserName());
		String check = null;
		if (customer == null && user.isEmpty()) {
			bankAccountRepository.save(account);
			check = "Bank Account Create Susscess";
		} else {
			check = "Bank Account Already Exits or Some Detail Not Right ";
		}

		return check;

	}

	@Override
	public List<Customer> viewCustomers() {
		return customerRepository.findAll();
	}

	public boolean updateCustomer(String email, String address, String phone) {
		Customer cus = null;
		cus = customerRepository.findByCustomerPhone(phone);
		Customer customer = customerRepository.findByCustomerEmail(email);
		if (cus == null || customer != null) {
			return false;
		} else {
			BankAccount account = null;
			account = bankAccountRepository.findByCustomer(cus);
			if (account != null) {
				if (account.getLockStatus().equalsIgnoreCase("Inactive")) {
					return false;
				} else {

					cus.setCustomerAddress(address);
					cus.setCustomerEmail(email);
					cus = customerRepository.save(cus);
					return true;

				}
			} else {
				return false;
			}
		}
	}

}
