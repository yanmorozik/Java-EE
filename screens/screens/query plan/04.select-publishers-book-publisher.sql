select * from publishers p 
inner join book_publisher bp on p.id =bp.publisher_id  where p.name_publisher ='ivan'