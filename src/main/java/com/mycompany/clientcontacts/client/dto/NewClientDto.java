package com.mycompany.clientcontacts.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация о новом клиенте.")
public class NewClientDto {

    @NotBlank
    @Size(max = 100)
    @Schema(description = "ФИО")
    private String name;
}
