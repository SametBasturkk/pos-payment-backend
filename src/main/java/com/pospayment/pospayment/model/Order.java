package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "orders")
@Entity
@Data
public class Order {


    @Id
    @GeneratedValue
    private Integer id;

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
    private String isActive = "0";

    @Column(name = "isDeleted")
    private String isDeleted = "0";

}
