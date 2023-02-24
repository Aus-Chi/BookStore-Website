package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDao;
	private CategoryDAO categoryDao;

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		super();

		this.request = request;
		this.response = response;
		bookDao = new BookDAO();
		categoryDao = new CategoryDAO();
	}

	public void listBooks() throws ServletException, IOException {

		List<Book> listBook = bookDao.listAll();

		request.setAttribute("listBook", listBook);

		RequestDispatcher dispatcher = request.getRequestDispatcher("book_list.jsp");
		dispatcher.forward(request, response);
	}

	public void newBookForm() throws ServletException, IOException {

		List<Category> listCategory = categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book_form.jsp");
		dispatcher.forward(request, response);

	}

	public void updateBook() throws ParseException, IOException, ServletException {
		System.out.println("done");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		System.out.println(bookId);

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		Date pDate = date.parse(request.getParameter("publishdate"));
		Float price = Float.parseFloat(request.getParameter("price"));
		String description = request.getParameter("description");

		Book book = bookDao.get(bookId);
		Book bookByTitle = bookDao.findByTitle(title);

		if (!(bookByTitle.getTitle().equals(book.getTitle()))) {
			request.setAttribute("message", "Could not update book because a book of same title exists");
			listBooks();
			return;
		}
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishDate(pDate);
		book.setPrice(price);
		book.setTitle(title);

		Category category = categoryDao.get(categoryId);

		book.setCategory(category);

		Part part = request.getPart("bookImage");

		if (part != null && part.getSize() > 0) {

			byte[] image = new byte[(int) part.getSize()];

			InputStream stream = part.getInputStream();
			stream.read(image);
			stream.close();

			book.setImage(image);

		}

		bookDao.update(book);

		request.setAttribute("message", "The book has been updated successfully");
		listBooks();
		System.out.println("done");

	}

	public void createBook() throws ParseException, IOException, ServletException {

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		Date pDate = date.parse(request.getParameter("publishdate"));
		Float price = Float.parseFloat(request.getParameter("price"));
		String description = request.getParameter("description");

		Book book = new Book();
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishDate(pDate);
		book.setPrice(price);
		book.setTitle(title);

		Category category = categoryDao.get(categoryId);

		book.setCategory(category);

		Part part = request.getPart("bookImage");

		if (part != null && part.getSize() > 0) {

			byte[] image = new byte[(int) part.getSize()];

			InputStream stream = part.getInputStream();
			stream.read(image);
			stream.close();

			book.setImage(image);

		}

		Book existBook = bookDao.findByTitle(title);

		if (existBook != null) {

			request.setAttribute("message", "A book with this title already exists");

		}

		else {

			Book createdBook = bookDao.create(book);

			if (createdBook.getBookId() > 0) {

				request.setAttribute("message", "A new book has been created successfully");

			}

		}

		listBooks();

	}

	public void editBook() throws ServletException, IOException {

		List<Category> listCategory = categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);
		Integer id = Integer.parseInt(request.getParameter("id"));

		Book book = bookDao.get(id);

		if (book == null) {
			request.setAttribute("message", "Could not find book with ID " + id);
			listBooks();
			return;
		}

		request.setAttribute("Book", book);

		RequestDispatcher dispatcher = request.getRequestDispatcher("book_form.jsp");
		dispatcher.forward(request, response);

	}

	public void deleteBook() throws ServletException, IOException {

		Integer bookId = Integer.parseInt(request.getParameter("id"));

		Book bookToDelete = bookDao.get(bookId);

		if (bookToDelete == null) {

			request.setAttribute("message",
					"Could not delete book with ID " + bookId + " or it might have been deleted by another admin.");

		}

		else {

			if (!bookToDelete.getReviews().isEmpty()) {
				request.setAttribute("message", "Could not delete book with ID " + bookId + " because it has reviews.");
			}

			else {

				bookDao.delete(bookId);
				request.setAttribute("message", "Book was succesfully deleted");

			}

		}

		listBooks();

	}

	public void listBooksByCategory() throws ServletException, IOException {

		int categoryId = Integer.parseInt(request.getParameter("id"));

		Category category = categoryDao.get(categoryId);

		if (category == null) {

			request.setAttribute("message", "Sorry, the category ID " + categoryId + " is not avaliable.");

		}

		else {

			List<Book> listBooks = bookDao.listByCategory(categoryId);

			request.setAttribute("listBooks", listBooks);
			request.setAttribute("category", category);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/books_list_by_category.jsp");
		dispatcher.forward(request, response);

	}

	public void viewBookDetail() throws ServletException, IOException {

		int bookId = Integer.parseInt(request.getParameter("id"));

		Book book = bookDao.get(bookId);

		if (book == null) {
			request.setAttribute("message", "Sorry, the book with ID " + bookId + " is not avaliable.");
		}

		else {

			request.setAttribute("book", book);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/book_detail.jsp");
		dispatcher.forward(request, response);

	}

	public void search() throws ServletException, IOException {

		String keyword = request.getParameter("keyword");

		List<Book> result = null;

		if (keyword.equals("")) {
			result = bookDao.listAll();
		} else {
			result = bookDao.search(keyword);
			request.setAttribute("keyword", keyword);
		}

		request.setAttribute("result", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/search_result.jsp");
		dispatcher.forward(request, response);
	}

}
