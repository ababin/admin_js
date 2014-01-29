package ru.cti.tve.rest.transformer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import ru.cti.oss.iptv.common.RatingSystem;
import ru.cti.tve.rest.BaseTest;
import ru.cti.tve.rest.transformer.base.Transformer;

public class AgeRatingSystemTransformerTest extends BaseTest{
	
	@Autowired
	@Qualifier("ageRatingSystemTransformer")
	private Transformer<RatingSystem> transformer;
				
	@Test
	public void test_toExternal(){
		RatingSystem r = getRatingSystem();
		Map <String,Object> ob = transformer.toExternal(r);
		assertTrue(ob.get("id").equals(r.getId()));
		assertTrue(ob.get("name").equals(r.getName()));
		assertTrue(ob.get("desc").equals(r.getDescription()));
	}
	
	@Test
	public void test_toExternalList(){
		List <RatingSystem> ratingSystems = Arrays.asList(getRatingSystem(), getRatingSystem2());
		List<Map<String,Object>> res = transformer.toExternalList(ratingSystems);
		assertEquals(res.size(), 2);
	}
	
	@Test
	public void test_toExternalId(){
		RatingSystem r = getRatingSystem();
		Object id = transformer.toExternalId(r);
		assertEquals(id, r.getId());
	}
	
	@Test
	public void test_toExternalIdList(){
		List <RatingSystem> ratingSystems = Arrays.asList(getRatingSystem(), getRatingSystem2());
		List<Object> res = transformer.toExternalIdList(ratingSystems);
		assertEquals(res.size(), 2);
		assertEquals(getRatingSystem().getId(), res.get(0));
		assertEquals(getRatingSystem2().getId(), res.get(1));
	}
	
	@Test
	public void test_toInternal(){
		RatingSystem ratingSystem = getRatingSystem();
		// getReady map
		Map <String, Object> mapExternal = transformer.toExternal(ratingSystem);
		
		RatingSystem r = transformer.toInternal(mapExternal);
		
		assertEquals(r.getId(), ratingSystem.getId());
		assertEquals(r.getId(), ratingSystem.getId());
		assertEquals(r.getName(), ratingSystem.getName());
		assertEquals(r.getDescription(),ratingSystem.getDescription());
	}
				
	private RatingSystem getRatingSystem(){
		RatingSystem r = new RatingSystem();
		r.setDescription("desc");
		r.setId(111L);
		r.setName("name");
		return r;
	}
	
	private RatingSystem getRatingSystem2(){
		RatingSystem r = new RatingSystem();
		r.setDescription("desc2");
		r.setId(1112L);
		r.setName("name2");
		return r;
	}
	
}
