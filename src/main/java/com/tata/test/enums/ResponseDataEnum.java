package com.tata.test.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseDataEnum {

    SUCCESS("Success", "200", HttpStatus.OK),
    FAILURE("Not found", "404", HttpStatus.NOT_FOUND);

    private final String message;
    private final String code;
    private final HttpStatus status;

}
