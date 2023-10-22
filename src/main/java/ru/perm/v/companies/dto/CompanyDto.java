package ru.perm.v.companies.dto;

import java.util.Objects;

public class CompanyDto {
    private Long n;
    private String shortname = "";
    private String fullname = "";
    private String inn = "";
    private String ogrn = "";
    private String addressPost = "";
    private String addressUr = "";
//    EmployeeEntity director = new EmployeeEntity();


    public CompanyDto() {
    }

    public CompanyDto(Long n) {
        this.n = n;
    }

    public CompanyDto(Long n, String shortname, String fullname, String inn, String ogrn, String addressPost, String addressUr) {
        this.n = n;
        this.shortname = shortname;
        this.fullname = fullname;
        this.inn = inn;
        this.ogrn = ogrn;
        this.addressPost = addressPost;
        this.addressUr = addressUr;
    }

    //
//    public CompanyDto(Long n, String shortname, String fullname, String inn, String ogrn, String addressPost, String addressUr) {
//        this.n = n;
//        this.shortname = shortname;
//        this.fullname = fullname;
//        this.inn = inn;
//        this.ogrn = ogrn;
//        this.addressPost = addressPost;
//        this.addressUr = addressUr;
////        this.director = director;
//    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost;
    }

    public String getAddressUr() {
        return addressUr;
    }

    public void setAddressUr(String addressUr) {
        this.addressUr = addressUr;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "n=" + n +
                ", shortname='" + shortname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", inn='" + inn + '\'' +
                ", ogrn='" + ogrn + '\'' +
                ", addressPost='" + addressPost + '\'' +
                ", addressUr='" + addressUr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyDto)) return false;
        CompanyDto that = (CompanyDto) o;
        return Objects.equals(n, that.n) && Objects.equals(shortname, that.shortname) && Objects.equals(fullname, that.fullname) && Objects.equals(inn, that.inn) && Objects.equals(ogrn, that.ogrn) && Objects.equals(addressPost, that.addressPost) && Objects.equals(addressUr, that.addressUr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, shortname, fullname, inn, ogrn, addressPost, addressUr);
    }


}
