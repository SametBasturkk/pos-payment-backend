package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "menus")
@Entity
@Data
public class Menu {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "menu_category",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "companyID", referencedColumnName = "id")
    private Company company;

    @Column(name = "isActive")
    private Boolean isActive = false;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

}
