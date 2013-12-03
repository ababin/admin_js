package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.physical.BandwidthRating;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("bandwidthRatingTransformer")
public class BandwidthRatingTransformer extends Transformer<BandwidthRating> {
		
	@Override
	public Map<String,Object> toExternal(BandwidthRating domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		
		map.put("id",toExternalId(domain));
		map.put("name",domain.getName());
		map.put("desc",domain.getDescription());
		map.put("code",domain.getCode());
		map.put("level",domain.getLevel());
		return map;
	}
	
	@Override
	public Object toExternalId(BandwidthRating domain) {
		// TODO: должен быть externalId , а его в модели еще нет ...
		return domain.getId();
	}

	@Override
	public BandwidthRating toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
