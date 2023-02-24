package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class ShoppingCart {
	
	private Map<Book, Integer> cart = new HashMap<>();
	
	public void addItem(Book book) {
		
		if(cart.containsKey(book)) {
			Integer quantity = cart.get(book) + 1;
			cart.put(book, quantity);
		}
		else {
			
			cart.put(book, 1);
			
		}
		
	}
	
	public Map<Book, Integer> getItems() {
		
		return this.cart;
		
	}
	
	public void removeItem(Book book) {
		
		cart.remove(book);
		
	}
	
	public float getTotalAmount() {
		
		float total = 0.0f;
		
		Iterator<Book> iterator = cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Book next = iterator.next();
			double price = next.getPrice();
			Integer quantity = cart.get(next);
			total += quantity * price;
		}
		
		return total;
		
	}
	
	public void updateCart(int[] bookIds, int[] quantities) {
		
		for(int i = 0; i < bookIds.length; i++) {
			
			Book key = new BookDAO().get(bookIds[i]);
			Integer value = quantities[i];
			
				cart.put(key, value);
			
		}
		
	}
	
	public int getTotalQuantity() {
		
		int total = 0;
		
		Iterator<Book> iterator = cart.keySet().iterator();
		
		while(iterator.hasNext()) {
			Book next = iterator.next();
			Integer quantity = cart.get(next);
			total += quantity;
		}
		
		
		return total;
		
	}
	
	public int getTotalItems() {
		
		return cart.size();
		
	}
	
	public void clear() {
		cart.clear();
	}

}
