package com.tata.test.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaveAccountDto {

    private Long clientId;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;

}
