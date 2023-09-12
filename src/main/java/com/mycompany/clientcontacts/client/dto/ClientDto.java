package com.mycompany.clientcontacts.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Schema(description = "Информация о полученном клиенте.")
public class ClientDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "ФИО")
    private String name;

}
