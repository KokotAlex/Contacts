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
Используемый стек: Java, Spring Boot, Maven, Hibernate, SQL DSL, Lomboc, junit, Swagger, PostgreSQL, Docker, RESTful API, Postman

API можно посмотреть:
- В документации swagger, которую можно скачать [здесь](https://github.com/KokotAlex/Contacts/blob/main/api-spec.json). Веб версия документации после запуска приложения доступна по адресу: http://127.0.0.1:8080/swagger-ui/index.html
- В Postman [здесь](https://github.com/KokotAlex/Contacts/blob/main/Postman/ContactsAPI.json)

Дополнительно к основному заданию:
- Код проекта покрыт Unit тестами
- Реализовано интеграционное тестирование с использованием Postman
- Реализована сборка проекта в Docker

Структура БД:

Интерактивнуая структура БД содержится [здесь](https://dbdiagram.io/d/6501801302bd1c4a5e7b825d)
![client_contacts](https://github.com/KokotAlex/Contacts/assets/108023555/32779bb8-1e0b-4190-8a19-fe13f5216ef0)
