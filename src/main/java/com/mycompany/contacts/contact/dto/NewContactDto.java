package com.mycompany.contacts.contact.dto;

import com.mycompany.contacts.contact.model.ContactType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewContactDto {

    @NotNull
    private ContactType type;

    @NotBlank
    @Size(max = 100)
    private String description;

}
