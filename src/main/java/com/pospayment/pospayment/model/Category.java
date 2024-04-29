package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "categories")
@Entity
@Data
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "companyID", referencedColumnName = "id")
    private Company company;


    @Column(name = "isActive")
    private Boolean isActive = false;


    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

}
