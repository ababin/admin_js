package ru.cti.tve.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.common.Rating;
import ru.cti.tve.rest.dao.Dao;
import ru.cti.tve.rest.service.BaseService;

@Service("ageRatingService")
public class AgeRatingServiceImpl extends BaseService<Rating> {
	
	@Autowired
	@Qualifier("ratingDao")
	private Dao<Rating> ratingDao;
	
	public AgeRatingServiceImpl(){
		super(Rating.class);
	}

	@Override
	protected Dao<Rating> getDao() {
		return ratingDao;
	}

	

}
