package com.mycompany.contacts.contact.service;

import com.mycompany.contacts.contact.model.Contact;
import com.mycompany.contacts.contact.model.ContactType;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ContactService {

    Contact save(Contact contact, Long clientId);

    List<Contact> getContacts(Long clientId, @Nullable ContactType type);

}
