select * from users u 
inner join booking b on u.id = b.user_id where u.first_name ='test'