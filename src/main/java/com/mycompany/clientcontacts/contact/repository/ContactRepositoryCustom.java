package com.mycompany.clientcontacts.contact.repository;

import com.mycompany.clientcontacts.contact.model.Contact;
import com.mycompany.clientcontacts.contact.model.ContactType;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ContactRepositoryCustom {

    List<Contact> findClientsContacts(Long clientId, @Nullable ContactType type);

}
