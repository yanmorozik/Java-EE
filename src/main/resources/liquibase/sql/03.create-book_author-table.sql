create table book_author(
author_id int8 references authors(id),
book_id int8 references  books(id)
);