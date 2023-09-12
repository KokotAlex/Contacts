package com.mycompany.clientcontacts.contact.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.clientcontacts.client.model.Client;
import com.mycompany.clientcontacts.contact.dto.ContactDto;
import com.mycompany.clientcontacts.contact.dto.NewContactDto;
import com.mycompany.clientcontacts.contact.model.Contact;
import com.mycompany.clientcontacts.contact.model.ContactType;
import com.mycompany.clientcontacts.contact.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
@AutoConfigureMockMvc
class ContactControllerTest {

    @MockBean
    ContactService service;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    Client client;
    Contact contact;
    NewContactDto newContactDto;
    ContactDto contactDto;

    @BeforeEach
    void beforeEach() {
        client = Client.builder()
                       .id(1L)
                       .name("Александр")
                       .build();
        contact = Contact.builder()
                         .id(1L)
                         .type(ContactType.EMAIL)
                         .description("Email@.ru")
                         .client(client)
                         .build();
        newContactDto = NewContactDto.builder()
                                     .type(contact.getType())
                                     .description(contact.getDescription())
                                     .build();
        contactDto = ContactDto.builder()
                               .id(contact.getId())
                               .type(contact.getType())
                               .description(contact.getDescription())
                               .build();
    }

    @Test
    void addContact_whenInvoked_thenResponseStatusCreatedWithContactDtoInBodyTest() throws Exception {
        when(service.save(any(), any()))
                .thenReturn(contact);

        mockMvc.perform(post("/clients/{clientId}/contacts", client.getId())
                       .content(mapper.writeValueAsString(newContactDto))
                       .characterEncoding(StandardCharsets.UTF_8)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id", is(contactDto.getId()), Long.class))
               .andExpect(jsonPath("$.type", is(contactDto.getType().toString())))
               .andExpect(jsonPath("$.description", is(contactDto.getDescription())));

        verify(service, times(1))
                .save(any(), any());
    }

    @Test
    void getContacts_whenInvoked_thenResponseStatusOkWithContactDtoCollectionInBodyTest() throws Exception {
        when(service.getContacts(eq(client.getId()), eq(contact.getType())))
                .thenReturn(List.of(contact));

        mockMvc.perform(get("/clients/{clientId}/contacts", client.getId())
                       .param("type", contact.getType().toString())
                       .characterEncoding(StandardCharsets.UTF_8)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id", is(contactDto.getId()), Long.class))
               .andExpect(jsonPath("$[0].type", is(contactDto.getType().toString())))
               .andExpect(jsonPath("$[0].description", is(contactDto.getDescription())))
               .andExpect(jsonPath("$", hasSize(1)));

        verify(service, times(1))
                .getContacts(eq(client.getId()), eq(contact.getType()));
    }

}