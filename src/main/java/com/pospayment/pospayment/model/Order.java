package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "orders")
@Entity
@Data
public class Order {

    public final static Integer STATUS_PENDING = 0;
    public final static Integer STATUS_PROCESSING = 1;
    public final static Integer STATUS_COMPLETED = 2;
    public final static Integer STATUS_CANCELLED = 3;


    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "orderDetails")
    private String orderDetails;

    @Column(name = "totalPrice")
    private String price;

    @ManyToOne
    @JoinColumn(name = "companyID", referencedColumnName = "id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "menuID", referencedColumnName = "id")
    private Menu menu;

    @Column(name = "status")
    private Integer status = STATUS_PENDING;

    @Column(name = "isActive")
    private Boolean isActive = false;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

}
