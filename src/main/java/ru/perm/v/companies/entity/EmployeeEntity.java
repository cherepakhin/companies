package ru.perm.v.companies.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @Column(name = "n", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long n = 0L; // имя "n" , не "id" , т.к. в нектр. БД слово id ключевое
    @Column(name = "firstname", columnDefinition = "varchar(255) default ''")
    private String firstname = "";
    @Column(name = "lastname", columnDefinition = "varchar(255) default ''")
    private String lastname = "";
    @Column(name = "fathername", columnDefinition = "varchar(255) default ''")
    private String fathername = "";
    @Column(name = "birthday", columnDefinition = "TIMESTAMP")
    private LocalDate birthday = LocalDate.now();

    public EmployeeEntity(long n) {
        super();
        this.n = n;
    }

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
        if (!(o instanceof EmployeeEntity)) return false;
        EmployeeEntity employeeEntity = (EmployeeEntity) o;
        return Objects.equals(n, employeeEntity.n) && Objects.equals(firstname, employeeEntity.firstname) && Objects.equals(lastname, employeeEntity.lastname) && Objects.equals(fathername, employeeEntity.fathername) && Objects.equals(birthday, employeeEntity.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, firstname, lastname, fathername, birthday);
    }
}
