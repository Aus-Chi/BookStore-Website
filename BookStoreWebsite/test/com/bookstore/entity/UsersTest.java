package com.bookstore.entity;

import com.bookstore.entity.Users;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Users user1 = new Users();
		
		user1.setEmail("harri@yahoo.com");
		user1.setFullName("Harri");
		user1.setPassword("Gandhi");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		
		EntityManager entityManager  = entityManagerFactory.createEntityManager();
	
		entityManager.getTransaction().begin();
		
		entityManager.persist(user1);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("A Users object was persisted");
	}

}


