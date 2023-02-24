package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private OrderDAO orderDao;

	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.orderDao = new OrderDAO();
	}

	public void listAllOrder() throws ServletException, IOException {
		List<BookOrder> listOrder = orderDao.listAll();
		request.setAttribute("listOrder", listOrder);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("order_list.jsp");
		dispatcher.forward(request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder order = orderDao.get(orderId);
		
		request.setAttribute("order", order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("order_detail.jsp");
		dispatcher.forward(request, response);
	}

	public void showCheckOutForm() throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/checkout.jsp");
		dispatcher.forward(request, response);
		
	}

	public void placeOrder() throws ServletException, IOException {

		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");
		
		BookOrder order = new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		String shippingAddres = address + "," + city + "," + zipcode + "," + country;
		order.setShippingAddress(shippingAddres);
		//order.setPaymentMethod(paymentMethod);
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = shoppingCart.getItems();
		
		Iterator<Book> iterator = items.keySet().iterator();
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		
		while(iterator.hasNext()) {
			
			Book book = iterator.next();
			Integer quantity = items.get(book);
			float subtotal = quantity * book.getPrice();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book); 
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity); 
			orderDetail.setSubtotal(subtotal);
			
			orderDetails.add(orderDetail); 
			
		}
		
		order.setOrderDetails(orderDetails);
		order.setTotal(shoppingCart.getTotalAmount());
		
		orderDao.create(order);
		  
		shoppingCart.clear(); 
		
		String message = "Thank you. Your order has been received. We will deliver your books within a few days.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/message.jsp");
		dispatcher.forward(request, response);
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		List<BookOrder> listOrders = orderDao.listByCustomer(customer.getCustomerId());
		
		request.setAttribute("listOrders", listOrders);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/order_list.jsp");
		dispatcher.forward(request, response);
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		BookOrder order = orderDao.get(orderId, customer.getCustomerId());
		
		request.setAttribute("order", order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/order_detail.jsp");
		dispatcher.forward(request, response);
		
	}

	public void showEditOrderForm() throws ServletException, IOException {
		
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder order = orderDao.get(orderId);
		
		HttpSession session = request.getSession();
		session.setAttribute("order", order);
		if(order == null) {
			request.setAttribute("message", "Could not find order with ID " + orderId + ".");
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
		
		request.setAttribute("order", order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("order_form.jsp");
		dispatcher.forward(request, response);
		
		}
	}
		
	
}
