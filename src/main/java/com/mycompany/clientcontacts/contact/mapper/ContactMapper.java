package com.mycompany.clientcontacts.contact.mapper;

import com.mycompany.clientcontacts.contact.dto.ContactDto;
import com.mycompany.clientcontacts.contact.dto.NewContactDto;
import com.mycompany.clientcontacts.contact.model.Contact;

public class ContactMapper {

    public static Contact toContact(NewContactDto newContactDto) {
        return Contact.builder()
                      .type(newContactDto.getType())
                      .description(newContactDto.getDescription())
                      .build();
    }

    public static ContactDto toContactDto(Contact contact) {
        return ContactDto.builder()
                         .id(contact.getId())
                         .type(contact.getType())
                         .description(contact.getDescription())
                         .build();
    }

}
