drop table if exists cinema CASCADE;
create table cinema
 (
 	id integer primary key auto_increment,
 	branch varchar(255),
 	no_of_screens int
 );
 
 drop table if exists staff CASCADE;
create table staff
 (
 	id integer primary key auto_increment,
 	name VARCHAR(255),
 	cinema_id integer,
 	FOREIGN KEY (cinema_id) REFERENCES cinema(id) ON DELETE CASCADE
 );
 
 drop table if exists movies CASCADE;
create table movies
 (
 	id integer primary key auto_increment,
 	title VARCHAR(255),
 	runtime int
 );
 
 drop table if exists customer CASCADE;
create table customer
 (
 	id integer primary key auto_increment,
 	name VARCHAR(255),
 	monthly_pass BOOLEAN
 );