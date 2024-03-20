package com.bankpr.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bankpr.model.Customer;
import com.bankpr.model.User;

public class HibernateUtil {

    // SessionFactory instance for database session management
    private final static SessionFactory sessionFactory = buildSessionFactory();

    // Method to build SessionFactory
    private static SessionFactory buildSessionFactory() {
        try {
            // Create SessionFactory using Hibernate configuration file
            return new Configuration().configure("hibernate.cfg.xml")
                    // Add annotated classes for entity mapping
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            // Throw ExceptionInInitializerError if unable to create SessionFactory
            throw new ExceptionInInitializerError(e);
        }
    }

    // Method to retrieve SessionFactory instance
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Method to open a new database session
    public static Session getSession() {
        return getSessionFactory().openSession(); // Open a new session
    }
}
