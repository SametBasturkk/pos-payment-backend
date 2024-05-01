package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "companies")
@Entity
@Data
public class Company {

    @Id
    @Column(name = "id")
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
    private Boolean isActive = false;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date modifiedAt;
}
