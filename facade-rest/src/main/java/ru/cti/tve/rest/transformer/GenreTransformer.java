package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.common.Category;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("genreTransformer")
public class GenreTransformer extends Transformer<Category> {
		
	@Override
	public Map<String,Object> toExternal(Category domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		
		map.put("id",toExternalId(domain));
		map.put("i18nName",toExternalI18n(domain.getI18nName()));
		map.put("i18nDesc",toExternalI18n(domain.getI18nDescription()));
		return map;
	}
	
	@Override
	public Object toExternalId(Category domain) {
		return domain.getExternalId();
	}

	@Override
	public Category toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
