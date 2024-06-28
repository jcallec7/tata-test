package com.tata.test.transactions.controller;

import com.tata.test.transactions.dto.AccountDto;
import com.tata.test.transactions.dto.SaveAccountDto;
import com.tata.test.transactions.service.AccountService;
import com.tata.test.dto.ResponseDataDto;
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
