package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review; 

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {
	
	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}
	
	@Override
	public Review update(Review review) {
		return super.update(review);
	}

	@Override
	public Review get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Review.class, id);
	}

	@Override
	public void delete(Object id) {

		super.delete(Review.class, id);
		
	}

	@Override
	public List<Review> listAll() {
		// TODO Auto-generated method stub
		return super.listAll("Review.listAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.count("Review.countAll");
	}
	
	public Review findByCustomerAndBook(Integer customerId, Integer bookId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("customerId", customerId);
		parameters.put("bookId", bookId);
		
		List<Review> result = super.findWithNamedQuery("Review.findByCustomerAndBook", parameters);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		
		else {
			return null;
		}
		
		
	}
	
	public List<Review> listMostRecent(){
		
		return super.findWithNamedQuery("Review.listAll", 0, 3);
		
	}

}
