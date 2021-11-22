create table booking(
id serial primary key,
book_id int references books(id),
user_id int references users(id),
start_time timestamp without time zone,
end_time timestamp without time zone
);