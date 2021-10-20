/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  SANTIAGO SIERRA
 * Created: 16/10/2021
 */

CREATE TABLE Employee  (
    employee_num int auto_increment,
    employee_name varchar(30) NOT NULL,
    primary key(employee_num)
);

create  table Passenger (
	passenger_id int NOT NULL,
    passenger_name varchar(30) NOT NULL,
    primary key (passenger_id)
);

create table bus (
	bus_id int auto_increment,
    seat_capacity int not null,
    primary key (bus_id)
);

create table city (
	city_name varchar(20) not null,
    primary key (city_name)
);

create table luggage (
	luggage_id int auto_increment,
    luggage_status varchar(10) default 'OK',
    passenger_id_fk int not null,
    primary key (luggage_id),
    foreign key(passenger_id_fk) references passenger(passenger_id)
);


create table trip (
    trip_id int auto_increment,
    trip_date date not null,
    price int not null,
    origin_city_fk varchar(20) not null,
    destiny_city_fk varchar(20) not null,
    employee_num_fk int not null,
    bus_id_fk int not null,
    primary key(trip_id),
    foreign key(origin_city_fk) references city(city_name),
    foreign key(destiny_city_fk) references city(city_name),
    foreign key(employee_num_fk) references employee(employee_num),
    foreign key(bus_id_fk) references bus(bus_id)
);


create table reservation (
	reservation_num int auto_increment,
    passenger_id_fk int not null,
    trip_id_fk int not null,
    primary key (reservation_num),
    foreign key(passenger_id_fk) references passenger(passenger_id),
    foreign key(trip_id_fk) references trip(trip_id)
);

