package com.mycompany.contacts.contact.mapper;

import com.mycompany.contacts.contact.dto.ContactDto;
import com.mycompany.contacts.contact.dto.NewContactDto;
import com.mycompany.contacts.contact.model.Contact;

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
