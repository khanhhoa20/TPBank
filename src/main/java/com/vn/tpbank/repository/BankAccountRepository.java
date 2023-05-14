package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.tpbank.entity.BankAccount;
@Repository
public interface BankAccountRepository  extends JpaRepository<BankAccount, Long>{

}
