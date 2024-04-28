package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Table(name = "orders")
@Entity
@Data
public class Order {

    public final static Integer STATUS_PENDING = 0;
    public final static Integer STATUS_PROCESSING = 1;
    public final static Integer STATUS_COMPLETED = 2;
    public final static Integer STATUS_CANCELLED = 3;


    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @Column(name = "UUID")
    private String UUID = java.util.UUID.randomUUID().toString();

    @Column(name = "orderDetails")
    private String orderDetails;

    @Column(name = "totalPrice")
    private String price;

    @Column(name = "companyID")
    private Integer companyID;

    @Column(name = "menuID")
    private String menuID;

    @Column(name = "status")
    private Integer status = STATUS_PENDING;

    @Column(name = "isActive")
    private Boolean isActive = false;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

}
