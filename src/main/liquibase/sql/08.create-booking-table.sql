create table booking(
id bigserial primary key,
book_id int8 references books(id),
user_id int8 references users(id),
start_time timestamp without time zone,
end_time timestamp without time zone
);