select * from users u 
inner join user_role ur on u.id = ur.user_id where u.first_name ='test'