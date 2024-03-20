package com.bankpr.dao;

import java.util.List;

import com.bankpr.model.Customer;

public interface AccountDao {

    // Method to create a new customer account
    public void createCustomer(Customer customer);

    // Method to update customer account details
    public void updateCustomer(Customer customer);

    // Method to delete a customer account
    public void deleteCustomer(Customer customer);

    // Method to display all customer accounts
    List<Customer> displayAllCustomer();

    // Method to retrieve a customer by account number
    Customer getByAccountNumber(String accountNumber);
}
