package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {

	private CustomerDAO customerDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		customerDao = new CustomerDAO();
	}

	public void listCustomer() throws ServletException, IOException {

		List<Customer> customers = customerDao.listAll();

		request.setAttribute("listCustomer", customers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_list.jsp");
		dispatcher.forward(request, response);
	}

	public void createCustomer() throws ServletException, IOException {

		String email = request.getParameter("email");

		Customer existCustomer = customerDao.findByEmail(email);

		if (existCustomer != null) {
			request.setAttribute("message", "Could not create new customer: the email " + email
					+ " is already registered by another customer.");
		}

		else {

			String fullName = request.getParameter("fullName");
			String password = request.getParameter("password");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String zipCode = request.getParameter("zipCode");
			String country = request.getParameter("country");

			Customer customer = new Customer();
			customer.setEmail(email);
			customer.setFullname(fullName);
			customer.setAddress(address);
			customer.setCity(city);
			customer.setCountry(country);
			customer.setZipcode(zipCode);
			customer.setPhone(phoneNumber);
			customer.setPassword(password);

			customerDao.create(customer);

			request.setAttribute("message", "Customer was successfully created.");

		}
		listCustomer();

	}

	public void registerCustomer() throws ServletException, IOException {

		String email = request.getParameter("email");

		Customer existCustomer = customerDao.findByEmail(email);

		if (existCustomer != null) {
			request.setAttribute("message",
					"Could not register, The email " + email + " is already registered by another customer.");
		}

		else {

			String fullName = request.getParameter("fullName");
			String password = request.getParameter("password");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String zipCode = request.getParameter("zipCode");
			String country = request.getParameter("country");

			Customer customer = new Customer();
			customer.setEmail(email);
			customer.setFullname(fullName);
			customer.setAddress(address);
			customer.setCity(city);
			customer.setCountry(country);
			customer.setZipcode(zipCode);
			customer.setPhone(phoneNumber);
			customer.setPassword(password);

			customerDao.create(customer);

			request.setAttribute("message",
					"Registration was successful! Thank you.<br/><a href='login'>Click " + "here</a> to login");

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/message.jsp");
		dispatcher.forward(request, response);

	}

	public void editCustomer() throws ServletException, IOException {

		Integer customerId = Integer.parseInt(request.getParameter("id"));

		Customer customer = customerDao.get(customerId);

		if (customer == null) {
			request.setAttribute("message",
					"Could not find customer with ID " + customerId + ". It might have been deleted by another admin.");
		} else {

			request.setAttribute("customer", customer);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_form.jsp");
		dispatcher.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException {

		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		Customer customer = customerDao.get(customerId);

		Customer existCustomer = customerDao.findByEmail(request.getParameter("email"));

		if (customer == null) {

			request.setAttribute("message",
					"Could not find customer with ID " + customerId + ". It might have been deleted by another admin.");

		}

		else {

			if (existCustomer != null && existCustomer.getCustomerId() != customerId) {
				request.setAttribute("message", "Could not update the customer Id " + customerId
						+ " because there's an existing customer having the same email");
			}

			else {

				String email = request.getParameter("email");
				String fullName = request.getParameter("fullName");
				String password = request.getParameter("password");
				String phoneNumber = request.getParameter("phoneNumber");
				String address = request.getParameter("address");
				String city = request.getParameter("city");
				String zipCode = request.getParameter("zipCode");
				String country = request.getParameter("country");

				customer.setEmail(email);
				customer.setFullname(fullName);
				customer.setAddress(address);
				customer.setCity(city);
				customer.setCountry(country);
				customer.setZipcode(zipCode);
				customer.setPhone(phoneNumber);
				customer.setPassword(password);
				customer.setCustomerId(customerId);

				customerDao.update(customer);

				request.setAttribute("message", "Customer information was successfully updated");

			}

		}

		listCustomer();

	}

	public void deleteCustomer() throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		Customer customer = customerDao.get(id);

		if (customer == null) {

			request.setAttribute("message",
					"Could not delete Customer with ID " + id + " " + ", It might have been deleted by another admin.");

		}

		else {

			if (!customer.getReviews().isEmpty()) {
				request.setAttribute("message",
						"Could not delete customer with ID " + id + " because he/she posted review for books.");

			}

			else {

				customerDao.delete(id);

				request.setAttribute("message", "Customer was successfully deleted.");

			}
		}

		listCustomer();
	}

	public void showLogin() throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/login.jsp");
		dispatcher.forward(request, response);

	}

	public void doLogin() throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Customer customer = customerDao.checkLogin(email, password);

		if (customer == null) {

			request.setAttribute("message", "Login failed. Please check your email and password");
			showLogin();
		}

		else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);
			
			Object objRedirectURL = session.getAttribute("redirectURL");

			if (objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			}

			else {
  
				showCustomerProfile();

			}
		}

	}

	public void showCustomerProfile() throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/customer_profile.jsp");
		dispatcher.forward(request, response);

	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/edit_profile.jsp");
		dispatcher.forward(request, response);

	}

	public void updateCustomerProfile() throws ServletException, IOException {

		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");

		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");

		customer.setFullname(fullName);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setZipcode(zipCode);
		customer.setPhone(phoneNumber);

		if (password != null && !password.equals("")) {
			customer.setPassword(password);
		}

		customerDao.update(customer);

		showCustomerProfile();

	}

}
