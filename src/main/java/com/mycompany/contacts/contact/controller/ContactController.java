package com.mycompany.contacts.contact.controller;

import com.mycompany.contacts.contact.dto.ContactDto;
import com.mycompany.contacts.contact.dto.NewContactDto;
import com.mycompany.contacts.contact.mapper.ContactMapper;
import com.mycompany.contacts.contact.model.Contact;
import com.mycompany.contacts.contact.model.ContactType;
import com.mycompany.contacts.contact.service.ContactService;
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
public class ContactController {

    public final ContactService service;

    @PostMapping
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
