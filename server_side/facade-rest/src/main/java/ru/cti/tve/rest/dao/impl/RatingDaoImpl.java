package ru.cti.tve.rest.dao.impl;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.common.Rating;
import ru.cti.tve.rest.dao.BaseDao;

@Service("ratingDao")
public class RatingDaoImpl extends BaseDao<Rating>{

	/*
	 * constructor
	 */
	public RatingDaoImpl() {
		super(Rating.class);
	}
	
	
	
}
