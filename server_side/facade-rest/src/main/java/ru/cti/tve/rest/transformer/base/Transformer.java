package ru.cti.tve.rest.transformer.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import ru.cti.oss.iptv.common.Category;

public abstract class Transformer<T>{
			
	public abstract Map<String,Object> toExternal(T domain);
	public abstract Object toExternalId(T domain);
	public abstract T toInternal(Map <String,Object> map);
	
	public List<Map<String,Object>> toExternalList(Collection<T> domains){
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		for(T domain : domains){
			res.add(toExternal(domain));
		}
		return res;
	}
	
	public List<Object> toExternalIdList(Collection<T> domains){
		List<Object> res = new ArrayList<Object>();
		for(T domain : domains){
			res.add(toExternalId(domain));
		}
		return res;
	}
	
	protected Map <String, String> toExternalI18n(Map <Locale, String> mapIn){
		Map <String, String> res = new HashMap<String, String>();
		for(Entry<Locale, String> entry : mapIn.entrySet()){
			res.put(localeToString(entry.getKey()), entry.getValue());
		}
		return res;
	}
	
	protected Map <Locale, String> toInternalI18n(Map <String, String> mapIn){
		Map <Locale, String> res = new HashMap<Locale, String>();
		for(Entry<String, String> entry : mapIn.entrySet()){
			res.put(stringToLocale(entry.getKey()), entry.getValue());
		}
		return res;
	}
	
	protected List <String> toExternalCategories(ru.cti.oss.iptv.resource.logical.BtvChannel domain){
		List <String> res = new ArrayList <String> ();
		for(Category cat : domain.getCategories()){
			res.add(cat.getExternalId());
		}
		return res;
	}
		
	protected String localeToString(Locale locale){
		return locale.getLanguage() + "_" + locale.getCountry();
	}
	
	protected Locale stringToLocale(String localeString){
		return new Locale(localeString.substring(0,2), localeString.substring(3,5));
	}
	
	protected String getValueForDefaultLocale(Map<String, Object> map){
		String localeString = "ru_RU";
		if(map != null && map.get(localeString)!=null){
			return String.valueOf(map.get(localeString));
		}
		return null;
	}
	
	
}
