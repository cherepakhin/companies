package ru.perm.v.companies.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDB {
    @Id
    private Long n = 0L;
    @Column(name = "firstname", columnDefinition = "varchar(255) default ''")
    private String firstname = "";
    @Column(name = "lastname", columnDefinition = "varchar(255) default ''")
    private String lastname = "";
    @Column(name = "fathername", columnDefinition = "varchar(255) default ''")
    private String fathername = "";
    @Column(name = "birthday", columnDefinition = "TIMESTAMP")
    private LocalDate birthday = LocalDate.now();

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleDB)) return false;
        PeopleDB peopleDB = (PeopleDB) o;
        return Objects.equals(n, peopleDB.n) && Objects.equals(firstname, peopleDB.firstname) && Objects.equals(lastname, peopleDB.lastname) && Objects.equals(fathername, peopleDB.fathername) && Objects.equals(birthday, peopleDB.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, firstname, lastname, fathername, birthday);
    }
}
