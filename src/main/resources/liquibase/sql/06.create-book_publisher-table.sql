create table book_publisher(
publisher_id int8 references publishers(id),
book_id int8 references  books(id)
);