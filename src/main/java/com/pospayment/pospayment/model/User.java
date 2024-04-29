package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "tckn", unique = true)
    private String tckn;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    private String role = "user";

    @ManyToOne
    @JoinColumn(name = "companyID", referencedColumnName = "id")
    private Company company;

    @Column(name = "isActive")
    private Boolean isActive = false;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;
}
