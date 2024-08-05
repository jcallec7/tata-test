package com.transaction.dev.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionReportDto {

    private String clientName;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private String transactionType;
    private BigDecimal amount;
}
