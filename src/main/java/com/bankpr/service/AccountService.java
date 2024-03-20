package com.bankpr.service;

import java.util.List;

import com.bankpr.model.Customer;

public interface AccountService {

    // Method to create a new customer account
    void createCustomer(Customer customer);

    // Method to update customer account details
    void updateCustomer(Customer customer);

    // Method to delete a customer account
    void deleteCustomer(Customer customer);

    // Method to display all customer accounts
    List<Customer> displayAllCustomer();

    // Method to retrieve a customer by account number
    Customer getByAccountNumber(String accountNumber);
}
