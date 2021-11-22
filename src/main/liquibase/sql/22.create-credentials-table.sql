create table credentials
(
    id         serial primary key,
    login            varchar(64),
    password         varchar(64),
    password_confirm varchar(64)
);