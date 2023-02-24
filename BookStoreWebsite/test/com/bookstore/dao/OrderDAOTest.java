package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest extends BaseDAOTest{
	
	private static OrderDAO orderDao;
	private static Book book;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		orderDao = new OrderDAO();
		book = new Book();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
		
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		
		customer.setCustomerId(3);
		
		order.setCustomer(customer);
		order.setRecipientName("Chi AUstin");
		order.setRecipientPhone("5145820401");
		order.setShippingAddress("553 radiant pvt");  
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		
		
		book.setBookId(14);
		
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(60.5f);
		orderDetail.setBookOrder(order);
		
		
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		BookOrder savedOrder = orderDao.create(order);
		
		assertNotNull(savedOrder);
	}

	@Test
	public void testGetObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		orderDao.delete(6);
		BookOrder order = orderDao.get(6);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
	 int count = (int) orderDao.count();
	 
	 assertTrue(count == 0);
	}
	
	@Test
	public void testUpdateOrderShippingAddress() {
		
		BookOrder order = orderDao.get(6);
		order.setShippingAddress("New Shipping Address");
		
		orderDao.update(order);
		
		BookOrder updatedOrder = orderDao.get(6);
		
		assertEquals(order.getShippingAddress(), updatedOrder.getShippingAddress());
		
	}

}
