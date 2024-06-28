package com.tata.test.transactions.controller;

import com.tata.test.dto.ResponseDataDto;
import com.tata.test.transactions.dto.SaveTransactionDto;
import com.tata.test.transactions.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movimientos")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/registrar")
    public ResponseEntity<ResponseDataDto> registerTransaction(@RequestBody @Valid SaveTransactionDto saveTransactionDto) {

        return ResponseEntity.ok(transactionService.registerTransaction(saveTransactionDto));

    }

}
