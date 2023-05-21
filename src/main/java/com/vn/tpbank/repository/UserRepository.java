package com.vn.tpbank.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserNameAndUserPass(String userName, String userPass);
	public User findByUserName(String userName);
}
