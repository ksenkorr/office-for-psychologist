create table users
(
    id serial primary key,
    first_name varchar(20) not null,
    middle_name varchar(20) not null,
    last_name(40) varchar not null,
    name_acronym varchar(10) not null unique,
    role_id integer not null,
    login varchar(20) not null unique,
    password varchar(20),

    foreign key (role_id) references roles(id)
);


create table roles
(
    id serial primary key,
    role_name varchar(30) not null unique
);

create table emotional_diaries
(
    id serial primary key,
    dairyTime timestamp not null,
    user_id integer not null,
    comment varchar(1000),

    foreign key (user_id) references users(id)
);


insert into roles (role_name)
values
    ('Пациент'),
    ('Психолог');


insert into users (first_name, middle_name, last_name, name_acronym, role_id, login, password)
values
    ('Иван', 'Иванович', 'Иванов', 'ИИИ' 1, 'ivanov', 'ivanov1'),
    ('Петр', 'Петрович', 'Петров', 'ППП',1, 'petrov', 'petrov1'),
    ('Василий', 'Васильевич', 'Васильев', 'ВВВ', 2, 'vasiliev', 'vasiliev1');

insert into emotional_diaries (dairytime, user_id, comment)
values
    ('2020-03-15 10:23:54', 1, 'text 11'),
    ('2020-03-16 12:10:05', 1, 'text 12'),
    ('2020-03-17 15:30:00', 1, 'text 13'),
    ('2020-03-17 17:50:15', 1, 'text 14'),

    ('2020-03-12 10:25:04', 2, 'text 21'),
    ('2020-03-13 14:10:34', 2, 'text 22'),
    ('2020-03-13 19:30:49', 2, 'text 23'),
    ('2020-03-17 14:10:12', 2, 'text 24');