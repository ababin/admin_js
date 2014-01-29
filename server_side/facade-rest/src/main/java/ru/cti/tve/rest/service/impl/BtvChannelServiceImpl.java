package ru.cti.tve.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.BtvChannel;
import ru.cti.tve.rest.dao.Dao;
import ru.cti.tve.rest.dao.impl.BtvChannelDaoImpl;
import ru.cti.tve.rest.service.BaseService;


@Service("btvChannelService")
public class BtvChannelServiceImpl extends BaseService<BtvChannel> {
	
	@Autowired
	@Qualifier("btvChannelDao")
	private Dao<BtvChannel> btvChannelDao;
	
	public BtvChannelServiceImpl() {
		super(BtvChannel.class);
	}

	@Override
	protected Dao<BtvChannel> getDao() {
		return btvChannelDao;
	}
}
