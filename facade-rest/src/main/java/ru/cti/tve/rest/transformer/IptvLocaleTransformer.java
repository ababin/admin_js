package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.cbe.location.Locale;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("iptvLocaleTransformer")
public class IptvLocaleTransformer extends Transformer<Locale> {
		
	@Override
	public Map<String,Object> toExternal(Locale domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		
		map.put("id",toExternalId(domain));		
		map.put("code", domain.getCode()); 
		map.put("name", domain.getName());
		return map;
	}
	
	@Override
	public Object toExternalId(Locale domain) {
		return domain.getExternalId();
	}

	@Override
	public Locale toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
