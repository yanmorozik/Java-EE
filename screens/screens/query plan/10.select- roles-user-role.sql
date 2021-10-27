select * from roles r 
inner join user_role ur on r.id = ur.role_id where r.name_role ='test'