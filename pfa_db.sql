emp_ID integer not null primary key auto_increment,
emp_phone text,
emp_birthdate text,
emp_hiredate text,
emp_name text,
emp_salary integer,
emp_certdate text
);

create table CUSTOMER (
customer_ID integer not null primary key auto_increment,
customer_name text,
customer_birthdate text,
customer_phone text
);

create table RIDE (
ride_ID integer not null primary key auto_increment,
ride_location text,
ride_duration integer,
ride_datetime text,
ride_price double
);

create table PAYSFOR (
paysfor_ID integer not null primary key auto_increment,
ride_ID integer,
customer_ID integer,
foreign key (ride_ID) references RIDE (ride_ID),
foreign key (customer_ID) references CUSTOMER (customer_ID)
 on delete cascade
on update no action
);

create table OPERATES (
operates_ID integer not null primary key auto_increment,
ride_ID integer,
emp_ID integer,
foreign key (ride_ID) references RIDE (ride_ID),
foreign key (emp_ID) references EMPLOYEE (emp_ID)
 on delete cascade
on update no action
);