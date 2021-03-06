package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weekfourproject.Product;

/**
 * Servlet implementation class DeleteFromDB
 */
@WebServlet("/DeleteFromDB")
public class DeleteFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFromDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Product deleteFromDB = new Product();
		
		int productID = Integer.parseInt(request.getParameter("product_id"));
		
		deleteFromDB.setMovieID(productID);
		
		com.weekfourproject.DAO.deleteFromDatabase(deleteFromDB);
		
		request.getRequestDispatcher("delete.html").forward(request,response);
	}
	
	
}
