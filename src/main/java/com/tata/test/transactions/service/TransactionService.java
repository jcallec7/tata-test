package com.tata.test.transactions.service;

import com.tata.test.dto.ResponseDataDto;
import com.tata.test.transactions.dto.SaveTransactionDto;

public interface TransactionService {

    ResponseDataDto registerTransaction(SaveTransactionDto saveTransactionDto);
}
