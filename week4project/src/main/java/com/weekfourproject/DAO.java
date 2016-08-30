package com.weekfourproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";
	
	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;
	
	public static void connToDB() {
		
		try {
			
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Attempting connection to database...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to the database!");
		} catch (SQLException | ClassNotFoundException e) {
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
				ourProduct.setMovieID(RES_SET.getInt("movie_id"));
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
	
	public static void writeToDatabase(Product product) {
		
		Product productToAdd = new Product();
		
		productToAdd = product;
		
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
	
	
	
	public static void deleteFromDatabase(Product product) {
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("What is the product ID you would like to delete?\n");
//		String deleteID = sc.nextLine();
		
		Product productToDelete = new Product();
		
		productToDelete = product;
		
		try {
			connToDB();
			
			PREP_STMT=CONN.prepareStatement("DELETE FROM products.movies WHERE movie_id = ?");
			PREP_STMT.setInt(1, productToDelete.getMovieID());
			PREP_STMT.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		sc.close();
	} // deleteFromDatabase
	
	
	
	
	
	
	public static void updateDB() {
		Scanner sc = new Scanner(System.in);
		readFromDB();
		System.out.println("Please enter the ID number of the product you would like to update.");
		int updateProductID = Integer.parseInt(sc.nextLine());
		
		Product productToUpdate = new Product();
		

		try {

			PREP_STMT = CONN.prepareStatement(updateDB);
			
			PREP_STMT.setString(1, productToUpdate.getMovieName());
			PREP_STMT.setDouble(2, productToUpdate.getMoviePrice());
			PREP_STMT.setInt(3, productToUpdate.getMovieCount());
			PREP_STMT.setString(4, productToUpdate.getMovieDesc());
			PREP_STMT.setInt(5, updateProductID);

			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
	} // update DB Method
	
	
	private static String updateDB = ("UPDATE `products`.`movies` SET `movie_name` = ?, `movie_price` = ?, `movie_inv_count` = ?, `movie_desc` = ? WHERE `movie_id` = ?");
	
	
	private static String insertIntoTable = "INSERT INTO `products`.`movies`"
			+ "(movie_name, movie_price, movie_inv_count, movie_desc)"
			+ "VALUES"
			+ "(?, ?, ?, ?)";
	
//	private static Product aboutTheProduct() {
//		Scanner sc = new Scanner (System.in);
//		
//		Product productToAdd = new Product();
//		
//		System.out.println("What is the product's name?\n");
//		productToAdd.setMovieName(sc.nextLine());
//		
//		System.out.println("What is the product's price?\n");
//		String priceInput = sc.nextLine();
//		productToAdd.setMoviePrice(Double.parseDouble(priceInput));
//		
//		System.out.println("How many would you like to add to the database?\n");
//		String countInput = sc.nextLine();
//		productToAdd.setMovieCount(Integer.parseInt(countInput));
//		
//		System.out.println("Please add a description of the product.\n");
//		productToAdd.setMovieDesc(sc.nextLine());
//		
//		sc.close();
//		return productToAdd;
//	} // aboutTheProduct
	
} // class
