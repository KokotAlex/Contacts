package com.mycompany.contacts.client.mapper;

import com.mycompany.contacts.client.dto.ClientDto;
import com.mycompany.contacts.client.dto.NewClientDto;
import com.mycompany.contacts.client.model.Client;

public class ClientMapper {

    public static Client toClient(NewClientDto newClientDto) {
        return Client.builder()
                     .name(newClientDto.getName())
                     .build();
    }

    public static ClientDto toClientDto(Client client) {
        return ClientDto.builder()
                        .id(client.getId())
                        .name(client.getName())
                        .build();
    }

}
