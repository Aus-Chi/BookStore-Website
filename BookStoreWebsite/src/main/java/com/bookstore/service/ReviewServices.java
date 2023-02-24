package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {

	private ReviewDAO reviewDao;
	private BookDAO bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.reviewDao = new ReviewDAO();
		this.request = request;
		this.response = response;
	}

	public void listAllReview() throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Review> listReviews = reviewDao.listAll();

		request.setAttribute("listReviews", listReviews);

		RequestDispatcher dispatcher = request.getRequestDispatcher("review_list.jsp");
		dispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {

		Integer reviewId = Integer.parseInt(request.getParameter("id"));

		Review review = reviewDao.get(reviewId);

		if (review == null) {
			request.setAttribute("message",
					"Could not find review with ID " + reviewId + ". It might have been deleted by another admin.");

			listAllReview();

		}

		else {

			request.setAttribute("review", review);

			RequestDispatcher dispatcher = request.getRequestDispatcher("review_form.jsp");
			dispatcher.forward(request, response);

		}
	}

	public void updateReview() throws ServletException, IOException {

		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDao.get(reviewId);

		review.setHeadline(request.getParameter("headline"));
		review.setComment(request.getParameter("comment"));

		reviewDao.update(review);

		request.setAttribute("message", "Review successfully updated");

		listAllReview();

	}

	public void deleteReview() throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		Review review = reviewDao.get(id);

		if (review == null) {

			request.setAttribute("message",
					"Could not find review with ID " + id + ", or it might have been deleted by another admin.");
			;

		}

		else {

			reviewDao.delete(id);

			request.setAttribute("message", "The review was successfully deleted.");

		}

		listAllReview();

	}

	public void showReviewForm() throws ServletException, IOException {

		Integer bookId = Integer.parseInt(request.getParameter("book_id"));

		Book book = new BookDAO().get(bookId);

		request.setAttribute("book", book);
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		
		Review existReview = reviewDao.findByCustomerAndBook(customer.getCustomerId(), bookId);

		if(existReview != null) {
			request.setAttribute("review", existReview);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/review_info.jsp");
			dispatcher.forward(request, response);
			
		}
		
		else {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/review_form.jsp");
		dispatcher.forward(request, response);
		
		}
	}

	public void submitReview() throws ServletException, IOException {

		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		
		float rating = Float.parseFloat(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review newReview = new Review();
		newReview.setHeadline(headline);
		newReview.setComment(comment);
		newReview.setRating(rating);
		
		Book book = new Book();
		book.setBookId(bookId);
		newReview.setBook(book);
		
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);
		
		reviewDao.create(newReview);
		
		book = new BookDAO().get(bookId);
		
		request.setAttribute("book", book);
		
		request.setAttribute("message", "Your review was successfully created.");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/review_done.jsp");
		dispatcher.forward(request, response);
	}

}
