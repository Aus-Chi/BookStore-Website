package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	@Override
	public Customer create(Customer t) {
		t.setRegisterDate(new Date()); 
		return super.create(t);
	}

	@Override
	public Customer update(Customer t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public Customer get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Customer.class, id);
		
	}

	@Override
	public List<Customer> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Customer.findAll");
	} 
	
	public Customer findByEmail(String email) {
		
		List<Customer> result = super.findWithNamedQuery("Customer.findByEmail", "email", email);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0; 
	}
	
	public Customer checkLogin(String email, String password) {
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Customer> result = super.findWithNamedQuery("Customer.checkLogin", parameters);
		
		if(!result.isEmpty()) {
			return result.get(0); 
		}
		
		return null;
		
	} 

}
