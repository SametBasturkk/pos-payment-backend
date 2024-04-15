package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "categories")
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "companyID")
    private Integer companyID;

    @Column(name = "productList")
    @OneToMany
    private List<Product> products;

    @Column(name = "isActive")
    private String isActive = "0";


    @Column(name = "isDeleted")
    private String isDeleted = "0";

}
