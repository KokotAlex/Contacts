package com.mycompany.contacts.client.service;

import com.mycompany.contacts.client.model.Client;

import java.util.List;

public interface ClientService {

    Client save(Client client);

    Client getById(Long clientId);

    List<Client> getAll(Integer from, Integer size);

}
