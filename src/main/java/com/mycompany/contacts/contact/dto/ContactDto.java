package com.mycompany.contacts.contact.dto;

import com.mycompany.contacts.contact.model.ContactType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private Long id;
    private ContactType type;
    private String description;

}
