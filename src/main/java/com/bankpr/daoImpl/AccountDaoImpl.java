package com.bankpr.daoImpl;


import java.util.Date;
import java.util.List;
import java.util.Scanner;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bankpr.dao.AccountDao;
import com.bankpr.model.Customer;
import com.bankpr.utils.HibernateUtil;

public class AccountDaoImpl implements AccountDao {
	// Scanner for user input
	 Scanner scanner = new Scanner(System.in);
	 // Hibernate session factory for database operations
	    private SessionFactory sessionFactory;
	 // Constructor to initialize session factory
	    public AccountDaoImpl() {
	        this.sessionFactory = HibernateUtil.getSessionFactory();
	    }

	//create or insert
	@Override
	public void createCustomer(Customer customer) {
		 Transaction transaction = null;
	        try (Session session = sessionFactory.openSession()) {
	            transaction = session.beginTransaction();
	         // Creating a new customer instance
	            Customer cust = new Customer();
	            long cid=1;
	         // Getting input from user for customer details
	            System.out.println("Enter Account Holder Name: ");
	            String cName = scanner.nextLine();
	            System.out.println("Enter Contact Number: ");
	            String cNumber = scanner.nextLine();
	            System.out.println("Enter Account Number");
	            String account=scanner.next();
	            
	            System.out.println("Enter opening Balance");
	            double balance=scanner.nextDouble();
	            scanner.nextLine();
	           	            
	            System.out.println("Enter Email: ");
	            String cEmail = scanner.nextLine();
	            System.out.println("Enter Address: ");
	            String cAddress = scanner.nextLine();
	            // Setting customer details
	            cust.setCustomerId(cid);
	            cust.setCustomerName(cName);
	            cust.setCustomerNumber(cNumber);
	            cust.setCustomerAccountNumber(account);
	            cust.setBalance(balance);
	            cust.setAccOpenDate(new Date()); // Setting current date as account open date
	            cust.setCustomerEmail(cEmail);
	            cust.setCustomerAddress(cAddress);
	         // Saving customer object in the database
	            session.save(cust);
	            transaction.commit();
	            
	            System.out.println("Account created successfully");
	            
	            session.close();
	            
	           
	        } catch (Exception e) {
	        	System.out.println("Account Number Already Exist !");
	        	System.out.println("Try again with Different Account Number");

	            
	        }		
	}
	//update Customer
	@Override
	public void updateCustomer(Customer customer) {
		Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            System.out.println("Enter Customer ID to update: ");
            long cId = scanner.nextLong();
            scanner.nextLine(); // consume the newline character
            Customer cust = session.get(Customer.class, cId);

            if (cust != null) {
                System.out.println("Enter updated Account holder Name:");
                String name = scanner.nextLine();
                System.out.println("Enter updated Number:");
                String number = scanner.nextLine();
                System.out.println("Enter updated Email:");
                String email = scanner.nextLine();
                System.out.println("Enter updated Address:");
                String address = scanner.nextLine();

                cust.setCustomerName(name);
                cust.setCustomerNumber(number);
                cust.setCustomerEmail(email);
                cust.setCustomerAddress(address);

                session.update(cust);
                transaction.commit();
                System.out.println("Account updated successfully.");
            } else {
                System.out.println("Customer with ID " + cId + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
	}
	//Delete Customer
	@Override
	public void deleteCustomer(Customer customer) {
		 Transaction transaction = null;
	        try (Session session = sessionFactory.openSession()) {
	            transaction = session.beginTransaction();
	            System.out.println("Enter Customer Id to delete: ");
	            long cid = scanner.nextLong();
	            Customer cust = session.get(Customer.class, cid);

	            if (customer != null) {
	                session.delete(cust);
	                transaction.commit();
	                System.out.println("Account deleted successfully.");
	            } else {
	                System.out.println("Customer with ID " + cid + " not found.");
	            }
	            session.close();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
		
	}
	
//	Display List
	@Override
	public List<Customer> displayAllCustomer() {
		try (Session session = sessionFactory.openSession()) {
            String hql = "from Customer";
            Query q = session.createQuery(hql);
            List<Customer> customers = q.getResultList();
            for (Customer customer : customers) {
                System.out.println("ID: " + customer.getCustomerId() + 
                		",Account holder Name: " + customer.getCustomerName() +
                		",Account Number : "+customer.getCustomerAccountNumber()+
                		",Account opening Date : "+customer.getAccOpenDate()+
                		",Account Balance : "+customer.getBalance()+
                        ", Email: " + customer.getCustomerEmail() +
                        ", Address: " + customer.getCustomerAddress() +
                        ", Number: " + customer.getCustomerNumber());
            }
            return customers;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	//particular account number
	@Override
	public Customer getByAccountNumber(String accountNumber) {
		
		try (Session session = sessionFactory.openSession()) {
			
			 Query<Customer> query = session.createQuery("FROM Customer WHERE customerAccountNumber = :accNumber", Customer.class);
	            query.setParameter("accNumber", accountNumber);
	            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
           
        }
		 return null;
	}
		

}

