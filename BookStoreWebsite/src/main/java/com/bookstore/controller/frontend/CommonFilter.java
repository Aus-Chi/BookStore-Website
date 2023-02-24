package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

/**
 * Servlet Filter implementation class CommonFilter
 */
@WebFilter("/*")
public class CommonFilter implements Filter {
	
	private CategoryDAO categoryDao;

    /**
     * Default constructor. 
     */
    public CommonFilter() {
    	
        categoryDao = new CategoryDAO();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		if(!path.startsWith("/admin/")) {
		
		List<Category> listCategory = categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);
		
		}
		
		chain.doFilter(request, response);
	}
 
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
