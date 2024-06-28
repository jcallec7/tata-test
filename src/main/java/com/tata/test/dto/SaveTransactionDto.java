package com.tata.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaveTransactionDto {

    private String transactionType;
    private BigDecimal amount;
    private Long accountId;

}
