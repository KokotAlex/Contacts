package com.mycompany.clientcontacts.contact.controller;

import com.mycompany.clientcontacts.contact.dto.ContactDto;
import com.mycompany.clientcontacts.contact.dto.NewContactDto;
import com.mycompany.clientcontacts.contact.mapper.ContactMapper;
import com.mycompany.clientcontacts.contact.model.Contact;
import com.mycompany.clientcontacts.contact.model.ContactType;
import com.mycompany.clientcontacts.contact.service.ContactService;
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
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/clients/{clientId}/contacts")
@Tag(name = "Контакты", description = "Методы для работы с контактной информацией клиентов")
public class ContactController {

    public final ContactService service;

    @PostMapping
    @Operation(summary = "Добавление контакта клиенту.")
    public ResponseEntity<ContactDto> addContact(
            @PathVariable @Positive Long clientId,
            @Valid @RequestBody NewContactDto newContactDto) {
        log.info("Processing a request to add new contact: {} for client: {}", newContactDto, clientId);

        Contact contact = ContactMapper.toContact(newContactDto);
        Contact savedContact = service.save(contact, clientId);
        ContactDto contactToReturn = ContactMapper.toContactDto(savedContact);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contactToReturn);
    }

    @GetMapping
    @Operation(summary = "Получение контактов клиента.")
    public ResponseEntity<List<ContactDto>> getContacts(
            @PathVariable @Positive Long clientId,
            @RequestParam(required = false) ContactType type) {
        log.info("Processing a request to finding contacts for client: {}", clientId);

        List<ContactDto> contacts = service.getContacts(clientId, type).stream()
                                           .map(ContactMapper::toContactDto)
                                           .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contacts);
    }

}
