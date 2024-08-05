package com.transaction.dev.transactions.controller;

import com.transaction.dev.transactions.dto.AccountDto;
import com.transaction.dev.transactions.dto.SaveAccountDto;
import com.transaction.dev.transactions.service.AccountService;
import com.transaction.dev.common.dto.ResponseDataDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cuentas")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseDataDto> createAccount(@RequestBody @Valid SaveAccountDto saveAccountDto) {

        return ResponseEntity.ok(accountService.createAccount(saveAccountDto));

    }

    @GetMapping("/obtenerPorNumeroCuenta/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountByAccountNumber(@PathVariable String accountNumber) {

        return ResponseEntity.ok(accountService.getAccountByAccountNumber(accountNumber));

    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDataDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto updateAccountDto) throws IllegalAccessException {

        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDataDto> deleteAccount(@PathVariable Long id) throws IllegalAccessException {

        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
}
