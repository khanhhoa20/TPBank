package com.vn.tpbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	public Optional<Department> findByDepartmentId(Long departmentId);
}
