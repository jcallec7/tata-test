package com.transaction.dev.transactions.service.impl;

import com.transaction.dev.common.dto.ResponseDataDto;
import com.transaction.dev.transactions.dto.SaveTransactionDto;
import com.transaction.dev.transactions.dto.TransactionReportDto;
import com.transaction.dev.transactions.entity.Accounts;
import com.transaction.dev.transactions.entity.Transactions;
import com.transaction.dev.common.enums.ResponseDataEnum;
import com.transaction.dev.transactions.repository.AccountRepository;
import com.transaction.dev.transactions.repository.TransactionRepository;
import com.transaction.dev.transactions.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    public ResponseDataDto registerTransaction(SaveTransactionDto saveTransactionDto) {

        Optional<Accounts> account = accountRepository.findById(saveTransactionDto.getAccountId());

        if (account.isEmpty()) {
            return ResponseDataDto.builder()
                    .message(ResponseDataEnum.NOT_FOUND.getMessage())
                    .code(ResponseDataEnum.NOT_FOUND.getCode())
                    .status(ResponseDataEnum.NOT_FOUND.getStatus()).build();
        }

        if (Objects.equals(saveTransactionDto.getTransactionType(), "Debito")) {

            if (account.get().getInitialBalance().compareTo(saveTransactionDto.getAmount()) < 0) {
                return ResponseDataDto.builder()
                        .message(ResponseDataEnum.INSUFFICIENT_FUNDS.getMessage())
                        .code(ResponseDataEnum.INSUFFICIENT_FUNDS.getCode())
                        .status(ResponseDataEnum.INSUFFICIENT_FUNDS.getStatus()).build();
            }

            account.get().setInitialBalance(account.get().getInitialBalance().subtract(saveTransactionDto.getAmount()));

        } else if (Objects.equals(saveTransactionDto.getTransactionType(), "Credito")) {

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

    public List<TransactionReportDto> getReport(LocalDate startDate, LocalDate endDate) {

        List<Transactions> transactionList = transactionRepository.findByDateBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));

        return transactionList.stream().map(transactions -> {
                    TransactionReportDto transactionReportDto = new TransactionReportDto();
                    transactionReportDto.setClientName(transactions.getAccount().getClient().getName());
                    transactionReportDto.setAccountNumber(transactions.getAccount().getAccountNumber());
                    transactionReportDto.setAccountType(transactions.getAccount().getAccountType());
                    transactionReportDto.setInitialBalance(transactions.getAccount().getInitialBalance());
                    transactionReportDto.setTransactionType(transactions.getTransactionType());
                    transactionReportDto.setAmount(transactions.getAmount());
                    return transactionReportDto;
                }
        ).toList();
    }
}
