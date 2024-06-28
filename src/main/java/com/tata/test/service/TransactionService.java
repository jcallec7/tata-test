package com.tata.test.service;

import com.tata.test.dto.ResponseDataDto;
import com.tata.test.dto.SaveTransactionDto;

public interface TransactionService {

    ResponseDataDto registerTransaction(SaveTransactionDto saveTransactionDto);
}
