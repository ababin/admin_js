package ru.cti.tve.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.Region;
import ru.cti.tve.rest.dao.Dao;
import ru.cti.tve.rest.service.BaseService;

@Service("regionService")
public class RegionServiceImpl extends BaseService<Region> {
	
	@Autowired
	@Qualifier("regionDao")
	private Dao<Region> dao;
	
	public RegionServiceImpl(){
		super(Region.class);
	}

	@Override
	protected Dao<Region> getDao() {
		return dao;
	}

}
