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
@Table(name = "company")
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @Column(name = "n", columnDefinition = "varchar(255) default ''")
    // имя "n" , не "id" , т.к. в нектр. БД слово id ключевое
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
        if (!(o instanceof CompanyEntity)) return false;
        CompanyEntity that = (CompanyEntity) o;
        return Objects.equals(n, that.n) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(fathername, that.fathername) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, firstname, lastname, fathername, birthday);
    }
}
