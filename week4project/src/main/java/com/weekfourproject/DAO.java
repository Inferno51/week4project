package com.weekfourproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {
	
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";
	
	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;
	
	public static void connToDB() {
		
		try {
			System.out.println("Attempting connection to database...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to the database!");
		} catch (SQLException e) {
			System.out.println("Database connection failed.");
			e.printStackTrace();
		} //try statement
		
	} // connToDB
	
	public static void readFromDB() {
		connToDB();
		
		ArrayList<Product> ourProducts = new ArrayList<>();
		
		try {
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM products.movies;");
			
			while (RES_SET.next()) {
				Product ourProduct = new Product();
				ourProduct.setMovieID(RES_SET.getString("movie_id"));
				ourProduct.setMovieName(RES_SET.getString("movie_name"));
				ourProduct.setMoviePrice(RES_SET.getDouble("movie_price"));
				ourProduct.setMovieCount(RES_SET.getInt("movie_inv_count"));
				ourProduct.setMovieDesc(RES_SET.getString("movie_desc"));
				
				ourProducts.add(ourProduct);
			}
			
			for (Product product : ourProducts) {
				System.out.println(product.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // readFromDB
	
	public static void writeToDatabase() {
		
		Product productToAdd = new Product();
		
		productToAdd = aboutTheProduct();
		
		try {
			connToDB();
			
			PREP_STMT = CONN.prepareStatement(insertIntoTable);
			
			PREP_STMT.setString(1, productToAdd.getMovieName());
			PREP_STMT.setDouble(2, productToAdd.getMoviePrice());
			PREP_STMT.setInt(3, productToAdd.getMovieCount());
			PREP_STMT.setString(4, productToAdd.getMovieDesc());
			
			System.out.println(PREP_STMT);
			
			PREP_STMT.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // writeToDatabase
	
	/* public static void deleteFromDatabase() {
		Scanner sc = new Scanner(System.in);
		connToDB();
		
		System.out.println("What is the product ID you would like to delete?\n");
		String deleteID = sc.nextLine();
		
		try {
			PREP_STMT=CONN.prepareStatement("DELETE FROM products.movies WHERE movie_id = ?");
			PREP_STMT.setString(1, deleteID);
			PREP_STMT.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	} // deleteFromDatabase
	
	public static void updateDatabase() {
		Scanner sc = new Scanner(System.in);
		connToDB();
		
		System.out.println("What is the product ID you would like to update?\n");
		int idNumber = Integer.parseInt(sc.nextLine());
		
		Product productToUpdate = new Product();
		
		productToUpdate = 
		
	} */
	
	
	private static String insertIntoTable = "INSERT INTO `products`.`movies`"
			+ "(movie_name, movie_price, movie_inv_count, movie_desc)"
			+ "VALUES"
			+ "(?, ?, ?, ?)";
	
	private static Product aboutTheProduct() {
		Scanner sc = new Scanner (System.in);
		
		Product productToAdd = new Product();
		
		System.out.println("What is the product's name?\n");
		productToAdd.setMovieName(sc.nextLine());
		
		System.out.println("What is the product's price?\n");
		String priceInput = sc.nextLine();
		productToAdd.setMoviePrice(Double.parseDouble(priceInput));
		
		System.out.println("How many would you like to add to the database?\n");
		String countInput = sc.nextLine();
		productToAdd.setMovieCount(Integer.parseInt(countInput));
		
		System.out.println("Please add a description of the product.\n");
		productToAdd.setMovieDesc(sc.nextLine());
		
		sc.close();
		return productToAdd;
	} // aboutTheProduct
	
} // class
