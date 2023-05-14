package com.vn.tpbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vn.tpbank.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
