package ru.cti.tve.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.cti.tve.rest.api.ResultCollection;
import ru.cti.tve.rest.dao.Dao;
import ru.cti.tve.rest.exception.IdGenerateException;
import ru.cti.tve.rest.exception.ValidationException;
import ru.cti.tve.rest.serviceutil.IdGenerator;
import ru.cti.tve.rest.transformer.base.Transformer;
import ru.cti.tve.rest.transformer.base.TransformerFactory;

public abstract class BaseService<T> implements  EntityService{

	protected static final boolean FOR_GET_COUNT = true;
	protected static final boolean FOR_GET_LIST = false;
	
	private Class<T> clazz;
		
	private Transformer<T> transformer;
					
	@Autowired
	private IdGenerator idGenerator;
	
	@SuppressWarnings("unchecked")
	protected BaseService(Class<T> entityClass){
		clazz = entityClass;
		transformer = TransformerFactory.create(clazz);
	};
		
	@Override
	@Transactional(readOnly=true)
	public ResultCollection readCollection(int pageNum,int pageSize) {
		ResultCollection res = new ResultCollection(); 
		res.page = pageNum;
		res.pageSize = pageSize;
		
		// get total count
		res.totalCount = getDao().getCount();
		int from = (pageNum-1) * pageSize;
		List <T> domainList =  getDao().findAll(from, pageSize);
		
		List<Object> entities = new ArrayList<Object>();
		for(T domainEntity : domainList){
			entities.add(transformer.toExternal(domainEntity));
		}
		
		res.list = entities;
		
		return res;
	}
		
	@Override
	@Transactional
	public String create(Map<String, Object> params) throws ValidationException, IdGenerateException {
		String newId = prepareId((String) params.get("id"), clazz);  
		params.put("id", newId);
		T entity = transformer.toInternal(params);
		getDao().create(entity);		
		
		return newId;
	}

	@Override
	@Transactional
	public void delete(String id) {
		getDao().remove(id);
	}

	@Override
	public Map<String, Object> update(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> read(String id) {
		T ob = find(id);
		if(ob != null){
			return transformer.toExternal(ob);
		}
		return null;
	}
	
	protected String prepareId(String id, Class<T> entity) throws ValidationException, IdGenerateException{
		if(id == null){
			return idGenerator.getNextId(entity);
		}
		if(idGenerator.isAlreadyExists(id, entity)){
			throw new ValidationException("id","UNIQUE");
		}
		return id;
	}
	
	@Transactional
	private T find(String id){
		return getDao().find(id);
	}
	
	protected abstract Dao<T> getDao();
	

}
