package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Users;

import javax.persistence.EntityManager;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	 
	
	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users create(Users user) {
		
		return super.create(user);
	}

	@Override
	public Users update(Users t) {
		
		return super.update(t);
	}

	@Override
	public Users get(Object id) {
		
		return super.get(Users.class, id);
	}
	
	public Users findByEmail(String email) {
		
		List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
		
		if(listUsers != null && listUsers.size() == 1) {
			return listUsers.get(0);
		}
		
		return null; 
		
	}
	
	public boolean checkLogin(String email, String password) {
		
		Map<String, Object> parameters = new HashMap<>();
		
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters);
		
		if(listUsers.size() == 1) {
			return true;
		}
		
		return false;
		
	}

	@Override
	public void delete(Object id) {
		super.delete(Users.class, id);
		
	}

	@Override
	public List<Users> listAll() {
		
		return super.listAll("Users.findAll");
	}

	@Override
	public long count() {
		
		return super.count("Users.countAll");
	}
	
	

}
