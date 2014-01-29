package ru.cti.tve.rest.exception;

import java.util.HashMap;
import java.util.Map;

public class IdGenerateException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Map <String,String> errors;
	
	public IdGenerateException(Map <String, String> errors){
		this.errors = errors;
	}
		
	public IdGenerateException(String field, String desc){
		errors = new HashMap<String,String>();
		errors.put(field, desc);
	}
	
}
