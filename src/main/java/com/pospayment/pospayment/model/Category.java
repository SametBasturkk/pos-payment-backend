package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Table(name = "categories")
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @Column(name = "UUID")
    private String UUID = java.util.UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "companyID")
    private Integer companyID;

    @Column(name = "productList")
    @OneToMany
    private List<Product> products;

    @Column(name = "isActive")
    private Boolean isActive = false;


    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

}
