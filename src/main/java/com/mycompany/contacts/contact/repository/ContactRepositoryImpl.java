package com.mycompany.contacts.contact.repository;

import com.mycompany.contacts.contact.model.Contact;
import com.mycompany.contacts.contact.model.ContactType;
import com.mycompany.contacts.contact.model.QContact;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepositoryCustom {

    public final EntityManager entityManager;

    @Override
    public List<Contact> findClientsContacts(Long clientId, @Nullable ContactType type) {
        QContact qContact = QContact.contact;

        // Сформируем фильтр для запроса.
        List<BooleanExpression> conditions = new ArrayList<>();
        conditions.add(qContact.client.id.eq(clientId));

        if (type != null) {
            conditions.add(qContact.type.eq(type));
        }

        BooleanExpression filter = conditions.stream().reduce(BooleanExpression::and).get();

        // Создадим и выполним запрос.
        JPAQuery<Contact> query = new JPAQuery<>(entityManager);

        return query.select(qContact)
                    .from(qContact)
                    .where(filter)
                    .orderBy(qContact.id.asc())
                    .fetch();
    }

}
