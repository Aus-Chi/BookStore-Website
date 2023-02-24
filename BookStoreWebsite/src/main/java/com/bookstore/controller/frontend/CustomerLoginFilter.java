package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.bookstore.entity.Category;

/**
 * Servlet Filter implementation class CustomerLoginFilter
 */
@WebFilter("/*")
public class CustomerLoginFilter implements Filter {

	private static final String[] loginRequiredURLs = { "/view_profile", "/write_review", "/edit_profile", "/update_profile", "/checkout", "/place_order", "/view_orders", "/show_order_detail" };

	/**
	 * Default constructor.
	 */
	public CustomerLoginFilter() {
		// TODO Auto-generated constructor stub
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

		if (path.startsWith("/admin/")) {

			chain.doFilter(request, response);
			return;  

		}

		String requestURL = httpRequest.getRequestURL().toString();

		System.out.println(path);
		if (!loggedIn && isLoginRequired(requestURL)) {
			String queryString = httpRequest.getQueryString();
			String redirectURL = requestURL;
			if(queryString != null) {
				redirectURL = redirectURL.concat("?").concat(queryString);
			}
			
			session.setAttribute("redirectURL", redirectURL);

			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/frontend/login.jsp");
			dispatcher.forward(httpRequest, response);
		}

		else {     

			chain.doFilter(request, response);

		} 
	}

	private boolean isLoginRequired(String requestURL) {

		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
