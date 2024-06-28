package com.tata.test.controller;

import com.tata.test.dto.ClientDto;
import com.tata.test.dto.ResponseDataDto;
import com.tata.test.dto.SaveClientDto;
import com.tata.test.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/clientes")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/crear")
    public ResponseEntity<ResponseDataDto> createClient(@RequestBody @Valid SaveClientDto saveClientDto) {

        return ResponseEntity.ok(clientService.createClient(saveClientDto));

    }

    @GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {

        return ResponseEntity.ok(clientService.getClientById(id));

    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDataDto> updateClient(@PathVariable Long id, @RequestBody ClientDto updateClientDto) throws IllegalAccessException {

        return ResponseEntity.ok(clientService.updateClient(id, updateClientDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDataDto> deleteClient(@PathVariable Long id) throws IllegalAccessException {

        return ResponseEntity.ok(clientService.deleteClient(id));
    }


}
