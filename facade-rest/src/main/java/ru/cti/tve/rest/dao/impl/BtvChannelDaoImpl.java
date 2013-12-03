package ru.cti.tve.rest.dao.impl;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.BtvChannel;
import ru.cti.tve.rest.dao.BaseDao;

@Service("btvChannelDao")
public class BtvChannelDaoImpl  extends BaseDao<BtvChannel> {

	/*
	 * constructor
	 */
	public BtvChannelDaoImpl() {
		super(BtvChannel.class);
	}
	
	
	
}
