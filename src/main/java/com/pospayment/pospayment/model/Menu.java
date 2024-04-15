package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Table(name = "menus")
@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "procutList")
    private ArrayList<Product> procutList;

    @Column(name = "companyID")
    private Integer companyID;

    @Column(name = "isActive")
    private String isActive = "0";

    @Column(name = "isDeleted")
    private String isDeleted = "0";

}
