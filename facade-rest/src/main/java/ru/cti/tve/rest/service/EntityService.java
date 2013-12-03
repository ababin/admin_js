package ru.cti.tve.rest.service;

import java.util.Map;

import ru.cti.tve.rest.api.ResultCollection;
import ru.cti.tve.rest.exception.IdGenerateException;
import ru.cti.tve.rest.exception.ValidationException;

public interface EntityService {

	String create(Map <String,Object> params) throws ValidationException, IdGenerateException;
	
	void delete(String id);
	
	Map <String,Object> update(Map <String,Object> params);
	
	Map <String,Object> read(String id);
	
	public ResultCollection readCollection(int pageNum,int pageSize);
			
}
