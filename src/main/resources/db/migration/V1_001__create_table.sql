CREATE table if not exists people
(
    "n"            serial not null UNIQUE,
    "firstname"     varchar(120) not null default '',
    "lastname"      varchar(120) not null default '',
    "fathername"    varchar(120) not null default '',
    "birthday"      timestamp not null default CURRENT_TIMESTAMP,
    CONSTRAINT people_pkey PRIMARY KEY (n)
);

-- default value for null director
insert into people(n) values (0);

CREATE table if not exists company
(
    "n"            serial not null UNIQUE,
    "shortname"    varchar(50) not null default '',
    "fullname"     varchar(150) not null default '',
    "inn"           varchar(12) not null default '',
    "ogrn"          varchar(15) not null default '',
    "address_post"  varchar(100) not null default '',
    "address_ur"  varchar(100) not null default '',
    "director_n"   integer not null default 0,
    CONSTRAINT company_pkey PRIMARY KEY (n),
    CONSTRAINT fk_company_director FOREIGN KEY (director_n) REFERENCES people (n)
);

-- default value for company with no filial
insert into company(n) values (0);

CREATE table if not exists filial
(
    "n"            serial not null UNIQUE,
    "filial_n"     integer not null,
    "parent_n"     integer not null,
    CONSTRAINT filial_pkey PRIMARY KEY (n),
    CONSTRAINT fk_filial_n FOREIGN KEY (filial_n) REFERENCES company (n),
    CONSTRAINT fk_parent_n FOREIGN KEY (parent_n) REFERENCES company (n)
);
