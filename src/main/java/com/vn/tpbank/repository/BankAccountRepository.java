package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.BankAccount;
import java.util.List;
import com.vn.tpbank.entity.Customer;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

	public BankAccount findByCustomer(Customer customer);

	@Query ("Delete FROM BankAccount b where b.customer.customerId =:customerId ")
	public void deleteByCustomerId(@Param("customerId") long customerId);

	@Query ("Select b FROM BankAccount b where b.customer.customerId =:customerId ")
	public List<BankAccount> findByCustomerId(@Param("customerId") long customerId);
//	@Modifying
//	@Query("UPDATE BankAccount b SET b.lockStatus='inactive' WHERE b.customer.customerId=3")
//	public void unlockBankAccount();
}
