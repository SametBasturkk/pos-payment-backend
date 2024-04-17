package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Table(name = "orders")
@Entity
@Data
public class Order {


    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @Column(name = "UUID")
    private String UUID = java.util.UUID.randomUUID().toString();

    @Column(name = "orderID")
    private String orderID;

    @Column(name = "productID")
    private String productID;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private String price;

    @Column(name = "companyID")
    private Integer companyID;

    @Column(name = "isActive")
    private Boolean isActive = false;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

}
