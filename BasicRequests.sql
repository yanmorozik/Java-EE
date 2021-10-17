https://www.pgexercises.com/questions/basic/


1) SELECT * FROM  cd.facilities ;


2) SELECT NAME, membercost  FROM cd.facilities;


3) SELECT * FROM CD.FACILITIES WHERE MEMBERCOST!=0


4) SELECT facid, name, membercost, monthlymaintenance FROM cd.facilities
WHERE (membercost*50)<monthlymaintenance AND membercost>0;


5) SELECT * FROM cd.facilities WHERE name LIKE '%Tennis%'


6) SELECT * FROM cd.facilities WHERE facid IN (1,5);


7) SELECT name,
CASE 
WHEN monthlymaintenance>100 THEN 'expensive'
WHEN monthlymaintenance<=100 THEN 'cheap'
END AS cost
FROM cd.facilities;


8) select memid, surname, firstname, joindate FROM cd.members
WHERE joindate>'2012-09-01';


9) SELECT DISTINCT surname FROM cd.members
ORDER BY surname LIMIT 10;


10) SELECT surname FROM cd.members
union
SELECT name FROM cd.facilities


11) select max(joindate) as latest
	from cd.members; 


12) SELECT firstname,surname,joindate FROM cd.members
WHERE joindate=(SELECT max(joindate) from cd.members);