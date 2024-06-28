package com.tata.test.service;

import com.tata.test.dto.ClientDto;
import com.tata.test.dto.ResponseDataDto;
import com.tata.test.dto.SaveClientDto;

public interface ClientService {

    ResponseDataDto createClient(SaveClientDto saveClientDto);
    ClientDto getClientById(Long id);
    ResponseDataDto updateClient(Long id, ClientDto updateClientDto) throws IllegalAccessException;
    ResponseDataDto deleteClient(Long id) throws IllegalAccessException;
}
