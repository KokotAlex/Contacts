package com.mycompany.clientcontacts.contact.dto;

import com.mycompany.clientcontacts.contact.model.ContactType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о полученной контактной информации")
public class ContactDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Тип")
    private ContactType type;
    @Schema(description = "Значение")
    private String description;

}
