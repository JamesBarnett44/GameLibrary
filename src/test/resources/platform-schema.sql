drop table if exists platform CASCADE;

create table platform (
id integer not null auto_increment,
name varchar(255) not null,
primary key (id)
);

