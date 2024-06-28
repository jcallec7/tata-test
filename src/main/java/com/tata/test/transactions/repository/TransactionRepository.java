package com.tata.test.transactions.repository;

import com.tata.test.transactions.dto.TransactionReportDto;
import com.tata.test.transactions.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
