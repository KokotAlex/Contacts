package com.mycompany.clientcontacts.client.mapper;

import com.mycompany.clientcontacts.client.dto.ClientDto;
import com.mycompany.clientcontacts.client.dto.NewClientDto;
import com.mycompany.clientcontacts.client.model.Client;

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
