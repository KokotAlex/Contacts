package com.mycompany.contacts.contact.repository;

import com.mycompany.contacts.contact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContactRepository extends
        JpaRepository<Contact, Long>,
        QuerydslPredicateExecutor<Contact>,
        ContactRepositoryCustom {
}
