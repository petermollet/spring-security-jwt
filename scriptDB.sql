create table users (
  id serial primary key,
  username varchar(255) not null unique,
  password varchar(255) not null
);

create sequence users_seq start with 10;

INSERT INTO public.users
(id, username, "password")
values (1, 'user', '$2a$10$z1AriUReCJhq2LUdP3SgGOFQ4Pqp.awx5rjkN46yfWMBN3U0GaNba')
,(2, 'admin', '$2a$10$z1AriUReCJhq2LUdP3SgGOFQ4Pqp.awx5rjkN46yfWMBN3U0GaNba');

create table authority (
  name varchar(50) primary key not null unique
);

INSERT INTO public.authority
("name")
VALUES('ROLE_ADMIN'),('ROLE_USER');


create table users_authority (
	user_id bigint not null,
	authority_name varchar(50) not null,
	constraint user_id foreign key(user_id) references users(id),
	constraint authority_name foreign key(authority_name) references authority(name)
);

INSERT INTO public.users_authority
(user_id, authority_name)
VALUES(1, 'ROLE_USER'),(2, 'ROLE_ADMIN');