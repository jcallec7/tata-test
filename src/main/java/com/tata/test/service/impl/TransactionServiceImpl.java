package com.tata.test.service.impl;

import com.tata.test.dto.ResponseDataDto;
import com.tata.test.dto.SaveTransactionDto;
import com.tata.test.entity.Accounts;
import com.tata.test.entity.Transactions;
import com.tata.test.enums.ResponseDataEnum;
import com.tata.test.repository.AccountRepository;
import com.tata.test.repository.TransactionRepository;
import com.tata.test.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    public ResponseDataDto registerTransaction(SaveTransactionDto saveTransactionDto) {

        Optional<Accounts> account = accountRepository.findById(saveTransactionDto.getAccountId());

        if(account.isEmpty()) {
            return ResponseDataDto.builder()
                    .message(ResponseDataEnum.NOT_FOUND.getMessage())
                    .code(ResponseDataEnum.NOT_FOUND.getCode())
                    .status(ResponseDataEnum.NOT_FOUND.getStatus()).build();
        }

        if(Objects.equals(saveTransactionDto.getTransactionType(), "Debito")) {

            if (account.get().getInitialBalance().compareTo(saveTransactionDto.getAmount()) < 0) {
                return ResponseDataDto.builder()
                        .message(ResponseDataEnum.INSUFFICIENT_FUNDS.getMessage())
                        .code(ResponseDataEnum.INSUFFICIENT_FUNDS.getCode())
                        .status(ResponseDataEnum.INSUFFICIENT_FUNDS.getStatus()).build();
            }

            account.get().setInitialBalance(account.get().getInitialBalance().subtract(saveTransactionDto.getAmount()));

        }else if (Objects.equals(saveTransactionDto.getTransactionType(), "Credito")){

            account.get().setInitialBalance(account.get().getInitialBalance().add(saveTransactionDto.getAmount()));

        } else {
            return ResponseDataDto.builder()
                    .message("Invalid transaction type")
                    .code("400")
                    .status(HttpStatus.BAD_REQUEST).build();
        }

        accountRepository.save(account.get());

        Transactions transaction = new Transactions();
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(saveTransactionDto.getAmount());
        transaction.setTransactionType(saveTransactionDto.getTransactionType());
        transaction.setBalance(account.get().getInitialBalance());
        transaction.setAccount(account.get());

        transactionRepository.save(transaction);

        return ResponseDataDto.builder()
                .message(ResponseDataEnum.SUCCESS.getMessage())
                .code(ResponseDataEnum.SUCCESS.getCode())
                .status(ResponseDataEnum.SUCCESS.getStatus()).build();

    }

}
