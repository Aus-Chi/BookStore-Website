package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BaseDAOTest;
import com.bookstore.entity.Book;

public class ShoppingCartTest extends BaseDAOTest{
	
	private static ShoppingCart cart;
	private static Book book;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		cart = new ShoppingCart();
		book = new Book();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testAddItem() {
		
		book.setBookId(14);
		book.setPrice(10);
		cart.addItem(book);
		
		Map<Book, Integer> items = cart.getItems();
		
		int quantity = items.get(book);
		
		assertEquals(1, quantity);
		
		
	}
	
	@Test
	public void testGetTotalAmount() {
		System.out.println(cart.getTotalAmount());
		assertEquals(cart.getTotalAmount(), 10f, 0f);
		
	}
	
//	@Test
//	public void testGetTotalQuantity() {
//		
//		int quantity = cart.getTotalQuantity();
//		System.out.println(quantity);
//		assertTrue(quantity == 1);
//		
//	}

//	@Test
//	public void testGetItems() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testRemoveItem() {
//		//book.setBookId(14);
//		cart.removeItem(book);
//		System.out.println(book.getTitle());
//		assertTrue(cart.getItems().isEmpty());
//		
//	}
//	

}
