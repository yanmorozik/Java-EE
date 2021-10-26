select * from books b 
inner join book_publisher bp on b.id = bp.book_id where b.name_book ='TEST'