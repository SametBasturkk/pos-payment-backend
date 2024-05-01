package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date modifiedAt;

}
