package com.tata.test.transactions.service.impl;

import com.tata.test.transactions.dto.SaveAccountDto;
import com.tata.test.transactions.entity.Accounts;
import com.tata.test.clients.entity.Clients;
import com.tata.test.enums.ResponseDataEnum;
import com.tata.test.transactions.repository.AccountRepository;
import com.tata.test.clients.repository.ClientRepository;
import com.tata.test.transactions.service.AccountService;
import com.tata.test.transactions.dto.AccountDto;
import com.tata.test.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static com.tata.test.utils.utils.getNonNullFields;

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

        Map<String, Object> nonNullFields = getNonNullFields(updateAccount);

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
