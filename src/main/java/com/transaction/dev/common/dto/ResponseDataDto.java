package com.transaction.dev.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ResponseDataDto {

    private String message;
    private String code;
    private HttpStatus status;

}
