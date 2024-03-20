package com.bankpr.daoImpl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bankpr.dao.UserDao;
import com.bankpr.model.User;
import com.bankpr.utils.HibernateUtil;

public class UserDaoImpl implements UserDao {

    // SessionFactory instance for database session management
    private SessionFactory sessionFactory;

    // Constructor to initialize SessionFactory
    public UserDaoImpl() {
        // Create SessionFactory using HibernateUtil
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    // Method to find user by username (email)
    @Override
    public Optional<User> findByUsername(String email) {
        try (Session session = sessionFactory.openSession()) {
            // Create HQL query to retrieve user by email
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            // Set parameter with the provided email
            query.setParameter("email", email);
            // Return the unique result wrapped in an Optional
            return query.uniqueResultOptional();
        }
    }
}
