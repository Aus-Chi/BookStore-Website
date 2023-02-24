package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {

	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDao;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO(); 
	}
	
	public void listCategory() throws ServletException, IOException {
		
		List<Category> listAll = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listAll);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("category_list.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void createCategory() throws ServletException, IOException {
		
		String name = request.getParameter("name");
		
		if(categoryDAO.findByName(name) != null) {
			
			request.setAttribute("message", "Could not create new Category because category " + name + " already exists");
			
		}
		
		else {
			Category cat = new Category(name);
			categoryDAO.create(cat);
			request.setAttribute("message", "Category created successfully");
			
		}
		
		listCategory();
		
	}

	public void editCategory() throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Category cat = categoryDAO.get(id);
		
		request.setAttribute("cat", cat);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("category_form.jsp");
		dispatcher.forward(request, response);
		
	}

	public void updateCategory() throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String name = request.getParameter("name");
		
		Category cat = categoryDAO.get(id);
		
		if(cat == null) {
			
		}
		
		else {
		
		if(categoryDAO.findByName(name) != null && categoryDAO.findByName(name).getCategoryId() != id) {
			
			request.setAttribute("message", "Category with name " + name + " already exists");
			
		}
		
		else {
			cat.setName(name);
			categoryDAO.update(cat);
			request.setAttribute("message", "Category updated successfully");
		}
		
		}
		
		listCategory();
		
	}

	public void deleteCategory() throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Category cat = categoryDAO.get(id);
		
		long result = bookDao.countByCategory(id);
		
		if(cat == null) {
			
			request.setAttribute("message", "Category with ID " + id + " could not be found");
			
			
		}
		
		
		
		else {
			
			if(result > 0) {
				String message = String.format("%s %d%s", "Could not delete category (ID:", id, ") because some books belong ");
				request.setAttribute("message", message);
						
			}
			
			else {
			
			categoryDAO.delete(id);
			request.setAttribute("message", "Category successfully deleted");
			
			}
			 
		}
		
		listCategory();
		
		
		
	}

}
