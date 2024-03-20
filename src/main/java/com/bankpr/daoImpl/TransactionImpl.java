package com.bankpr.daoImpl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bankpr.dao.Transactions;
import com.bankpr.model.Customer;

public class TransactionImpl implements Transactions {

    // SessionFactory instance for database session management
    private SessionFactory sessionFactory;

    // Constructor to initialize SessionFactory
    public TransactionImpl() {
        // Create SessionFactory using Hibernate configuration
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Method to find customer by ID
    @Override
    public Optional<Customer> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            // Retrieve customer by ID and return it wrapped in an Optional
            return Optional.ofNullable(session.get(Customer.class, id));
        }
    }

    // Method to withdraw amount from customer's account
    @Override
    public void withdraw(Customer customer, double amount) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            // Retrieve customer from database
            customer = session.get(Customer.class, customer.getCustomerId());
            double currentBalance = customer.getBalance();
            if (currentBalance >= amount) {
                // If sufficient balance, update balance and commit transaction
                customer.setBalance(currentBalance - amount);
                session.update(customer);
                transaction.commit();
            } else {
                // If insufficient balance, rollback transaction and throw exception
                transaction.rollback();
                throw new IllegalArgumentException("Insufficient balance");
            }
        }
    }

    // Method to deposit amount to customer's account
    @Override
    public void deposit(Customer customer, double amount) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            // Retrieve customer from database
            customer = session.get(Customer.class, customer.getCustomerId());
            double currentBalance = customer.getBalance();
            // Update balance by adding deposited amount and commit transaction
            customer.setBalance(currentBalance + amount);
            session.update(customer);
            transaction.commit();
        }
    }

    // Method to retrieve account balance of a customer
    @Override
    public double getBalance(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            // Retrieve customer from database and return the balance
            Customer balance = session.get(Customer.class, customer.getCustomerId());
            return balance.getBalance();
        }
    }
}
