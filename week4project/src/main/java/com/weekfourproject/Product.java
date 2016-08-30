package com.weekfourproject;

public class Product {
	
	private int movieID = 0;
	private String movieName = null;
	private double moviePrice = 0.0d;
	private int movieCount = 0;
	private String movieDesc = null;
	
	
	// Constructor
	public Product() {
		super();
	}

	
	// Getters and setters.
	public int getMovieID() {
		return movieID;
	}


	public void setMovieID(int productID) {
		this.movieID = productID;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public double getMoviePrice() {
		return moviePrice;
	}


	public void setMoviePrice(double moviePrice) {
		this.moviePrice = moviePrice;
	}


	public int getMovieCount() {
		return movieCount;
	}


	public void setMovieCount(int movieCount) {
		this.movieCount = movieCount;
	}


	public String getMovieDesc() {
		return movieDesc;
	}


	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}


	@Override
	public String toString() {
		return "Product [movieID=" + movieID + ", movieName=" + movieName + ", moviePrice=" + moviePrice
				+ ", movieCount=" + movieCount + ", movieDesc=" + movieDesc + "]";
	}
	
}
