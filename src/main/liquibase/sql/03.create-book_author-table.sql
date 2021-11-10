create table book_author(
author_id int references authors(id),
book_id int references  books(id)
);