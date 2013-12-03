package ru.cti.tve.rest.api;

import java.io.Serializable;
import java.util.List;

public class ResultCollection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int page;
	
	public int pageSize;
	
	public int totalCount;
	
	public List <Object> list;
		
}
