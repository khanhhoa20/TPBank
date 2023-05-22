package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long>{
}
