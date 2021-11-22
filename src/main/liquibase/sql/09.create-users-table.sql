create table users
(
    id            serial primary key,
    credential_id int references credentials (id),
    first_name    varchar(64),
    surname       varchar(64),
    telephone     varchar(64)
);