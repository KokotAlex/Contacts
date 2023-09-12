package com.mycompany.clientcontacts.client.service;

import com.mycompany.clientcontacts.client.model.Client;
import com.mycompany.clientcontacts.client.repository.ClientRepository;
import com.mycompany.clientcontacts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    public final ClientRepository repository;

    @Override
    @Transactional
    public Client save(Client client) {
        log.info("Start saving client {}", client);

        return repository.save(client);
    }

    @Override
    public Client getById(Long clientId) {
        log.info("Start getting client by id: {}", clientId);

        return repository
                .findById(clientId)
                .orElseThrow(() -> new NotFoundException(format("Not found client with id %d", clientId)));
    }

    @Override
    public List<Client> getAll(Integer from, Integer size) {
        log.info("Start finding clients from: {}, size: {}", from, size);

        // Пусть категории возвращаются в алфавитном порядке.
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        // Создадим page.
        Pageable page = PageRequest.of(from, size, sortByName);
        // Выполним запрос.
        Page<Client> clientPage = repository.findAll(page);

        return clientPage.getContent();
    }

}
