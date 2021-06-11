drop table if exists game CASCADE;

create table game (
id integer not null auto_increment,
genre varchar(255),
name varchar(255),
progress varchar(255),
primary key (id)
);