package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.MediaAsset;
import ru.cti.oss.iptv.resource.logical.MediaSource;
import ru.cti.oss.iptv.resource.logical.MulticastChannel;
import ru.cti.tve.rest.transformer.base.Transformer;
import ru.cti.tve.rest.util.HibernateUtil;

@Service("mediaSourceTransformer")
public class MediaSourceTransformer extends Transformer<MediaSource>{

	private MediaAssetTransformer mediaAssetTransformer = new MediaAssetTransformer();
	private MulticastChannelTransformer multicastChannelTransformer = new MulticastChannelTransformer();
	
	@Override
	public Map<String, Object> toExternal(MediaSource domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		domain = HibernateUtil.deproxy(domain, MediaSource.class);
		if(domain instanceof MediaAsset){
			map = mediaAssetTransformer.toExternal((MediaAsset)domain);
		}else if(domain instanceof MulticastChannel){
			map = multicastChannelTransformer.toExternal((MulticastChannel)domain);
		}else{
			map = null;
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
