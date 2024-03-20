package com.bankpr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Customer {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated primary key
    private Long customerId;
    
    // Fields
    @Column(length=30) // Limiting length for database column
    private String customerName; // Name of the customer
    
    @Column(length=30) // Limiting length for database column
    private String customerNumber; // Number of the customer
    
    @Column(length=30, unique = true) // Limiting length and ensuring uniqueness for database column
    private String customerAccountNumber; // Account number of the customer
    
    private double balance; // Balance of the customer
    
    @Temporal(TemporalType.DATE) // Storing only date without time information
    private Date accOpenDate; // Date when the account was opened
    
    @Column(length=30) // Limiting length for database column
    private String customerEmail; // Email address of the customer
    
    @Column(length=30) // Limiting length for database column
    private String customerAddress; // Address of the customer
    
    // Getters and Setters
    
    // Getter for customerId
    public Long getCustomerId() {
        return customerId;
    }
    
    // Setter for customerId
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    // Getter for customerName
    public String getCustomerName() {
        return customerName;
    }
    
    // Setter for customerName
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    // Getter for customerNumber
    public String getCustomerNumber() {
        return customerNumber;
    }
    
    // Setter for customerNumber
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
    
    // Getter for customerAccountNumber
    public String getCustomerAccountNumber() {
        return customerAccountNumber;
    }
    
    // Setter for customerAccountNumber
    public void setCustomerAccountNumber(String customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }
    
    // Getter for balance
    public double getBalance() {
        return balance;
    }
    
    // Setter for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    // Getter for accOpenDate
    public Date getAccOpenDate() {
        return accOpenDate;
    }
    
    // Setter for accOpenDate
    public void setAccOpenDate(Date accOpenDate) {
        this.accOpenDate = accOpenDate;
    }
    
    // Getter for customerEmail
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    // Setter for customerEmail
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    // Getter for customerAddress
    public String getCustomerAddress() {
        return customerAddress;
    }
    
    // Setter for customerAddress
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    // toString method to provide a string representation of the object
    
    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerNumber="
                + customerNumber + ", customerAccountNumber=" + customerAccountNumber + ", balance=" + balance
                + ", accOpenDate=" + accOpenDate + ", customerEmail=" + customerEmail + ", customerAddress="
                + customerAddress + "]";
    }
    
    // Constructors
    
    // Constructor with parameters
    public Customer(Long customerId, String customerName, String customerNumber, String customerAccountNumber,
            double balance, Date accOpenDate, String customerEmail, String customerAddress) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.customerAccountNumber = customerAccountNumber;
        this.balance = balance;
        this.accOpenDate = accOpenDate;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
    }
    
    // Default Constructor
    public Customer() {
        super(); // Calls constructor of superclass (Object)
    }   
}
