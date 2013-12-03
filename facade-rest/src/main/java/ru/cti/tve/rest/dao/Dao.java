package ru.cti.tve.rest.dao;

import java.util.List;

public interface Dao<T> {
	
	public void create(T entity);
	
	public T find(String id);
	
	public void remove(String id);
			
	public List<T> findAll(int from, int pageSize);
	
	public int getCount();
}
