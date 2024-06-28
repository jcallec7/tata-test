package com.tata.test.transactions.service;

import com.tata.test.transactions.dto.AccountDto;
import com.tata.test.dto.ResponseDataDto;
import com.tata.test.transactions.dto.SaveAccountDto;

public interface AccountService {

    ResponseDataDto createAccount(SaveAccountDto saveAccountDto);
    AccountDto getAccountByAccountNumber(String accountNumber);
    ResponseDataDto updateAccount(Long id, AccountDto updateAccount) throws IllegalAccessException;
    ResponseDataDto deleteAccount(Long id) throws IllegalAccessException;
}
