package ru.perm.v.companies.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "company")
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @Column(name = "n", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // имя "n" , не "id" , т.к. в нектр. БД слово id ключевое
    private Long n = 0L;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "director_n", nullable = false)
    PeopleEntity director = new PeopleEntity();

}
