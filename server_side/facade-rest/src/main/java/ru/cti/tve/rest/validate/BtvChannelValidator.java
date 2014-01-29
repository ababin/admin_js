package ru.cti.tve.rest.validate;

import java.util.HashMap;
import java.util.Map;

import ru.cti.tve.rest.exception.ValidationException;

public abstract class BtvChannelValidator {
		
	
	public static void validate(Map<String, Object> map) throws ValidationException {
		
		Map<String,String> errors = new HashMap<String,String>();
		
		// 1.  
		if(map.get("sortOrder") == null){
			errors.put("sortOrder", "REQUIRED");
		}
						
		if(!errors.isEmpty()){
			throw new ValidationException(errors);
		}
		
	}
	
}
