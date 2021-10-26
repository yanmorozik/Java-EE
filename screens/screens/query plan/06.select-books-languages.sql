select * from books b 
inner join languages l on b.language_id = l.id where b.name_book ='test'