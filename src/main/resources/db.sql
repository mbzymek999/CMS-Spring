create table roles
(
    id   int primary key auto_increment,
    name varchar(20)
);

CREATE table users
(
    id          BIGINT primary key auto_increment,
    username    varchar(100),
    email       varchar(100) not null,
    password    varchar(100) not null
);

CREATE table employees
(
    id          BIGINT primary key auto_increment,
    name    varchar(100),
    last_name    varchar(100),
    position varchar(100),
    phone    varchar(50),
    street    varchar(50),
    street_number    varchar(50),
    building_number    varchar(50),
    city    varchar(50),
    postcode    varchar(50),
    user_id         BIGINT not null,
    foreign key (user_id) references users (id)
);

CREATE table companies
(
    id          BIGINT primary key auto_increment,
    company_name    varchar(50),
    short_company_name    varchar(50),
    nip    varchar(10),
    regon    varchar(50),
    phone    varchar(50),
    street    varchar(50),
    street_number    varchar(50),
    building_number    varchar(50),
    city    varchar(50),
    postcode    varchar(50),
    province    varchar(50),
    country    varchar(50),
    additional_fields varchar(300),
    user_id         BIGINT not null,
    foreign key (user_id) references users (id)
);

create table user_roles
(
    id      int primary key auto_increment,
    user_id BIGINT not null,
    role_id int not null
);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_COMPANY');
INSERT INTO roles(name) VALUES('ROLE_EMPLOYEE');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');