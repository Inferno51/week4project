package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.weekfourproject.Product;

/**
 * Servlet implementation class AddToDB
 */
@WebServlet("/AddToDB")
public class AddToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Product addToDB = new Product();
		
		double productPrice = Double.parseDouble(request.getParameter("product_price"));
		int productCount = Integer.parseInt(request.getParameter("product_count"));

		addToDB.setMovieName(request.getParameter("product_name"));
		addToDB.setMoviePrice(productPrice);
		addToDB.setMovieCount(productCount);
		addToDB.setMovieDesc(request.getParameter("product_desc"));

		com.weekfourproject.DAO.writeToDatabase(addToDB);
		
		request.getRequestDispatcher("insert.html").forward(request,response);
	}

}
