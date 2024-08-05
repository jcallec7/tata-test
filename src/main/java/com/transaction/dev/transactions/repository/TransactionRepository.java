package com.transaction.dev.transactions.repository;

import com.transaction.dev.transactions.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
