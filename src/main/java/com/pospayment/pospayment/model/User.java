package com.pospayment.pospayment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


enum UserRole {
    USER,
    ADMIN
}

@Data
@Entity
@Table(name = "users", indexes = {@Index(name = "userIndex", columnList = "username, name, role, companyID")})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 3, message = "Surname must be at least 3 characters long")
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotBlank(message = "TCKN cannot be blank")
    @Size(min = 11, max = 11, message = "TCKN must be exactly 11 characters long")
    @Column(name = "tckn", unique = true, nullable = false)
    private String tckn;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Pattern(message = "Phone number must be valid", regexp = "^\\+?[0-9]{10,15}$")
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.ADMIN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyID", referencedColumnName = "id", nullable = false)
    private Company company;

    @Column(name = "isActive")
    private Boolean isActive = false;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

    @CreationTimestamp
    @Column(name = "createdAt")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "modifiedAt")
    private Date modifiedAt;
}


