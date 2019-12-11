create table hibernate_sequence (
next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

create table city (
id bigint not null,
name varchar(255),
primary key (id)
) engine=InnoDB;

create table info (
id bigint not null,
info_message varchar(255),
city_id bigint,
primary key (id)
) engine=InnoDB;

alter table info
add constraint info_city_fk
foreign key (city_id) references city (id)