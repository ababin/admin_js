package ru.cti.tve.rest.aop;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class ControllerAspect {

	@Around("execution(public * *..BtvChannelController.create(..) || execution(public * *..BtvChannelController.update(..)")
	public Object normalizeInputDatas(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Map <String,Object> map =  (Map<String, Object>) proceedingJoinPoint.getArgs()[0];
		
        // 1. normalize data
        normalizeData(map);
        
        // 2. validate data
        validateData(map);
        
        return proceedingJoinPoint.proceed();
        
		        
        
        
    }
	
	private void normalizeData(Object data){
		if(data == null){
			return;
		}
		
		if(data instanceof Map){
			Map <String, Object> mapData = (Map<String, Object>) data;
			for(Entry <String,Object> entry : mapData.entrySet()){
				if(entry.getValue() != null){
					normalizeData(entry.getValue());
				}
			}
		}else if(data instanceof Collection){
			Collection<Object> colData = (Collection<Object>) data;
			for(Object obData : colData){
				normalizeData(obData);
			}
		}else if(data instanceof String){
			data = String.valueOf(data).trim();
			if(((String) data).isEmpty()){
				data = null;
			}
		}
	}
	
	private void validateData(Object data){
		
	}
	
	
	
	
	
}
