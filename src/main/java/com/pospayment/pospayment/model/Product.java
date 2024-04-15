package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    @Column(name = "companyID")
    private Integer companyID;

    @Column(name = "isActive")
    private String isActive = "0";

    @Column(name = "isDeleted")
    private String isDeleted = "0";


}
