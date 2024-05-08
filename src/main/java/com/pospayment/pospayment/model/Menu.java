package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Table(name = "menus", indexes = {@Index(name = "menuIndex", columnList = "name, companyID")})
@Entity
@Data
public class Menu {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "menu_category",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

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
