package com.tata.test.transactions.controller;

import com.tata.test.dto.ResponseDataDto;
import com.tata.test.transactions.dto.SaveTransactionDto;
import com.tata.test.transactions.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
