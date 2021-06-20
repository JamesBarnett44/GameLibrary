drop table if exists game CASCADE;
drop table if exists platform CASCADE;

create table game (
id integer not null auto_increment,
genre varchar(255),
name varchar(255),
progress varchar(255),
platform_id int,
primary key (id)
);

create table platform (
id integer not null auto_increment,
name varchar(255) not null,
primary key (id)
);

alter table game add constraint FKqbkf2002dfk9drnxmwxiqix6h foreign key (platform_id) references platform (id);