package com.mycompany.clientcontacts.contact.repository;

import com.mycompany.clientcontacts.client.model.Client;
import com.mycompany.clientcontacts.client.repository.ClientRepository;
import com.mycompany.clientcontacts.contact.model.Contact;
import com.mycompany.clientcontacts.contact.model.ContactType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class ContactRepositoryImplTest {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    ClientRepository clientRepository;

    Client client;
    Contact phoneContact;
    Contact emailContact;

    @BeforeEach
    void beforeEach() {
        client = clientRepository.save(Client.builder()
                                             .name("Александр")
                                             .build());
        phoneContact = contactRepository.save(Contact.builder()
                                                .type(ContactType.PHONE)
                                                .description("Email@.ru")
                                                .client(client)
                                                .build());
        emailContact = contactRepository.save(Contact.builder()
                                                     .type(ContactType.EMAIL)
                                                     .description("+79604562578")
                                                     .client(client)
                                                     .build());
    }

    @Test
    void findClientsContacts_whenYouNeedToFindAllTheContacts_thenReturnCollectionWithAllContactsForClientTest() {
        final List<Contact> byClient = contactRepository.findClientsContacts(client.getId(), null);

        assertNotNull(byClient);
        assertEquals(2, byClient.size());
        assertEquals(byClient.get(0), phoneContact);
        assertEquals(byClient.get(1), emailContact);
    }

    @Test
    void findClientsContacts_whenYouNeedToFindOnlyEmails_thenReturnCollectionWithEmailsForClientTest() {
        final List<Contact> byClient = contactRepository.findClientsContacts(client.getId(), ContactType.EMAIL);

        assertNotNull(byClient);
        assertEquals(1, byClient.size());
        assertEquals(byClient.get(0), emailContact);
    }

}