package com.mycompany.contacts.contact.service;

import com.mycompany.contacts.client.model.Client;
import com.mycompany.contacts.client.repository.ClientRepository;
import com.mycompany.contacts.client.service.ClientService;
import com.mycompany.contacts.contact.model.Contact;
import com.mycompany.contacts.contact.model.ContactType;
import com.mycompany.contacts.contact.repository.ContactRepository;
import com.mycompany.contacts.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContactServiceImplTest {

    ContactRepository repository;
    ContactService service;
    ClientRepository clientRepository;
    ClientService clientService;

    @Captor
    ArgumentCaptor<Contact> captor;

    Client client;
    Contact contact;

    @BeforeEach
    void beforeEach() {
        captor = ArgumentCaptor.forClass(Contact.class);
        repository = mock(ContactRepository.class);
        clientRepository = mock(ClientRepository.class);
        clientService = mock(ClientService.class);
        service = new ContactServiceImpl(repository, clientRepository, clientService);
        client = Client.builder()
                       .id(1L)
                       .name("Александр")
                       .build();
        contact = Contact.builder()
                         .id(1L)
                         .type(ContactType.PHONE)
                         .description("Email@.ru")
                         .client(client)
                         .build();
    }

    @Test
    void save_whenSaveContactWithExactingClient_thenReturnContactTest() {

        final Contact parameterContact = Contact.builder()
                                             .type(contact.getType())
                                             .description(contact.getDescription())
                                             .build();
        when(clientService.getById(client.getId())).thenReturn(client);
        when(repository.save(any(Contact.class))).thenReturn(contact);

        Contact returnedContact = service.save(parameterContact, client.getId());

        // Получим модифицированное значение, которое будет сохраняться.
        verify(repository).save(captor.capture());
        final Contact savedContact = captor.getValue();

        // Проверим, что сохраняемый контакт соответствует ожидаемому.
        assertEquals(contact.getType(), savedContact.getType());
        assertEquals(contact.getDescription(), savedContact.getDescription());
        assertEquals(contact.getClient(), savedContact.getClient());

        // Проверим, что возвращаемое значение соответствует ожидаемому.
        assertEquals(contact.getId(), returnedContact.getId());
        assertEquals(contact.getType(), returnedContact.getType());
        assertEquals(contact.getDescription(), returnedContact.getDescription());
        assertEquals(contact.getClient(), returnedContact.getClient());

        // Проверим вызовы.
        InOrder inOrder = inOrder(clientService, repository);
        inOrder.verify(clientService, times(1))
               .getById(client.getId());
        inOrder.verify(repository, times(1))
               .save(any(Contact.class));
    }

    @Test
    void getContacts_whenClientExist_thenReturnCollectionContactsTest() {
        when(clientRepository.existsById(client.getId()))
                .thenReturn(true);
        when(repository.findClientsContacts(client.getId(), contact.getType()))
                .thenReturn(List.of(contact));

        List<Contact> foundContacts = service.getContacts(client.getId(), contact.getType());

        assertNotNull(foundContacts);
        assertEquals(1, foundContacts.size());
        assertEquals(contact, foundContacts.get(0));

        // Проверим вызовы.
        InOrder inOrder = inOrder(clientRepository, repository);
        inOrder.verify(clientRepository, times(1))
               .existsById(client.getId());
        inOrder.verify(repository, times(1))
               .findClientsContacts(client.getId(), contact.getType());
    }

    @Test
    void getContacts_whenClientIsNotExist_thenThrowNotFoundExceptionTest() {
        doReturn(false).when(clientRepository).existsById(any());

        assertThrows(NotFoundException.class,
                () -> service.getContacts(client.getId(), contact.getType()));

        verify(clientRepository, times(1))
                .existsById(any());
        verify(repository, never())
                .findClientsContacts(client.getId(), contact.getType());
    }

}