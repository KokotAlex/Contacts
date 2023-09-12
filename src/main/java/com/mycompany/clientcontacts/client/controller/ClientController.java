package com.mycompany.clientcontacts.client.controller;

import com.mycompany.clientcontacts.client.dto.ClientDto;
import com.mycompany.clientcontacts.client.dto.NewClientDto;
import com.mycompany.clientcontacts.client.mapper.ClientMapper;
import com.mycompany.clientcontacts.client.model.Client;
import com.mycompany.clientcontacts.client.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
@Tag(name = "Клиенты", description = "Методы для работы с клиентами")
public class ClientController {

    public final ClientService service;

    @PostMapping
    @Operation(summary = "Добавление нового клиента.")
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody NewClientDto newClientDto) {
        log.info("Processing a request to add a client with a name: {}", newClientDto.getName());

        Client client = ClientMapper.toClient(newClientDto);
        Client savedClient = service.save(client);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ClientMapper.toClientDto(savedClient));
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Получение данных клиента по его идентификатору")
    public ResponseEntity<ClientDto> getClient(@PathVariable @Positive Long clientId) {
        log.info("Processing a request to get client with id: {}", clientId);

        ClientDto clientDto = ClientMapper.toClientDto(service.getById(clientId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientDto);
    }

    @GetMapping
    @Operation(summary = "Получение постраничного списка клиентов.")
    public ResponseEntity<List<ClientDto>> getClients(
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Processing a request to finding clients from: {}, size: {}", from, size);

        List<ClientDto> clients = service.getAll(from, size).stream()
                                              .map(ClientMapper::toClientDto)
                                              .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clients);
    }

}
