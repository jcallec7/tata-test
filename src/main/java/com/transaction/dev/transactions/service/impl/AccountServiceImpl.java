package com.transaction.dev.transactions.service.impl;

import com.transaction.dev.transactions.dto.SaveAccountDto;
import com.transaction.dev.transactions.entity.Accounts;
import com.transaction.dev.clients.entity.Clients;
import com.transaction.dev.common.enums.ResponseDataEnum;
import com.transaction.dev.transactions.repository.AccountRepository;
import com.transaction.dev.clients.repository.ClientRepository;
import com.transaction.dev.transactions.service.AccountService;
import com.transaction.dev.transactions.dto.AccountDto;
import com.transaction.dev.common.dto.ResponseDataDto;
import com.transaction.dev.utils.utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public ResponseDataDto createAccount(SaveAccountDto saveAccountDto) {

        Clients client = clientRepository.findById(saveAccountDto.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));

        Accounts account = new Accounts();
        account.setAccountNumber(saveAccountDto.getAccountNumber());
        account.setAccountType(saveAccountDto.getAccountType());
        account.setInitialBalance(saveAccountDto.getInitialBalance());
        account.setClient(client);

        accountRepository.save(account);

        return ResponseDataDto.builder()
                .message(ResponseDataEnum.SUCCESS.getMessage())
                .code(ResponseDataEnum.SUCCESS.getCode())
                .status(ResponseDataEnum.SUCCESS.getStatus()).build();
    }

    public AccountDto getAccountByAccountNumber(String accountNumber) {

        ModelMapper modelMapper = new ModelMapper();

        Optional<Accounts> accounts = accountRepository.findByAccountNumberAndStatusIsTrue(accountNumber);

        if (accounts.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        return modelMapper.map(accounts.get(), AccountDto.class);

    }

    public ResponseDataDto updateAccount(Long id, AccountDto updateAccount) throws IllegalAccessException {

        Optional<Accounts> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        Map<String, Object> nonNullFields = utils.getNonNullFields(updateAccount);

        for (Map.Entry<String, Object> entry : nonNullFields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            Field field = ReflectionUtils.findField(Accounts.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, account.get(), value);
            } else {
                throw new RuntimeException("Field not found");
            }
        };

        accountRepository.save(account.get());

        return ResponseDataDto.builder()
                .message(ResponseDataEnum.SUCCESS.getMessage())
                .code(ResponseDataEnum.SUCCESS.getCode())
                .status(ResponseDataEnum.SUCCESS.getStatus()).build();
    }

    public ResponseDataDto deleteAccount(Long id) throws IllegalAccessException {

        Optional<Accounts> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        updateAccount(id, AccountDto.builder().status(false).build());

        return ResponseDataDto.builder()
                .message(ResponseDataEnum.SUCCESS.getMessage())
                .code(ResponseDataEnum.SUCCESS.getCode())
                .status(ResponseDataEnum.SUCCESS.getStatus()).build();
    }

}
