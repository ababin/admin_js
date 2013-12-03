package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.Region;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("regionTransformer")
public class RegionTransformer extends Transformer<Region> {
		
	@Override
	public Map<String,Object> toExternal(Region domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		
		
		map.put("id",toExternalId(domain));
		map.put("name",domain.getName());
		map.put("desc",domain.getDescription());
		map.put("code",domain.getCode());
		return map;
	}
	
	@Override
	public Object toExternalId(Region domain) {
		// TODO: должен быть externalId , а его в модели еще нет ...
		return domain.getId();
	}

	@Override
	public Region toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
