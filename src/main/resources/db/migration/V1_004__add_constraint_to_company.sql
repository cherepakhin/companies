alter table company add CONSTRAINT fk_company_director FOREIGN KEY (director_n) REFERENCES employee (n);
