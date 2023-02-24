package com.bookstore.dao;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class userDAOTest extends BaseDAOTest{
	
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO();
		
	} 

	@Test
	public void testCreateUsers() {

		Users user1 = new Users();
		
		user1.setEmail("bosco@gmail.com");
		user1.setFullName("Stelien");
		user1.setPassword("Fatman");
		
		user1 = userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test
	public void testCheckLogin() {
		String email = "chiaustin61@gmail.com";
		String password = "Mynameiskeblack";
		
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertTrue(loginResult);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		
		Users user1 = new Users();
		
		user1 = userDAO.create(user1);
		
	}
	
	@Test
	public void testUpdate() {
		Users user1 = new Users();
		
		user1.setUserId(20);
		user1.setEmail("shura@gmail.com");
		user1.setFullName("Shura");
		user1.setPassword("fool");
		
		userDAO.update(user1);
		
		String expected = "fool";
		String actual = user1.getPassword();
		
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void testFindByEmail() {
		
		String email = "55555";
		
		Users user = userDAO.findByEmail(email);
		
		
		
		assertNull(user);
		
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 7;
		Users user = userDAO.get(userId);
		if(user!=null) {
		System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testDeleteUSer() {
		
		Integer userId = 7;
		
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);
		
	}
	
	@Test 
	public void testListAll() {
		
		List<Users> listUsers = userDAO.listAll();
		
		for(Users u : listUsers) {
			System.out.println(u.getEmail());
		}
		
		assert(listUsers.size() > 0);
		
	}
	
	@Test
	public void testCount() {
		
		long totalUsers = userDAO.count();
	
		assert(totalUsers != 11);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
		
	}
	
	

}
