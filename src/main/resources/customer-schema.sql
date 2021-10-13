drop table if exists customer CASCADE;
create table customer
 (
 	id integer primary key auto_increment,
 	name VARCHAR(255),
 	monthly_pass BOOLEAN
 );