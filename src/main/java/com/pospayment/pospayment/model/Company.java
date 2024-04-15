package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "companies")
@Entity
@Data
public class Company {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "isActive")
    private String isActive = "0";

    @Column(name = "isDeleted")
    private String isDeleted = "0";
}
