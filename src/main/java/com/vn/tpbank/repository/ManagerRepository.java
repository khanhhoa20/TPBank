package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
