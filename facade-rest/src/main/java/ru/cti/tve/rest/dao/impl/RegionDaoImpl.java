package ru.cti.tve.rest.dao.impl;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.Region;
import ru.cti.tve.rest.dao.BaseDao;

@Service("regionDao")
public class RegionDaoImpl extends BaseDao<Region>{

	/*
	 * constructor
	 */
	public RegionDaoImpl() {
		super(Region.class);
	}
	
	
	
}
