package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.tpbank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
