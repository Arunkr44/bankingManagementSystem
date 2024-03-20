package com.bankpr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bankpr.enums.UserRole;

@Entity
public class User {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated primary key
    private int id;
    
    // Fields
    @Column(nullable = false, unique = true, length=30) // Not nullable, unique, and limiting length for database column
    private String email; // Email address of the user
    
    @Column(nullable = false , length=30) // Not nullable and limiting length for database column
    private String password; // Password of the user
    
    @Column(nullable = false , length=30) // Not nullable and limiting length for database column
    private String name; // Name of the user
    
    @Enumerated(EnumType.STRING) // Enumerated type to store UserRole as a String
    private UserRole role; // Role of the user
    
    // Constructor with parameters
    public User(int id, String email, String password, String name, UserRole role) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    // Getters and Setters
    
    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for role
    public UserRole getRole() {
        return role;
    }

    // Setter for role
    public void setRole(UserRole role) {
        this.role = role;
    }

    // Default Constructor
    public User() {
        super();
    }

    // toString method to provide a string representation of the object
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", role=" + role
                + "]";
    }
}
