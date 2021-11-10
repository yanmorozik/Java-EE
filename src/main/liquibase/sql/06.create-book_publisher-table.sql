create table book_publisher(
publisher_id int references publishers(id),
book_id int references  books(id)
);