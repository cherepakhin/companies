package ru.perm.v.companies.dto;

import java.util.Objects;

public class EmployeeDto {
    private Long n;
    private String firstname = "";
    private String lastname = "";
    private String fathername = "";
    private String birthday = "1990/01/01";
    private Long companyN = -1L;

    public EmployeeDto() {
    }

    public EmployeeDto(Long n, String firstname, String lastname, String fathername, String birthday, Long companyN) {
        this.n = n;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fathername = fathername;
        this.birthday = birthday;
        this.companyN = companyN;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getCompanyN() {
        return companyN;
    }

    public void setCompanyN(Long companyN) {
        this.companyN = companyN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDto)) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(n, that.n) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(fathername, that.fathername) && Objects.equals(birthday, that.birthday) && Objects.equals(companyN, that.companyN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, firstname, lastname, fathername, birthday, companyN);
    }
}
