package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.MediaAsset;
import ru.cti.oss.iptv.resource.logical.MediaSource;
import ru.cti.oss.iptv.resource.logical.MulticastChannel;
import ru.cti.tve.rest.transformer.base.Transformer;
import ru.cti.tve.rest.util.HibernateUtil;

@Service("mediaSourceShortTransformer")
public class MediaSourceShortTransformer extends Transformer<MediaSource>{

	@Override
	public Map<String, Object> toExternal(MediaSource domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		domain = HibernateUtil.deproxy(domain, MediaSource.class);
		if(domain instanceof MediaAsset){
			map.put("c", "MediaAsset");
			map.put("id",toExternalId(domain));
			map.put("protocol",domain.getProtocol());
			map.put("fileName",((MediaAsset) domain).getFileName());
		}else if(domain instanceof MulticastChannel){
			map.put("c", "MulticastChannel");
			map.put("id",toExternalId(domain));
			map.put("protocol",domain.getProtocol());
			map.put("mcastAddress",((MulticastChannel) domain).getMcastAddress());
			map.put("mcastPortNumber",((MulticastChannel) domain).getMcastPortNumber());
		}else{
			
		}
		return map;
	}
	
	@Override
	public Object toExternalId(MediaSource domain) {
		return domain.getExternalId();
	}

	@Override
	public MediaSource toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
