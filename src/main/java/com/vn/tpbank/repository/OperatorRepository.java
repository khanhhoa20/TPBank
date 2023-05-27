package com.vn.tpbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Operator;
import com.vn.tpbank.entity.User;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long>{
	public Operator findByOperName(String operName);
	public Optional<Operator> findById(Long id);
	public Operator findByUser(User user);
	public Optional<Operator> findByEmail(String email);
	public Optional<Operator> findByOperPhone(String operPhone);
	
}
