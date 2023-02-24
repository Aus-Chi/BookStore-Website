package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String[] arrayBookIds = request.getParameterValues("bookId");
		String[] arrayQuantities = new String[arrayBookIds.length];
		
		for(int i = 1; i <= arrayBookIds.length; i++) {
			
			String aq = request.getParameter("quantity"+i);
			arrayQuantities[i-1] = aq; 
			       
		}
		
		int[] bookIds = Arrays.stream(arrayBookIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
	
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
	
		cart.updateCart(bookIds, quantities);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/shopping_cart.jsp");
		dispatcher.forward(request, response);
	}

}
