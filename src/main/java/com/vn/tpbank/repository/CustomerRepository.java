package com.vn.tpbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Customer;
import com.vn.tpbank.entity.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Customer findByCustomerName(String name);
//	public Optional<Customer> findByCustomerId(Long id);
	public Customer findByCustomerId(Long id);
	
	public Customer findByUser(User user);
	public Customer findByCustomerPhone(String phone);
	public Customer findByCustomerEmail(String email);
	public Customer findByCustomerEmailAndCustomerNationalIdAndCustomerPhone(String customerEmail,Long customerNationalId, String customerPhone);
	
}
