package com.bookstore.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Book create(Book t) {
		t.setLastUpdateTime(new Date());
		return super.create(t);
	}

	@Override
	public Book update(Book t) {
		t.setLastUpdateTime(new Date());
		return super.update(t);
	}

	@Override
	public Book get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Book.class, id);
	}

	@Override
	public void delete(Object id) {
		
		super.delete(Book.class, id);
		
	}

	@Override
	public List<Book> listAll() {
		// TODO Auto-generated method stub
		return super.listAll("Book.findAll");
	}
	
	public Book findByTitle(String title){
		
		List<Book> list = super.findWithNamedQuery("Book.findByTitle", "title", title);
		
		if(!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	public List<Book> listByCategory(int categoryId){
		
		return super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
		
	}
	
	
	public List<Book> listNewBooks(){
		
		return super.findWithNamedQuery("Book.listNew", 0, 4);
		
		
		
	}
	
	public List<Book> search(String keyword){
		
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
		
	}
	
	public long countByCategory(int categoryId) {
		
		return super.count(categoryId, "Book.countByCategory");
		
	}
	
	public List<Book> listBestSellingBooks(){
		
		return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4); 
		
	}
	
	public List<Book> listMostFavoredBooks(){
		
		List<Book> mostFavoredBooks = new ArrayList<>();
		 
		List<Object[]> result = super.findWithNamedQueryObject("Review.mostFavoredBooks", 0, 4);
		
		if(!result.isEmpty()) {
			for(Object[] elements : result) {
				Book book = (Book) elements[0];
				mostFavoredBooks.add(book);
			}
		}
		
		return mostFavoredBooks;
	
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.count("Book.countAll"); 
	}

}
