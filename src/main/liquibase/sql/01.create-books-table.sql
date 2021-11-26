create table books(
id bigserial primary key,
name_book varchar(64),
genre_id int8 references genres(id),
language_id int8 references languages(id),
number_of_page int,
year_of_publishing int,
number_of_copies int,
description text default 'No description' 
);