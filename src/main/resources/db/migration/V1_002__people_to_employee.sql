CREATE table if not exists employee
(
    "n"            serial not null UNIQUE,
    "firstname"     varchar(120) not null default '',
    "lastname"      varchar(120) not null default '',
    "fathername"    varchar(120) not null default '',
    "birthday"      timestamp not null default CURRENT_TIMESTAMP,
    CONSTRAINT employee_pkey PRIMARY KEY (n)
);

insert into employee("n","firstname","lastname","fathername","birthday") select * from people;
