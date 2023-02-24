package com.bookstore.entity;
import com.bookstore.entity.Category;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Category category1 = new Category("Java How to Program");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(category1);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}

}
