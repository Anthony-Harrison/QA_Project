drop table if exists movies CASCADE;
create table movies
 (
 	id integer primary key auto_increment,
 	title VARCHAR(255),
 	runtime int
 );