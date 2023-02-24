package com.bookstore.dao;

import java.util.List;


import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Category create(Category t) {
		
		return super.create(t);
	}

	@Override
	public Category update(Category t) {
		
		return super.update(t);
	}

	@Override
	public Category get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
		
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.listAll("Category.findAll");
		
		
	}
	
	public Category findByName(String name) {
		
		List<Category> list = super.findWithNamedQuery("Category.findByName", "name", name);
		
		if(list != null && list.size()==1) {
			return list.get(0);
		}
		
		return null;
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.count("Category.countAll");
	}

}
