package com.tata.test.transactions.service;

import com.tata.test.dto.ResponseDataDto;
import com.tata.test.transactions.dto.SaveTransactionDto;
import com.tata.test.transactions.dto.TransactionReportDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    ResponseDataDto registerTransaction(SaveTransactionDto saveTransactionDto);
    List<TransactionReportDto> getReport(LocalDate startDate, LocalDate endDate);
}
