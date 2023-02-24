package com.bookstore.dao;

import static org.junit.Assert.*;


import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;


public class CategoryDAOTest extends BaseDAOTest{
	
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		
		Category newCat = new Category("Health");
		
		Category category = categoryDAO.create(newCat);
		
		assertTrue(category != null && category.getCategoryId()!=0);
		
	}

	@Test
	public void testUpdateCategory() {
		
		Category cat = new Category("Java Core");
		cat.setCategoryId(1);
		Category category = categoryDAO.update(cat);
		
		assertEquals(category.getName(), cat.getName());
		
	}

	@Test
	public void testGetObject() {
		
		Category cat = categoryDAO.get(10);
		
		assertEquals("Java Core", cat.getName());
		
	}

	@Test
	public void testDeleteObject() {
		
		categoryDAO.delete(3);
		
		assertNull(categoryDAO.get(3));
		
	}

	@Test
	public void testListAll() {
		java.util.List<Category> list = categoryDAO.listAll();
		list.forEach(c -> System.out.println(c.getName()));
		assert(list.size()>2);
	}

	@Test
	public void testCount() {
		
		assertEquals(5, categoryDAO.count());
	}

}
