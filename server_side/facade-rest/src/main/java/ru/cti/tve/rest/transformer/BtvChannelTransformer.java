package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.BtvChannel;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("btvChannelTransformer")
public class BtvChannelTransformer extends Transformer<BtvChannel> {
	
	private MediaSourceShortTransformer msTransformer = new MediaSourceShortTransformer();
	private RegionTransformer regionTransformer = new RegionTransformer();
	
	public Map<String,Object> toExternal(BtvChannel domain){
		Map <String,Object> map = new HashMap<String , Object>();
		map.put("id", toExternalId(domain));
		map.put("barkerPosition",domain.getBarkerPosition());
		map.put("categories",toExternalCategories(domain));
		map.put("channelUrl",domain.getChannelUrl());
		map.put("doScreenshots",domain.getDoScreenshots());
		map.put("format",domain.getFormat());
		map.put("i18nName",toExternalI18n(domain.getI18nName()));
		map.put("i18nDesc",toExternalI18n(domain.getI18nDescription()));
		map.put("logo", domain.getLogo() != null ? "/btvChannel/image/" + domain.getExternalId() : null);
		map.put("mediaSources", msTransformer.toExternalList(domain.getMediaSources()));
		map.put("primaryAddress",domain.getPrimaryAddress());
		map.put("primaryProtNumber",domain.getPrimaryPortNumber());
		map.put("primaryProtocol",domain.getPrimaryProtocol());
		map.put("ageRating",domain.getRating().getExternalId());
		map.put("regions", regionTransformer.toExternalList(domain.getRegions()));
		map.put("sortOrder",domain.getSortOrder());
		map.put("status",domain.getStatus().toString());
		return map;
	}
	
	@Override
	public Object toExternalId(BtvChannel domain) {
		return domain.getExternalId();
	}

	@Override
	public BtvChannel toInternal(Map<String, Object> map) {
				
		BtvChannel entity = new BtvChannel();
		
		entity.setExternalId((String) map.get("id"));
		entity.setBarkerPosition((Integer) map.get("barkerPosition"));
		
		return null;
		
		/*		
		map.put("categories",toExternalCategories(domain));
		map.put("channelUrl",domain.getChannelUrl());
		map.put("doScreenshots",domain.getDoScreenshots());
		map.put("format",domain.getFormat());
		map.put("i18nName",toExternalI18n(domain.getI18nName()));
		map.put("i18nDesc",toExternalI18n(domain.getI18nDescription()));
		map.put("logo", domain.getLogo() != null ? "/btvChannel/image/" + domain.getExternalId() : null);
		map.put("mediaSources", msTransformer.toExternalList(domain.getMediaSources()));
		map.put("primaryAddress",domain.getPrimaryAddress());
		map.put("primaryProtNumber",domain.getPrimaryPortNumber());
		map.put("primaryProtocol",domain.getPrimaryProtocol());
		map.put("ageRating",domain.getRating().getExternalId());
		map.put("regions", regionTransformer.toExternalList(domain.getRegions()));
		map.put("sortOrder",domain.getSortOrder());
		map.put("status",domain.getStatus().toString());
		*/
	}
	
}
