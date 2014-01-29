package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.MediaAsset;
import ru.cti.tve.rest.transformer.base.Transformer;
import ru.cti.tve.rest.util.HibernateUtil;

@Service("mediaAssetTransformer")
public class MediaAssetTransformer extends Transformer<MediaAsset>{

	private VideoServerTransformer videoserverTransformer = new VideoServerTransformer();
	private AudioChannelTransformer audioChannelTransformer = new AudioChannelTransformer();
	private SubtitleChannelTransformer subtitleChannelTransformer = new SubtitleChannelTransformer();
	private BandwidthRatingTransformer bandwidthRatingTransformer = new BandwidthRatingTransformer();
	
	@Override
	public Map<String, Object> toExternal(MediaAsset domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		domain = HibernateUtil.deproxy(domain, MediaAsset.class);
		
		// common
		map.put("c", "MediaAsset");
		
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
		map.put("subtitleChannels",subtitleChannelTransformer.toExternalList(domain.getSubtitleChannels()));
		map.put("audioChannels",audioChannelTransformer.toExternalList(domain.getAudioChannels()));
		
		// mediaAsset
		map.put("fileName",domain.getFileName());
		map.put("fileSize",domain.getFileSize());
		map.put("duration",domain.getDuration());
		map.put("videoservers",videoserverTransformer.toExternalList(domain.getVideoservers()));
		
		return map;
	}
		
	@Override
	public Object toExternalId(MediaAsset domain) {
		return domain.getExternalId();
	}

	@Override
	public MediaAsset toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
