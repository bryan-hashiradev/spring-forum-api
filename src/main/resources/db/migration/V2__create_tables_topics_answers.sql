create table topics(
id bigint not null auto_increment,
title varchar(255) not null,
message text not null,
created_at datetime not null,
user_id bigint not null,
course_id bigint not null,
status enum('OPEN', 'DISCUSSION', 'CLOSED', 'SOLVED') default 'OPEN',

primary key (id),
foreign key (user_id) references users(id),
foreign key (course_id) references courses(id)
);

create table answers(
id bigint not null auto_increment,
message text not null,
user_id bigint not null,
topic_id bigint not null,
created_at datetime not null,
is_solution tinyint(1) not null default 0,

primary key (id),
foreign key (user_id) references users(id),
foreign key (topic_id) references topics(id)
)