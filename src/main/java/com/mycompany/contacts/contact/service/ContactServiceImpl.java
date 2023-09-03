package com.mycompany.contacts.contact.service;

import com.mycompany.contacts.client.model.Client;
import com.mycompany.contacts.client.repository.ClientRepository;
import com.mycompany.contacts.client.service.ClientService;
import com.mycompany.contacts.contact.model.Contact;
import com.mycompany.contacts.contact.model.ContactType;
import com.mycompany.contacts.contact.repository.ContactRepository;
import com.mycompany.contacts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    public final ContactRepository repository;
    public final ClientRepository clientRepository;
    public final ClientService clientService;

    @Override
    @Transactional
    public Contact save(Contact contact, Long clientId) {
        log.info("Start saving contact: {} for client: {}", contact, clientId);

        // Найдем клиента.
        Client client = clientService.getById(clientId);

        // Дополним сохраняемый контакт данными клиента.
        contact.setClient(client);

        // Сохраним сформированный контакт.
        return repository.save(contact);
    }

    @Override
    public List<Contact> getContacts(Long clientId, @Nullable ContactType type) {
        log.info("Start getting contacts for client with id: {}", clientId);

        // Проверим, что клиент существует.
        if (!clientRepository.existsById(clientId)) {
            throw new NotFoundException(format("Client with id: %d is not exist", clientId));
        }

        // Найдем контакты клиента.
        return repository.findClientsContacts(clientId, type);
    }
}
