create table courses(
id bigint not null auto_increment,
category varchar(255),
name varchar(255),
primary key (id)
);

insert into courses values (null ,'Programming', 'Kotlin + Spring full course');
insert into courses values (null ,'Programming', 'Java + Spring full course');
insert into courses values (null ,'Programming', 'Java + JPA + Hibernate course');

create table users (
id bigint not null auto_increment,
name varchar(255) NOT NULL,
email varchar(255) NOT NULL,
primary key (id)
);

insert into users values (null, 'Bryan Gomes', 'bryan@email.com');
insert into users values (null, 'Julia Paz', 'juliap@email.com');
insert into users values (null, 'Guilherme Guerra', 'guiguer@email.com');
