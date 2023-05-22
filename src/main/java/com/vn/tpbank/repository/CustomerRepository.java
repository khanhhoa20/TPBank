package com.vn.tpbank.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Customer findByCustomerPhone(String phone);
	public Customer findByCustomerEmailAndCustomerNationalIdAndCustomerPhone(String cusEmail,Long cusNational,String phone);
}
