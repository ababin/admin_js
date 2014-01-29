package ru.cti.tve.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao<T> implements Dao<T> {
	
	@PersistenceContext(unitName = "iptvmw")
    protected EntityManager emanager;
	
	private Class<T> clazz ;
		
	/*
	 * Constructor for getting Type
	 */
	protected BaseDao(Class<T> clazz){
		this.clazz = clazz;
	}
	
	@Override
	@Transactional
	public void create(T entity) {
		emanager.persist(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public T find(String id) {
		@SuppressWarnings("unchecked")
		List <T> items = emanager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e WHERE externalId=:id").setParameter("id", id).getResultList();
		if(!items.isEmpty()){
			return items.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void remove(String id) {
		Object ob = find(id);
		if(ob != null){
			emanager.remove(ob);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> findAll(int from, int pageSize) {
		return emanager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").setFirstResult(from).setMaxResults(pageSize).getResultList();
	}
	
	@Override
	@Transactional
	public int getCount(){
		return Integer.valueOf(emanager.createQuery("SELECT count(e) FROM " + clazz.getSimpleName() + " e").getSingleResult().toString());
	}
	
}
