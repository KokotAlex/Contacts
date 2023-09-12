package com.mycompany.clientcontacts.contact.repository;

import com.mycompany.clientcontacts.contact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContactRepository extends
        JpaRepository<Contact, Long>,
        QuerydslPredicateExecutor<Contact>,
        ContactRepositoryCustom {
}
