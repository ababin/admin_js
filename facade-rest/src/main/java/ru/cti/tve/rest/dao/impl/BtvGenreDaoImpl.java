package ru.cti.tve.rest.dao.impl;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.common.Category;
import ru.cti.tve.rest.dao.BaseDao;

@Service("btvGenreDao")
public class BtvGenreDaoImpl extends BaseDao<Category>{

	/*
	 * constructor
	 */
	public BtvGenreDaoImpl() {
		super(Category.class);
	}
	
	
	
}
