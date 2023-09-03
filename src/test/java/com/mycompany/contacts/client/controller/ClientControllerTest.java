package com.mycompany.contacts.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.contacts.client.dto.ClientDto;
import com.mycompany.contacts.client.dto.NewClientDto;
import com.mycompany.contacts.client.model.Client;
import com.mycompany.contacts.client.service.ClientService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
class ClientControllerTest {

    @MockBean
    ClientService service;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    Client client;
    NewClientDto newClientDto;
    ClientDto clientDto;

    @BeforeEach
    void beforeEach() {
        client = Client.builder()
                       .id(1L)
                       .name("Александр")
                       .build();
        newClientDto = NewClientDto.builder()
                                   .name(client.getName())
                                   .build();
        clientDto = ClientDto.builder()
                             .id(client.getId())
                             .name(client.getName())
                             .build();
    }

    @Test
    void addClient_whenInvoked_thenResponseStatusCreatedWithClientDtoInBodyTest() throws Exception {
        when(service.save(any(Client.class)))
                .thenReturn(client);

        mockMvc.perform(post("/clients")
                       .content(mapper.writeValueAsString(newClientDto))
                       .characterEncoding(StandardCharsets.UTF_8)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id", is(clientDto.getId()), Long.class))
               .andExpect(jsonPath("$.name", is(clientDto.getName())));

        verify(service, times(1))
                .save(any(Client.class));
    }

    @Test
    void addClient_whenContentIsNotCorrect_thenResponseStatus400Test() throws Exception {
        mockMvc.perform(post("/clients")
                       .characterEncoding(StandardCharsets.UTF_8)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());

        verify(service, never()).save(any());
    }

    @Test
    void getClient_whenInvoked_thenResponseStatusOkWithCategoryDtoInBodyTest() throws Exception {
        when(service.getById(client.getId())).thenReturn(client);

        mockMvc.perform(get("/clients/{clientId}", client.getId())
                       .characterEncoding(StandardCharsets.UTF_8)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(clientDto.getId()), Long.class))
               .andExpect(jsonPath("$.name", is(clientDto.getName())));

        verify(service, times(1)).getById(client.getId());
    }

    @Test
    void getClients_whenInvoked_thenResponseStatusOkWithClientDtoCollectionInBodyTest() throws Exception {

        when(service.getAll(0, 20)).thenReturn(List.of(client));

        mockMvc.perform(get("/clients")
                       .param("from", mapper.writeValueAsString(0))
                       .param("size", mapper.writeValueAsString(20))
                       .characterEncoding(StandardCharsets.UTF_8)
                       .contentType(MediaType.APPLICATION_JSON)
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id", is(client.getId()), Long.class))
               .andExpect(jsonPath("$[0].name", is(client.getName())))
               .andExpect(jsonPath("$", hasSize(1)));

        verify(service, times(1)).getAll(0, 20);
    }

}