package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

/**
 * Servlet implementation class RemoveBookFromCartServlet
 */
@WebServlet("/remove_from_cart")
public class RemoveBookFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveBookFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		
		Book book = new BookDAO().get(bookId);
		
		cart.removeItem(book);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/shopping_cart.jsp");
		dispatcher.forward(request, response);
		
	}

}
