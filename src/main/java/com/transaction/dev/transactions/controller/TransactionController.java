package com.transaction.dev.transactions.controller;

import com.transaction.dev.common.dto.ResponseDataDto;
import com.transaction.dev.transactions.dto.SaveTransactionDto;
import com.transaction.dev.transactions.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/movimientos")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/registrar")
    public ResponseEntity<ResponseDataDto> registerTransaction(@RequestBody @Valid SaveTransactionDto saveTransactionDto) {

        return ResponseEntity.ok(transactionService.registerTransaction(saveTransactionDto));

    }

    @GetMapping("/reportes")
    public ResponseEntity<?> getReport(@RequestParam(name = "FechaInicial") @DateTimeFormat LocalDate startDate,
                                       @RequestParam(name = "FechaFinal") @DateTimeFormat LocalDate endDate){

        return ResponseEntity.ok(transactionService.getReport(startDate,endDate));
    }

}
