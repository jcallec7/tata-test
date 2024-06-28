package com.tata.test.transactions.repository;

import com.tata.test.transactions.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
