# Bot

bot name=GoncharovCityInfoBot
bot token=847095326:AAGrRANemOLPpXulSlBL5fUF_vScFUOqD1o

Application uses MySql database.
spring.datasource.url=jdbc:mysql://localhost:3306/bot

# Flyway migration files: 

## V1__Init_DB:
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
 
## V2__Start_Values:
 
insert into city (id, name) values (1, 'Минск');
 
insert into city (id, name) values (2, 'Москва');
 
insert into city (id, name) values (3, 'Лондон');
 
insert into city (id, name) values (4, 'Берлин');
 
insert into info (id, info_message, city_id)
             values (1, 'Вероятнее всего, своим названием Минск обязан речке Мене, что означает «мелкая» или «меньшая»', 1);
 
insert into info (id, info_message, city_id)
             values (2, 'Общая площадь цветочных клумб Минска сопоставима по размеру с 10 футбольными стадионами', 1);
 
insert into info (id, info_message, city_id)
             values (3, 'В Москве находится самый крупный зоопарк в России. В нем можно увидеть более 550 видов животных со всего мира', 2);
 
insert into info (id, info_message, city_id)
             values (4, 'Многие города мира могут позавидовать Москве по количеству парков. Старейший из них – Александровский сад – обустроен в XVIII веке', 2);
 
insert into info (id, info_message, city_id)
             values (5, 'Точный возраст Лондона неизвестен, но, судя по найденным летописям, ему около двух тысяч лет', 3);
 
insert into info (id, info_message, city_id)
             values (6, 'Музеев в Берлине больше, чем дождливых дней', 4);
             
# REST City:

***get all***
GET /city/all

***get by name***
POST /city

    {
	    "name": "Минск"
    }

***add city***
POST /city/add

    {    
	  "name": "Минск"	 
    }

***delete city***
DELETE /city

    {
	    "id": 3
    }

***update city***
PUT /city

    {
	    "id": 3,
	    "name": "Краков"
    }


# REST Info

***get information about the city***
GET /info

    {
	    "name": "Минск"
    }

***get information about all cities***
GET /info/all

***get information by id***
POST /info

    {
	    "id": 1
    }

***add new information***POST /info/add

    {
	    "infoMessage": "new information",
	    "city": {
		"id": 2
	    }
    }

***delete information***
DELETE /info/delete

    {
	    "id": 1
    }

***update information***
PUT /info

    {
        "id": 1,
	    "infoMessage": "some information",
	    "city": {
		"id": 2
	    }
    }




