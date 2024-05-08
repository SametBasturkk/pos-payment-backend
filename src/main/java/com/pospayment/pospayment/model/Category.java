package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "categories", indexes =
        {@Index(name = "categoryIndex", columnList = "name, companyID")}
)
@Entity
@Data
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "companyID", referencedColumnName = "id", nullable = false)
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
