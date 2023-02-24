package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder order) {
		order.setOrderDate(new Date());
		order.setPaymentMethod("Cash on Delivery");
		order.setStatus("Processing");
		return super.create(order);
	}
	@Override
	public BookOrder get(Object id) {
		// TODO Auto-generated method stub
		return super.get(BookOrder.class, id);
	}
	
	public BookOrder get(Integer orderId, Integer customerId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		List<BookOrder> result = super.findWithNamedQuery("BookOrder.findByIdAndCustomer", parameters);
	
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}


	@Override
	public void delete(Object id) {
		
		super.delete(BookOrder.class, id);
		
	}
	
	@Override
	public BookOrder update(BookOrder order) {
		return super.update(order);
	}

	@Override
	public List<BookOrder> listAll() {
		// TODO Auto-generated method stub
		return super.listAll("BookOrder.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.count("BookOrder.countAll");
	}
	
	public List<BookOrder> listByCustomer(Integer customerId){
		
		return super.findWithNamedQuery("BookOrder.findByCustomer", "customerId", customerId);
		
	}
	
	public List<BookOrder> listMostRecentSales(){
		
		return super.findWithNamedQuery("BookOrder.findAll", 0, 3);
		
	}

}
