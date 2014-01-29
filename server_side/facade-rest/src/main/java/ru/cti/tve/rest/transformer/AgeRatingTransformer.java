package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.common.Rating;
import ru.cti.oss.iptv.common.RatingSystem;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("ageRatingTransformer")
public class AgeRatingTransformer extends Transformer<Rating> {
	
	@Override
	public Map<String,Object> toExternal(Rating domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		map.put("id",toExternalId(domain));		
		map.put("code", domain.getCode());
		map.put("i18nName", toExternalI18n(domain.getI18nName()));
		map.put("i18nDesc", toExternalI18n(domain.getI18nDescription()));
		map.put("level", domain.getLevel());
		map.put("ratingSystem", domain.getSystem().getId());
		return map;
	}

	@Override
	public Object toExternalId(Rating domain) {
		return domain.getExternalId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Rating toInternal(Map<String, Object> map) {
		Rating r = new Rating();
						
		r.setCode((String) map.get("code"));
		r.setDescription(getValueForDefaultLocale((Map<String, Object>) map.get("i18nDesc")));
		r.setExternalId((String) map.get("id"));
		r.setI18nDescription(toInternalI18n((Map<String, String>) map.get("i18nDesc")));
		r.setI18nName(toInternalI18n((Map<String, String>) map.get("i18nName")));
		r.setId(null);
		r.setLevel((Long) map.get("level"));
		r.setName(getValueForDefaultLocale((Map<String, Object>) map.get("i18nName")));
		
		RatingSystem rs = new RatingSystem();
		rs.setId((Long) map.get("ratingSystem"));
		r.setSystem(rs);
		
		return r;
	}
	
	
}
