package com.vn.tpbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Transaction;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	public Department findByDepartmentId(Long departmentId);

//	public List<Transaction> findAllById(Long customerId);

	public List<Transaction> findAllByDepartmentId(Long customerId);
}
