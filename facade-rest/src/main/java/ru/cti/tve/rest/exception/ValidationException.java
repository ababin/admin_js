package ru.cti.tve.rest.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Map <String,String> errors;
	
	public ValidationException(Map <String, String> errors){
		this.errors = errors;
	}
		
	public ValidationException(String field, String desc){
		errors = new HashMap<String,String>();
		errors.put(field, desc);
	}
	
}
