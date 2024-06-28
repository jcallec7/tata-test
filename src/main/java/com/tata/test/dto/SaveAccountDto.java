package com.tata.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveAccountDto {

    private Long clientId;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;

}
