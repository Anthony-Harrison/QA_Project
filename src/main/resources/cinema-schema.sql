drop table if exists cinema CASCADE;
create table cinema
 (
 	id integer primary key auto_increment,
 	branch varchar(255),
 	no_of_screens int
 );