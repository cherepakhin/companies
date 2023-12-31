package ru.perm.v.companies.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @Column(name = "n", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // имя "n" , не "id" , т.к. в нектр. БД слово id ключевое
    private Long n;
    @Column(name = "shortname", columnDefinition = "varchar(255) default ''")
    private String shortname = "";
    @Column(name = "fullname", columnDefinition = "varchar(255) default ''")
    private String fullname = "";
    @Column(name = "inn", columnDefinition = "varchar(13) default ''")
    private String inn = "";
    @Column(name = "ogrn", columnDefinition = "varchar(15) default ''")
    private String ogrn = "";
    @Column(name = "address_post", columnDefinition = "varchar(100) default ''")
    private String addressPost = "";
    @Column(name = "address_ur", columnDefinition = "varchar(100) default ''")
    private String addressUr = "";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_n", nullable = false)
    EmployeeEntity director = new EmployeeEntity();
//TODO: Добавить сотрудников, либо тут через "OneToMany" List<EmployeeEntity>,
// или ЛУЧШЕ через отдельную сущность(таблицу) или для "OneToMany",
// или для "ManyToMany" (когда сотрудник работает в несклк. предприятиях)
// class CompanyEmployee (CompanyId, EmployeeId)
// Плюс отдельной сущности отвязать Company от Employee.

    public CompanyEntity() {
    }

    public CompanyEntity(long n) {
        super();
        this.n = n;
    }

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

    public EmployeeEntity getDirector() {
        return director;
    }

    public void setDirector(EmployeeEntity director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyEntity)) return false;
        CompanyEntity that = (CompanyEntity) o;
        return Objects.equals(n, that.n) && Objects.equals(shortname, that.shortname) && Objects.equals(fullname, that.fullname) && Objects.equals(inn, that.inn) && Objects.equals(ogrn, that.ogrn) && Objects.equals(addressPost, that.addressPost) && Objects.equals(addressUr, that.addressUr) && Objects.equals(director.getN(), that.director.getN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, shortname, fullname, inn, ogrn, addressPost, addressUr, director.getN());
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
                ", director=" + director +
                '}';
    }
}
