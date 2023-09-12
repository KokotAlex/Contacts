package com.mycompany.clientcontacts.client.service;

import com.mycompany.clientcontacts.client.model.Client;

import java.util.List;

public interface ClientService {

    Client save(Client client);

    Client getById(Long clientId);

    List<Client> getAll(Integer from, Integer size);

}
