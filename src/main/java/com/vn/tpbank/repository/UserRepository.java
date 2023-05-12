package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.tpbank.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
