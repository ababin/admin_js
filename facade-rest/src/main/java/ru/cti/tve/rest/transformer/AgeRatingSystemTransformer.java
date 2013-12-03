package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.common.RatingSystem;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("ageRatingSystemTransformer")
public class AgeRatingSystemTransformer extends Transformer<RatingSystem> {
	
	@Override
	public Map<String,Object> toExternal(RatingSystem domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		map.put("id",toExternalId(domain));		
		map.put("name", domain.getName());
		map.put("desc", domain.getDescription());
		return map;
	}

	@Override
	public Object toExternalId(RatingSystem domain) {
		return domain.getId();
	}

	@Override
	public RatingSystem toInternal(Map<String, Object> map) {
		RatingSystem r = new RatingSystem();
		
		r.setDescription((String) map.get("desc"));
		r.setId((Long) map.get("id"));
		r.setName((String) map.get("name"));
		
		return r;
	}
	
	
}
