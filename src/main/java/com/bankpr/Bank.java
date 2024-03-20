package com.bankpr;

import java.util.Optional;
import java.util.Scanner;

import com.bankpr.daoImpl.AccountDaoImpl;
import com.bankpr.daoImpl.UserDaoImpl;
import com.bankpr.enums.UserRole;
import com.bankpr.model.Customer;
import com.bankpr.model.User;
import com.bankpr.serviceImpl.AccountServiceImpl;
import com.bankpr.serviceImpl.TransactionServiceImpl;
import com.bankpr.serviceImpl.UserServiceImpl;


public class Bank {
	
	//Shows Customer balance
	static public void transactionCustomer() {
		  // Display options for the customer transaction
		System.out.println("1.View Balance");
 	   System.out.println("2.Exit");
 	   Scanner scanner5=new Scanner(System.in);
 	   System.out.print("Enter your choice: ");
		int choice = scanner5.nextInt();
		scanner5.nextLine();		
	TransactionServiceImpl tService2=new TransactionServiceImpl();
		
		Customer cs2=new Customer();
		switch (choice) {
		case 1:
			System.out.println("Enter Customer ID");
		    long cid2=scanner5.nextLong();
			Optional<Customer> optionalAccount2 = tService2.findById(cid2);
			 // Retrieve customer account details if present
			 optionalAccount2 = tService2.findById(cid2);
		        if (optionalAccount2.isPresent()) {
		           Customer customer = optionalAccount2.get();
		            double balance = tService2.getBalance(customer);
		            String customerName=customer.getCustomerName();
		            System.out.println("Customer "+customerName+" Has Account Balance: Rs " + balance);
		        }
		        break;
		case 2:
			mainMenu(); // Return to the main menu

		default:
			System.out.println("Invalid choice. Please enter a valid option.");
		}
		
	}
	
	
	
	//Admin Transaction Menu
	
	static public void transactionMenu() {
		Scanner scanner=new Scanner(System.in);
		// Continuously display the transaction menu
		while (true) {
			TransactionServiceImpl tService=new TransactionServiceImpl();			
			Customer cs=new Customer();
			System.out.println(" ====Welcome To Transaction Panel=== ");
			System.out.println("1. Withdraw Money");
			System.out.println("2. Deposit Money");
			System.out.println("3. View Balance");
			System.out.println("4. Main Menu");
			
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();		
			
			switch (choice) {
					
			case 1:
				// Withdraw money
				System.out.println("Enter Customer ID");
			    long cid=scanner.nextLong();
				System.out.println("Enter Amount");
				double amount=scanner.nextDouble();
				Optional<Customer> optionalAccount = tService.findById(cid);
				 
			     if (optionalAccount.isPresent()) {
			         Customer customer = optionalAccount.get();
			         tService.withdraw(customer, amount);
			         System.out.println(" Withdraw of Amount Rs "+amount+" Sucessfully");
			     }
				
				break;
			case 2:  
				// Deposit money
				System.out.println("Enter Customer ID");
				long cid2=scanner.nextLong();
				System.out.println("Enter Amount");
				double amount1=scanner.nextDouble();
				optionalAccount =tService.findById(cid2);
		     if (optionalAccount.isPresent()) {
		         Customer customer = optionalAccount.get();
		         tService.deposit(customer, amount1);
		         System.out.println("Deposit of Amount Rs "+amount1+" Sucessfully");
		     }
		         break;
			case 3:
				// View balance
				System.out.println("Enter Customer ID");
				long cid3=scanner.nextLong();
				 optionalAccount = tService.findById(cid3);
			        if (optionalAccount.isPresent()) {
			           Customer customer = optionalAccount.get();
			            double balance = tService.getBalance(customer);
			            String customerName=customer.getCustomerName();
			            System.out.println("Customer "+customerName+" Has Account Balance: Rs " + balance);
			        }
			        break;
		     case 4:
		    	 // Return to the main menu
					System.out.println("Enter Your Credentials");
					mainMenu();

				default:
					System.out.println("Invalid choice. Please enter a valid option.");
		      }
				
			}
	}
	
	
	// Admin main menu
	static public void mainMenu() {
		UserDaoImpl userDao = new UserDaoImpl();
		UserServiceImpl loginService = new UserServiceImpl(userDao);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("===== Welcome to BANK =======");
		 // Request user credentials
		System.out.println("Please Enter Email");
		String email = sc.nextLine();
		System.out.println("Please Enter Password ");
		String password = sc.nextLine();
		
		 // Define user roles
		String adminLogin = "ADMIN";
		String customerLogin = "CUSTOMER";
		UserRole adminRole = UserRole.valueOf(adminLogin);
		UserRole customerRole = UserRole.valueOf(customerLogin);
		 // Authenticate user
		Optional<User> user = loginService.login(email, password);
		if (user.isPresent()) {
			final UserRole role = user.get().getRole();
			System.out.println("Login successful. User role: " + user.get().getRole());
			if (role == adminRole) {
				 // Initialize Account Service and DAO for admin
				AccountDaoImpl aDao = new AccountDaoImpl();
				Customer customer = new Customer();
				AccountServiceImpl accService = new AccountServiceImpl();
				Scanner scanner = new Scanner(System.in);
				  // Admin menu
				while (true) {

					System.out.println(" ====Welcome To Admin Panel=== ");
					System.out.println("1. Create Account");
					System.out.println("2. Delete Account");
					System.out.println("3. Update Account");
					System.out.println("4. Show All Account Details");
					System.out.println("5. Search by Account Number");
					System.out.println("6. Transaction ");

					System.out.println("7. Exit");

					System.out.print("Enter your choice: ");
					int choice = scanner.nextInt();
					scanner.nextLine();

					switch (choice) {

					case 1:

						accService.createCustomer(customer);
						break;
					case 2:
						accService.deleteCustomer(customer);
						break;
					case 3:
						accService.updateCustomer(customer);
						break;
					case 4:
						accService.displayAllCustomer();
						break;
					case 5:
						System.out.println("Enter Account number ");
						String accountNumberToSearch = scanner.next();
						Customer account = aDao.getByAccountNumber(accountNumberToSearch);
						if (account != null) {
							System.out.println("Account found: " + account);
						} else {
							System.out.println("Account not found for account number: " + accountNumberToSearch);
						}

						break;
					case 6:
						transactionMenu();
					case 7:
						System.out.println("Exiting the application. Goodbye!");
						System.exit(0);

					default:
						System.out.println("Invalid choice. Please enter a valid option.");
					}
				}

			} else if (role == customerRole) {
				System.out.println("========Welcome Customer======");
				transactionCustomer();
			}
		} else {
			System.out.println("Login failed. Invalid username or password.");
		}
	}
	
	public static void main(String[] args) {
		
		//call admin main menu
       mainMenu();
		
	}
}
