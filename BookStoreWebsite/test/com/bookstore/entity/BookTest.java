package com.bookstore.entity;
import com.bookstore.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Book book1 = new Book();
		
		book1.setAuthor("Austin Maths made easy");
		book1.setPrice(100);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(book1);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
	}

}
