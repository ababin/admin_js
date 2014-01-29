package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.MulticastChannel;
import ru.cti.tve.rest.transformer.base.Transformer;
import ru.cti.tve.rest.util.HibernateUtil;

@Service("multicastChannelTransformer")
public class MulticastChannelTransformer extends Transformer<MulticastChannel>{

	
	private AudioChannelTransformer audioChannelTransformer = new AudioChannelTransformer();
	private SubtitleChannelTransformer subtitleChannelTransformer = new SubtitleChannelTransformer();
	private BandwidthRatingTransformer bandwidthRatingTransformer = new BandwidthRatingTransformer();
	
	@Override
	public Map<String, Object> toExternal(MulticastChannel domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		domain = HibernateUtil.deproxy(domain, MulticastChannel.class);
		
		// common
		map.put("c", "MulticastChannel");
		
		// source
		map.put("id",toExternalId(domain));
		map.put("name",domain.getName());
		map.put("desc",domain.getDescription());
		map.put("status",domain.getStatus());
		
		// mediaSource
		map.put("encryption",domain.getEncryption());
		map.put("copyProtection",domain.getCopyProtection());
		map.put("encoding",domain.getEncoding());
		map.put("bitrate",domain.getBitrate());
		map.put("volume",domain.getVolume());
		map.put("protocol",domain.getProtocol());
		map.put("application",domain.getApplication());
		map.put("bandwidthRating", bandwidthRatingTransformer.toExternalId(domain.getBandwidthRating()));
		map.put("resolution",domain.getResolution());
		map.put("casId",domain.getCasId());
		map.put("networkType",domain.getNetworkType());
		map.put("subtitleChannels", subtitleChannelTransformer.toExternalList(domain.getSubtitleChannels()));
		map.put("audioChannels", audioChannelTransformer.toExternalList(domain.getAudioChannels()));
		
		// MulticastChannel
		map.put("mcastAddress",domain.getMcastAddress());
		map.put("mcastPortNumber",domain.getMcastPortNumber());
		
		return map;
	}
	
	@Override
	public Object toExternalId(MulticastChannel domain) {
		return domain.getExternalId();
	}

	@Override
	public MulticastChannel toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
