select * from books b 
inner join booking b2 on b.id = b2.book_id where b.name_book ='test'