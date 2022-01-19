create table roles
(
    id   int primary key auto_increment,
    name varchar(20)
);

create table users
(
    id          BIGINT primary key auto_increment,
    id_client   varchar(100) not null,
    username    varchar(100) not null,
    email       varchar(100) not null,
    password    varchar(100) not null
);

create table user_roles
(
    id      int primary key auto_increment,
    user_id BIGINT not null,
    role_id int not null
);

create table employees
(
    id          BIGINT primary key auto_increment,
    name    varchar(100) not null,
    last_name    varchar(100) not null,
    pesel BIGINT not null,
    position varchar(100),
    phone    varchar(50) not null,
    street    varchar(50),
    street_number    varchar(50),
    building_number    varchar(50) not null,
    city    varchar(50) not null,
    postcode    varchar(50) not null,
    user_id         BIGINT not null,
    foreign key (user_id) references users (id)
);

create table companies
(
    id          BIGINT primary key auto_increment,
    company_name    varchar(50) not null,
    short_company_name    varchar(50),
    representative_person varchar (50),
    nip    varchar(10) not null,
    regon    varchar(50) not null,
    phone    varchar(50) not null,
    street    varchar(50),
    street_number    varchar(50),
    building_number    varchar(50) not null,
    city    varchar(50) not null,
    postcode    varchar(50) not null,
    province    varchar(50),
    country    varchar(50),
    additional_fields varchar(300),
    max_employees int not null,
    user_id         BIGINT not null,
    foreign key (user_id) references users (id)
);

create table tasks
(
    id int primary key auto_increment,
    name varchar(100) not null,
    type varchar(100),
    description varchar(100),
    status_task int,
    created_date DATE not null,
    date_to DATE not null,
    company_id BIGINT not null,
    employee_id BIGINT not null,
    foreign key (company_id) references companies (id),
    foreign key (employee_id) references employees (id)
);

create table agreements
(
    id int primary key auto_increment,
    agreement_type varchar(50),
    assigned_date DATE not null,
    date_from DATE not null,
    date_to DATE not null,
    salary double not null,
    bank_account varchar(50) not null,
    company_id BIGINT not null,
    user_id         BIGINT not null,
    employee_id         BIGINT not null,
    foreign key (company_id) references companies (id),
    foreign key (user_id) references users (id),
    foreign key (employee_id) references employees (id)
);

create table payments
(
    id int primary key auto_increment,
    date_payment date not null,
    term_payment date,
    price double not null,
    payment_done bit,
    company_id BIGINT not null,
    foreign key (company_id) references companies (id)
);

create table messages
(
    id int primary key auto_increment,
    id_client varchar(50) not null,
    company_name varchar(50),
    email varchar(50) not null,
    phone varchar(20),
    message varchar(1000) not null
);

insert into roles(name) values('ROLE_USER');
insert into roles(name) values('ROLE_COMPANY');
insert into roles(name) values('ROLE_EMPLOYEE');
insert into roles(name) values('ROLE_ADMIN');

drop table messages;
drop table agreements;
drop table roles;
drop table employees;
drop table companies;
drop table employees;
drop table user_roles;
drop table users;
drop table user_roles;
drop table tasks;
drop table payments;
