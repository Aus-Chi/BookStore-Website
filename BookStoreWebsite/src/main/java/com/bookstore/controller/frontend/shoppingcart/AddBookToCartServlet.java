package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;

/**
 * Servlet implementation class AddBookToCartServlet
 */
@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookToCartServlet() {
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

		Integer bookId = Integer.parseInt(request.getParameter("book_id"));

		Object cartObject = request.getSession().getAttribute("cart");

		if (cartObject == null) {
			ShoppingCart shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);

		}
		
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		shoppingCart.addItem(new BookDAO().get(bookId));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/shopping_cart.jsp");
		dispatcher.forward(request, response);
		
	}

}
