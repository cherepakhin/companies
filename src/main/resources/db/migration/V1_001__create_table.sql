CREATE table if not exists people
(
    "id"            serial not null UNIQUE,
    "firstname"     varchar(120) not null default '',
    "lastname"      varchar(120) not null default '',
    "fathername"    varchar(120) not null default '',
    "birthday"         timestamp not null default CURRENT_TIMESTAMP,
    CONSTRAINT people_pkey PRIMARY KEY (id)
);

-- default value for null director
insert into people(id) values (0);

CREATE table if not exists company
(
    "id"            serial not null UNIQUE,
    "short_name"    varchar(50) not null default '',
    "full_name"     varchar(150) not null default '',
    "inn"           varchar(12) not null default '',
    "ogrn"          varchar(15) not null default '',
    "post_address"  varchar(100) not null default '',
    "real_address"  varchar(100) not null default '',
    "director_id"   integer not null default 0,
    CONSTRAINT company_pkey PRIMARY KEY (id),
    CONSTRAINT fk_company_director FOREIGN KEY (director_id) REFERENCES people (id)
);

-- default value for company with no filial
insert into company(id) values (0);

CREATE table if not exists filial
(
    "id"            serial not null UNIQUE,
    "filial_id"     integer not null,
    "parent_id"     integer not null,
    CONSTRAINT filial_pkey PRIMARY KEY (id),
    CONSTRAINT fk_filial_id FOREIGN KEY (filial_id) REFERENCES company (id),
    CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES company (id)
);
