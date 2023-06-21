package com.vn.tpbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.Department;
import com.vn.tpbank.entity.Manager;
import com.vn.tpbank.entity.User;

/**
	* @author Nguyen Manh Hai
*/
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
	@Query("Select u from User u WHERE userID NOT IN (SELECT m.user.userID from Manager m where u.userID = m.user.userID)")
    List<User> userHaveNotBeenChosen();
	
	@Query("Select d from Department d WHERE departmentId NOT IN (SELECT m.department.departmentId from Manager m where d.departmentId = m.department.departmentId)")
    List<Department> departmentHaveNotBeenChosen();
}
