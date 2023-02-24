package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest extends BaseDAOTest{
	
	private static ReviewDAO reviewDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		reviewDao = new ReviewDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
		
	}

	@Test
	public void testCreateReview() {
		
		Review review = new Review();
		Book book = new Book();
		book.setBookId(17);
		Customer customer = new Customer();
		customer.setCustomerId(6);
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("Not a good book");
		review.setRating(2.5f);  
		review.setComment("It lacks details which makes it a good book for professionals and not for beginners");
		
		Review savedReview = reviewDao.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	}

	@Test
	public void testGetObject() {
		
		Integer reviewId = 5;
		
		Review review = reviewDao.get(reviewId);
		
		assertTrue(review.getCustomer().getCustomerId() == 3);
		
	}
	
	@Test
	public void testUpdateReview() {
		
		Integer reviewId = 1;
		
		Review review = reviewDao.get(reviewId);
		
		review.setHeadline("Excellent Book");
		
		Review updatedReview = reviewDao.update(review);
		
		assertEquals(updatedReview.getHeadline(), "Excellent Book");
		
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {

		List<Review> result = reviewDao.listAll();
		assertTrue(result.size()==1);

	}

	@Test
	public void testCount() {
		long result = reviewDao.count();
		assertTrue(result > 0);
	}
	
	@Test
	public void testFindByCustomerAndBook() {
		 
		Integer customerId = 3;
		Integer bookId = 14;
		
		Review result = reviewDao.findByCustomerAndBook(customerId, bookId);
		
		assertNotNull(result);
		
	}

}
