package com.vn.tpbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.tpbank.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//	Transaction findTransactionById(Long transactionId);
}
