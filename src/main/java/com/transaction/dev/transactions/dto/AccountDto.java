package com.transaction.dev.transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;

}