package com.weekfourproject;

import java.util.Scanner;

public class ProductApplication {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean menuLoop = false;
		
		System.out.println("Welcome to the Product Inventory Supply System.\n");
		
		do {
			System.out.println("Please make a Selection:\n"
					+ "1. View the database.\n"
					+ "2. Add record to database.\n"
					+ "3. Delete record from database.\n"
					+ "4. Update a record in database.");
			String userMenuInput = sc.nextLine();
			
			switch (userMenuInput) {
			case "1":
				DAO.readFromDB();
				break;
			case "2":
				// DAO.writeToDatabase();
				break;
			case "3":
				// DAO.deleteFromDatabase();
				break;
			case "4":
				DAO.updateDB();
				break;
			default:
				System.out.println("That is not a valid option, please input a valid choice.");
				menuLoop = true;
				break;
			}
		} while (menuLoop);
		sc.close();
	} // main
} // class