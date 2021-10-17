https://www.pgexercises.com/questions/aggregates/ 


1) SELECT count(facid) From cd.facilities;


2) SELECT COUNT(*) FROM cd.facilities 
WHERE guestcost>10;


3) select recommendedby, count(*) 
FROM cd.members
where recommendedby is not null
GROUP BY recommendedby
ORDER BY recommendedby;


4) SELECT facid, sum(slots)
FROM cd.bookings
GROUP BY facid
ORDER BY facid;


5) SELECT facid, sum(slots) AS "Total slots"
FROM cd.bookings
WHERE cd.bookings.starttime>='2012-09-01' AND cd.bookings.starttime<'2012-10-01'
GROUP BY facid
ORDER BY "Total slots"


6) SELECT facid,extract(month from starttime) as month,sum(slots) 
FROM cd.bookings
WHERE starttime>='2012-01-01' AND starttime<'2013-01-01'
GROUP BY facid, month
ORDER BY facid, month


7) SELECT COUNT(DISTINCT memid) FROM cd.bookings
WHERE starttime is not null


8)SELECT facid, SUM(slots) as "Total Slots" 
FROM cd.bookings
GROUP BY facid
HAVING SUM(slots)>1000
ORDER BY facid


9) select facs.name, sum(slots * case
			when memid = 0 then facs.guestcost
			else facs.membercost
		end) as revenue
	from cd.bookings bks
	inner join cd.facilities facs
		on bks.facid = facs.facid
	group by facs.name
order by revenue;         


10) select facs.name, sum(slots * case
			when memid = 0 then facs.guestcost
			else facs.membercost
		end) as revenue
	from cd.bookings bks
	inner join cd.facilities facs
		on bks.facid = facs.facid
	group by facs.name
	having sum(slots * case
			when memid = 0 then facs.guestcost
			else facs.membercost
		end)<1000
order by revenue;         


11) select facid, sum(slots) as totalslots
	from cd.bookings
	group by facid
	having sum(slots) = (select max(sum2.totalslots) from
		(select sum(slots) as totalslots
		from cd.bookings
		group by facid
		) as sum2);


12) select facid, extract(month from starttime) as month, sum(slots) as slots
	from cd.bookings
	where
		starttime >= '2012-01-01'
		and starttime < '2013-01-01'
	group by rollup(facid, month)
order by facid, month;    


13) SELECT cd.bookings.facid, cd.facilities.name,
trim(to_char(sum(cd.bookings.slots)/2.0, '999.99')) as "Total Hours"
FROM cd.bookings
INNER JOIN cd.facilities 
ON cd.facilities.facid=cd.bookings.facid
GROUP BY cd.bookings.facid, cd.facilities.name
ORDER BY cd.bookings.facid


14) SELECT mem.surname, mem.firstname, mem.memid, min(bks.starttime) FROM cd.members mem
INNER JOIN cd.bookings bks
ON mem.memid=bks.memid
WHERE bks.starttime>='2012-09-01'
GROUP BY mem.surname, mem.firstname, mem.memid
ORDER BY mem.memid


15) select (select count(*) from cd.members) as count, firstname, surname
	from cd.members
order by joindate


16) SELECT  row_number() over(order by joindate), firstname, surname FROM cd.members
ORDER BY joindate;


17) select facid, sum(slots) as totalslots
	from cd.bookings
	group by facid
	having sum(slots) = (select max(sum2.totalslots) from
		(select sum(slots) as totalslots
		from cd.bookings
		group by facid
		) as sum2);


18) select firstname, surname,
	((sum(bks.slots)+10)/20)*10 as hours,
	rank() over (order by ((sum(bks.slots)+10)/20)*10 desc) as rank

	from cd.bookings bks
	inner join cd.members mems
		on bks.memid = mems.memid
	group by mems.memid
order by rank, surname, firstname; 


19) select name, rank from (
	select facs.name as name, rank() over (order by sum(case
				when memid = 0 then slots * facs.guestcost
				else slots * membercost
			end) desc) as rank
		from cd.bookings bks
		inner join cd.facilities facs
			on bks.facid = facs.facid
		group by facs.name
	) as subq
	where rank <= 3
order by rank;        


20) select name, case when class=1 then 'high'
		when class=2 then 'average'
		else 'low'
		end revenue
	from (
		select facs.name as name, ntile(3) over (order by sum(case
				when memid = 0 then slots * facs.guestcost
				else slots * membercost
			end) desc) as class
		from cd.bookings bks
		inner join cd.facilities facs
			on bks.facid = facs.facid
		group by facs.name
	) as subq
order by class, name; 


21) select 	facs.name as name,
	facs.initialoutlay/((sum(case
			when memid = 0 then slots * facs.guestcost
			else slots * membercost
		end)/3) - facs.monthlymaintenance) as months
	from cd.bookings bks
	inner join cd.facilities facs
		on bks.facid = facs.facid
	group by facs.facid
order by name;  


22) select 	dategen.date,
	(
		select sum(case
			when memid = 0 then slots * facs.guestcost
			else slots * membercost
		end) as rev

		from cd.bookings bks
		inner join cd.facilities facs
			on bks.facid = facs.facid
		where bks.starttime > dategen.date - interval '14 days'
			and bks.starttime < dategen.date + interval '1 day'
	)/15 as revenue
	from
	(
		select 	cast(generate_series(timestamp '2012-08-01',
			'2012-08-31','1 day') as date) as date
	)  as dategen
order by dategen.date; 