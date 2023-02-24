package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;



public class CustomerDAOTest {

	private static CustomerDAO customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		customerDao = new CustomerDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("austin@gmail.com");
		customer.setFullname("Austin Chi");
		customer.setCity("Ottawa");
		customer.setCountry("Canada");
		customer.setAddress("553 radiant pvt.");
		customer.setPassword("Mynameiskeblack6");
		customer.setPhone("5145820401");
		customer.setZipcode("K2M 0M7");
		
		Customer savedCustomer = customerDao.create(customer);
		
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDao.get(1);
		customer.setFullname("Tom Tom");
		customerDao.update(customer);
		
		assertTrue(customer.getFullname().equals("Tom Tom"));
	}

	@Test
	public void testGetObject() {
		Integer id = 1;
		Customer customer = customerDao.get(id);
		
		assertTrue(customer.getCustomerId() == 1);
	}

	@Test(expected=Exception.class)
	public void testDeleteCustomer() {
		
		customerDao.delete(1);
		
		Customer customer = customerDao.get(1);
		assertNull(customer);
	}

	@Test
	public void testListAll() {
		List<Customer> result = customerDao.listAll();
		
		assertTrue(result.size() == 2);
	}
	@Test
	public void testFindByEmail() { 
		 
		Customer customer = customerDao.findByEmail("tom@gmail.com");
		assertTrue(customer.getEmail().equals("tom@gmail.com"));
		
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
