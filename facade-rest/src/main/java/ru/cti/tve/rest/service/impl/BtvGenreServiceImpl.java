package ru.cti.tve.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.common.Category;
import ru.cti.tve.rest.dao.Dao;
import ru.cti.tve.rest.service.BaseService;

@Service("btvGenreService")
public class BtvGenreServiceImpl extends BaseService<Category> {
	
	@Autowired
	@Qualifier("btvGenreDao")
	private Dao<Category> dao;
	
	public BtvGenreServiceImpl(){
		super(Category.class);
	}

	@Override
	protected Dao<Category> getDao() {
		return dao;
	}
			
}
