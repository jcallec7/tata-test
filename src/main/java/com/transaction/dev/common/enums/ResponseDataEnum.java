package com.transaction.dev.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseDataEnum {

    SUCCESS("Success", "200", HttpStatus.OK),
    NOT_FOUND("Not found", "404", HttpStatus.NOT_FOUND),
    INSUFFICIENT_FUNDS("Saldo insuficiente", "400", HttpStatus.BAD_REQUEST);

    private final String message;
    private final String code;
    private final HttpStatus status;

}
