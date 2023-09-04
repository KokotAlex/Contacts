package com.mycompany.contacts.client.service;

import com.mycompany.contacts.client.model.Client;
import com.mycompany.contacts.client.repository.ClientRepository;
import com.mycompany.contacts.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    ClientRepository repository;
    ClientService service;

    Client client;

    @BeforeEach
    void beforeEach() {
        repository = mock(ClientRepository.class);
        service = new ClientServiceImpl(repository);
        client = Client.builder()
                       .id(1L)
                       .name("Александр")
                       .build();
    }

    @Test
    void save_whenSaveClient_thenReturnClientTest() {
        Client unSavedClient = Client.builder()
                                     .name(client.getName())
                                     .build();
        when(repository.save(unSavedClient)).thenReturn(client);

        Client savedClient = service.save(unSavedClient);

        assertEquals(client.getId(), savedClient.getId());
        assertEquals(client.getName(), savedClient.getName());

        verify(repository, times(1))
                .save(unSavedClient);
    }

    @Test
    void getById_whenClientIsPresent_thenReturnClientTest() {
        when(repository.findById(client.getId()))
                .thenReturn(Optional.of(client));

        Client foundClient = service.getById(client.getId());

        assertEquals(client.getId(), foundClient.getId());
        assertEquals(client.getName(), foundClient.getName());

        verify(repository, times(1))
                .findById(client.getId());
    }

    @Test
    void getById_whenClientIsEmpty_thenThrowNotFoundException() {
        final long nonExistentId = 10L;

        when(repository.findById(nonExistentId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(nonExistentId));

        verify(repository, times(1))
                .findById(nonExistentId);
    }

    @Test
    void getAll_whenGettingClients_thenReturnCollectionClients() {
        Page<Client> clientPage = new PageImpl<>(List.of(client));

        when(repository.findAll(any(Pageable.class))).thenReturn(clientPage);

        List<Client> foundClients = service.getAll(0, 20);

        assertNotNull(foundClients);
        assertEquals(1, foundClients.size());
        assertEquals(client, foundClients.get(0));

        verify(repository, times(1))
                .findAll(any(Pageable.class));
    }

}