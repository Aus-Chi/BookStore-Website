package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest {
	
	private static BookDAO bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		BaseDAOTest.setUpBeforeClass();
		bookDao = new BookDAO();
		
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		
		Integer id = 8;
		bookDao.delete(id);
		
	}
	
	@Test
	public void testGetBook() {
		
		Integer id = 11;
		assertNull(bookDao.get(id));
		
	}
	
	@Test
	public void testListAll() {
		
		List<Book> list = bookDao.listAll();
		
		for(Book book : list) {
			System.out.println(book.getAuthor());
		}
		
		assertTrue(!list.isEmpty());
		
	}
	
	@Test
	public void testCount() {
		
		int books = (int) bookDao.count();
		
		assertEquals(books, 1);
		
	}
	
	@Test
	public void testfindByTitle() {
		
		Book list = bookDao.findByTitle("Effective Java (3rd Edition)");
		
		assertEquals(list.getBookId(), 8);
		
	}
	
	@Test
	public void testListMostFavoredBooks() {
		List<Book> mostFavoredBooks = bookDao.listMostFavoredBooks();
		for(Book book : mostFavoredBooks) {
			System.out.print(book.getTitle());
		}
		
		assertEquals(mostFavoredBooks.size(), 4);
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		
		Book book = new Book();
		book.setBookId(8);
		Category cat = new Category("Computer Science");
		cat.setCategoryId(1);
		book.setCategory(cat);
		book.setTitle("Effective Java (3rd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more");
				
		book.setPrice(40f);
		book.setIsbn("0321356683");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date pDate = dateFormat.parse("05/28/2008");
		
		book.setPublishDate(pDate);
		
		String imagePath = "C:\\Users\\chiau\\My Bookstore Project\\BookStoreWebsite\\books\\Effective Java.jpg";
		
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageByte);
		Book updatedBook = bookDao.update(book);
		assertEquals(updatedBook.getCategory().getCategoryId(), book.getCategory().getCategoryId());
		
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		
		Book book = new Book();
		
		Category cat = new Category("Programming");
		cat.setCategoryId(7);
		book.setCategory(cat);
		book.setTitle("Effective Java (2nd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more");
				
		book.setPrice(38.87f);
		book.setIsbn("0321356683");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date pDate = dateFormat.parse("05/28/2008");
		
		book.setPublishDate(pDate);
		
		String imagePath = "C:\\Users\\chiau\\My Bookstore Project\\BookStoreWebsite\\books\\Effective Java.jpg";
		
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageByte);
		Book createdBook = bookDao.create(book);
		assertTrue(createdBook.getBookId() > 0);
		
	}
	
	@Test
	public void testSearchBookInTitle() {
		
		String keyword = "Java";
		List<Book> result = bookDao.search(keyword);
		
		
		assertTrue(result.size() == 6);
		
	}
	
	@Test
	public void testSearchBookInAuthor() {
		
		String keyword = "Bloch";
		
		List<Book> result = bookDao.search(keyword);
		
		assertTrue(result.size()==1);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
