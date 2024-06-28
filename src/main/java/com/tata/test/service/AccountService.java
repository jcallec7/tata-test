package com.tata.test.service;

import com.tata.test.dto.AccountDto;
import com.tata.test.dto.ResponseDataDto;
import com.tata.test.dto.SaveAccountDto;

public interface AccountService {

    ResponseDataDto createAccount(SaveAccountDto saveAccountDto);
    AccountDto getAccountByAccountNumber(String accountNumber);
    ResponseDataDto updateAccount(Long id, AccountDto updateAccount) throws IllegalAccessException;
    ResponseDataDto deleteAccount(Long id) throws IllegalAccessException;
}
