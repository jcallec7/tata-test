package com.tata.test.service.impl;

import com.tata.test.dto.ClientDto;
import com.tata.test.dto.ResponseDataDto;
import com.tata.test.dto.SaveClientDto;
import com.tata.test.entity.Clients;
import com.tata.test.enums.ResponseDataEnum;
import com.tata.test.repository.ClientRepository;
import com.tata.test.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static com.tata.test.utils.utils.getNonNullFields;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ResponseDataDto createClient(SaveClientDto saveClientDto) {

        ModelMapper modelMapper = new ModelMapper();

        Clients clients = modelMapper.map(saveClientDto, Clients.class);

        clientRepository.save(clients);

        return ResponseDataDto.builder()
                .message(ResponseDataEnum.SUCCESS.getMessage())
                .code(ResponseDataEnum.SUCCESS.getCode())
                .status(ResponseDataEnum.SUCCESS.getStatus()).build();
    }

    public ClientDto getClientById(Long id) {

        ModelMapper modelMapper = new ModelMapper();

        Optional<Clients> clients = clientRepository.findByIdAndStatusIsTrue(id);

        if (clients.isEmpty()) {
            throw new RuntimeException("Client not found");
        }

        return modelMapper.map(clients.get(), ClientDto.class);

    }

    public ResponseDataDto updateClient(Long id, ClientDto updateClientDto) throws IllegalAccessException {

        Optional<Clients> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new RuntimeException("Client not found");
        }

        Map<String, Object> nonNullFields = getNonNullFields(updateClientDto);

        for (Map.Entry<String, Object> entry : nonNullFields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();


            Field field = ReflectionUtils.findField(Clients.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, client.get(), value);
            } else {
                throw new RuntimeException("Field not found");
            }
        };

        clientRepository.save(client.get());

        return ResponseDataDto.builder()
                .message(ResponseDataEnum.SUCCESS.getMessage())
                .code(ResponseDataEnum.SUCCESS.getCode())
                .status(ResponseDataEnum.SUCCESS.getStatus()).build();
    }

    public ResponseDataDto deleteClient(Long id) throws IllegalAccessException {

        Optional<Clients> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new RuntimeException("Client not found");
        }

        updateClient(id, ClientDto.builder().status(false).build());

        return ResponseDataDto.builder()
                .message(ResponseDataEnum.SUCCESS.getMessage())
                .code(ResponseDataEnum.SUCCESS.getCode())
                .status(ResponseDataEnum.SUCCESS.getStatus()).build();
    }


}
