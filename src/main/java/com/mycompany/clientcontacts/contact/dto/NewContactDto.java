package com.mycompany.clientcontacts.contact.dto;

import com.mycompany.clientcontacts.contact.model.ContactType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о новой контактной информации")
public class NewContactDto {

    @NotNull
    @Schema(description = "Тип контактной информации")
    private ContactType type;

    @NotBlank
    @Size(max = 100)
    @Schema(description = "Значение")
    private String description;

}
