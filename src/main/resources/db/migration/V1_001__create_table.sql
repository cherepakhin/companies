CREATE table if not exists company
(
    "id"   serial      not null UNIQUE,
    "short_name" varchar(50) not null default '',
    "full_name" varchar(150) not null default '',
    "inn" varchar(12) not null default '',
    "ogrn" varchar(15) not null default '',
    "post_address" varchar(100) not null default '',
    "real_address" varchar(100) not null default '',
    "director_id" integer not null default 0,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE table if not exists people
(
    "id"   serial      not null UNIQUE,
    "name" varchar(120) not null default '',
    CONSTRAINT people_pkey PRIMARY KEY (id)
);

alter table company add constraint director_fk foreign key (director_id) references people (id);

CREATE table if not exists filial
(
    "id"   serial      not null UNIQUE,
    "parent_id" integer not null,
    "filial_id" integer not null,
    CONSTRAINT filial_pkey PRIMARY KEY (id),
    CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES company (id),
    CONSTRAINT fk_filial_id FOREIGN KEY (filial_id) REFERENCES company (id)
);
