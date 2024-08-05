package com.transaction.dev.clients.service;

import com.transaction.dev.clients.dto.ClientDto;
import com.transaction.dev.common.dto.ResponseDataDto;
import com.transaction.dev.clients.dto.SaveClientDto;

public interface ClientService {

    ResponseDataDto createClient(SaveClientDto saveClientDto);
    ClientDto getClientById(Long id);
    ResponseDataDto updateClient(Long id, ClientDto updateClientDto) throws IllegalAccessException;
    ResponseDataDto deleteClient(Long id) throws IllegalAccessException;
}
