package com.bankpr.dao;

import java.util.Optional;

import com.bankpr.model.Customer;

public interface Transactions {

    // Method to find a customer by ID
    Optional<Customer> findById(Long id);

    // Method to withdraw an amount from a customer's account
    void withdraw(Customer customer, double amount);

    // Method to deposit an amount to a customer's account
    void deposit(Customer customer, double amount);

    // Method to retrieve the balance of a customer's account
    double getBalance(Customer customer);
}
