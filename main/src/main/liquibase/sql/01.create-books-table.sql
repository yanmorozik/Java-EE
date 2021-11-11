create table books(
id serial primary key,
name_book varchar(64),
genre_id int references genres(id),
language_id int references languages(id),
number_of_page int,
year_of_publishing int,
number_of_copies int,
description text default 'No description' 
);