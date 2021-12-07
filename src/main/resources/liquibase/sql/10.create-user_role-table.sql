create table user_role(
role_id int8 references roles(id),
user_id int8 references users(id)
);