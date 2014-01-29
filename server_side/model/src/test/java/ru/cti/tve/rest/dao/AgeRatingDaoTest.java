package ru.cti.tve.rest.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import ru.cti.oss.iptv.common.Rating;
import ru.cti.oss.iptv.common.RatingSystem;
import ru.cti.tve.rest.BaseTest;
import ru.cti.tve.rest.transformer.base.Transformer;

public class AgeRatingDaoTest extends BaseTest{
	
	@Autowired
	@Qualifier("ageRatingDao")
	private Dao<Rating> dao;
				
	@Test
	public void test_toExternal(){
		dao.find(id);
		
		Rating r = getRating();
		Map <String,Object> ob = transformer.toExternal(r);
		assertTrue(ob.get("id").equals(r.getExternalId()));
		assertTrue(ob.get("code").equals(r.getCode()));
		assertTrue(ob.get("level").equals(r.getLevel()));
		assertTrue(ob.get("ratingSystem").equals(r.getSystem().getId()));
		assertLocales(r.getI18nName(), (Map<String, Object>) ob.get("i18nName"));
		assertLocales(r.getI18nDescription(), (Map<String, Object>) ob.get("i18nDesc"));
				
	}
	
	@Test
	public void test_toExternalList(){
		List <Rating> ratings = Arrays.asList(getRating(), getRating2());
		List<Map<String,Object>> res = transformer.toExternalList(ratings);
		assertEquals(res.size(), 2);
	}
	
	@Test
	public void test_toExternalId(){
		Rating r = getRating();
		Object id = transformer.toExternalId(r);
		assertEquals(id, r.getExternalId());
	}
	
	@Test
	public void test_toExternalIdList(){
		List <Rating> ratings = Arrays.asList(getRating(), getRating2());
		List<Object> res = transformer.toExternalIdList(ratings);
		assertEquals(res.size(), 2);
		assertEquals(getRating().getExternalId(), res.get(0));
		assertEquals(getRating2().getExternalId(), res.get(1));
	}
	
	@Test
	public void test_toInternal(){
		Rating rating = getRating();
		// getReady map
		Map <String, Object> mapExternal = transformer.toExternal(rating);
		
		Rating r = transformer.toInternal(mapExternal);
		
		assertEquals(r.getId(), null);
		assertEquals(r.getExternalId(), rating.getExternalId());
		assertEquals(r.getCode(), rating.getCode());
		assertEquals(r.getDescription(),rating.getDescription());
		assertEquals(r.getName(),rating.getName());
		assertEquals(r.getI18nDescription(),rating.getI18nDescription());
		assertEquals(r.getI18nName(),rating.getI18nName());
		assertEquals(r.getLevel(),rating.getLevel());
		assertEquals(r.getSystem().getId(),rating.getSystem().getId());
				
	}
	
	
	private void assertLocales(Map<Locale,String> mapDomain , Map<String,Object> map){
		assertTrue(mapDomain.size() == map.size(), "map sizes not equals !");
		for(Entry<Locale,String> entry : mapDomain.entrySet()){
			String localeStr = entry.getKey().getLanguage() + "_" + entry.getKey().getCountry();
			String val = entry.getValue();
			assertEquals(map.get(localeStr), val);
		}
	}
	
	private Rating getRating(){
		Rating r = new Rating();
		r.setCode("rat_code");
		r.setDescription("ru desc");
		r.setName("ru name");
		r.setExternalId("1234");
		
		Map <Locale,String> i18nName = new HashMap<Locale,String>();
		i18nName.put(new Locale("ru","RU"), "ru name");
		i18nName.put(new Locale("en","EN"), "en name");
		
		Map <Locale,String> i18nDesc = new HashMap<Locale,String>();
		i18nDesc.put(new Locale("ru","RU"), "ru desc");
		i18nDesc.put(new Locale("en","EN"), "en desc");
		
		r.setI18nName(i18nName);
		r.setI18nDescription(i18nDesc);
		
		r.setId(111L);
		r.setLevel(5L);
		r.setSystem(getRatingSystem());
						
		return r;
	}
	
	private Rating getRating2(){
		Rating r = new Rating();
		r.setCode("rat_code_2");
		r.setDescription("ru desc 2");
		r.setName("ru name 2");
		r.setExternalId("12342");
		
		Map <Locale,String> i18nName = new HashMap<Locale,String>();
		i18nName.put(new Locale("ru","RU"), "ru name 2");
		i18nName.put(new Locale("en","EN"), "en name 2");
		
		Map <Locale,String> i18nDesc = new HashMap<Locale,String>();
		i18nDesc.put(new Locale("ru","RU"), "ru desc 2");
		i18nDesc.put(new Locale("en","EN"), "en desc 2");
		
		r.setI18nName(i18nName);
		r.setI18nDescription(i18nDesc);
		
		r.setId(1112L);
		r.setLevel(52L);
		r.setSystem(getRatingSystem());
						
		return r;
	}
	
	private RatingSystem getRatingSystem(){
		RatingSystem res =  new RatingSystem();
		res.setDescription("desc");
		res.setId(222L);
		res.setName("name");
		return res;
	}
	
	
}
