package com.transaction.dev.transactions.service;

import com.transaction.dev.transactions.dto.AccountDto;
import com.transaction.dev.common.dto.ResponseDataDto;
import com.transaction.dev.transactions.dto.SaveAccountDto;

public interface AccountService {

    ResponseDataDto createAccount(SaveAccountDto saveAccountDto);
    AccountDto getAccountByAccountNumber(String accountNumber);
    ResponseDataDto updateAccount(Long id, AccountDto updateAccount) throws IllegalAccessException;
    ResponseDataDto deleteAccount(Long id) throws IllegalAccessException;
}
