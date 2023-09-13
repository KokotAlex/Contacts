# Contacts

## Тестовое задание
Спроектировать простую БД, обеспечивающую хранение информации о клиентах и их контактой информации.
Каждый клиент характеризуется именем. 
Каждому клиенту в соответствие может быть поставлена информация о его контактах: 0 и более телефонных номеров, 0 и более адресов электронной почты.
Разработать в Spring Framework API, обеспечивающее работу с данной БД.
API должно обеспечивать следующие функции:
1) Добавление нового клиента
2) Добавление нового контакта клиента (телефон или email)
3) Получение списка клиентов
4) Получение информации по заданному клиенту (по id)
5) Получение списка контактов заданного клиента
6) Получение списка контактов заданного типа заданного клиента
Реализованный проект с описанием ендпоинтов разаработанного API выложить на GIT и предоставить ссылку.

## Комментарии к решению
API можно посмотреть:
- В документации swagger, которую можно скачать [здесь](https://github.com/KokotAlex/Contacts/blob/main/api-spec.json)
- В Postman [здесь](https://github.com/KokotAlex/Contacts/blob/main/postman/ContactsAPI.postman_collection.json)

Дополнительно к основному заданию:
- Код проекта покрыт Unit тестами
- Реализовано интеграционное тестирование с использованием Postman
- Реализовать сборку проекта в Docker
