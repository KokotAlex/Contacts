package com.mycompany.contacts.contact.repository;

import com.mycompany.contacts.contact.model.Contact;
import com.mycompany.contacts.contact.model.ContactType;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ContactRepositoryCustom {

    List<Contact> findClientsContacts(Long clientId, @Nullable ContactType type);

}
