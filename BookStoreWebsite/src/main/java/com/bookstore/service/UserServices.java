package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

import javax.persistence.EntityManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServices {

	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices() {
	};

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		userDAO = new UserDAO();
	}

	public void listUser() throws ServletException, IOException {

		List<Users> listUsers = userDAO.listAll();

		request.setAttribute("listUser", listUsers);

		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);

	}

	public void createUser() throws ServletException, IOException {

		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		if (userDAO.findByEmail(email) != null) {

			request.setAttribute("message", "Could not create user, email " + email + " already exists");
			
		}
    
		else {   
 
			request.setAttribute("message", "New user created successfully");

			Users newUser = new Users(email, fullName, password);

			userDAO.create(newUser);

		}
		
		listUser();     

	}

	public void editUser() throws ServletException, IOException {

		Integer userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);  

		String editPage = "user_form.jsp";
   
		request.setAttribute("user", user);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);

		requestDispatcher.forward(request, response);

	}

	public void updateUser() throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users user = new Users(id, email, fullName, password);

		Users user1 = userDAO.get(id);

		if (user1 == null) {

			request.setAttribute("message", "Could not find user with ID " + id);

		}

		else {

			if (userDAO.findByEmail(email) != null && userDAO.findByEmail(email).getUserId() != id) {
				request.setAttribute("message", "User with email " + email + " already exist");
			}

			else {

				userDAO.update(user);
				request.setAttribute("message", "User has been updated successfully");
			}

		}

		listUser();

	}

	public void deleteUser() throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("id"));

		Users user1 = userDAO.get(userId);

		if (user1 == null) {

			request.setAttribute("message",
					"Could not find user with ID " + userId + ", or it might have been deleted by another admin.");
 
		}

		else {

			userDAO.delete(userId);

			request.setAttribute("message", "User has been deleted successfully");

		}

		listUser();
	}  
	
	public void login() throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		String password = request.getParameter("password");
		
		boolean loginResult = userDAO.checkLogin(email, password);
		 
		if(loginResult) {
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}
		
		else {
			String message = "Login failed";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
