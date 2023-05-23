package com.vn.tpbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserNameAndUserPass(String userName, String userPass);
<<<<<<< HEAD
	public Optional<User>  findByUserName(String userName);
=======
	public Optional<User> findByUserName(String userName);
>>>>>>> parent of a235bd2 (ok)
}
