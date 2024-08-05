package com.transaction.dev.transactions.service;

import com.transaction.dev.common.dto.ResponseDataDto;
import com.transaction.dev.transactions.dto.SaveTransactionDto;
import com.transaction.dev.transactions.dto.TransactionReportDto;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    ResponseDataDto registerTransaction(SaveTransactionDto saveTransactionDto);
    List<TransactionReportDto> getReport(LocalDate startDate, LocalDate endDate);
}
