package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.physical.VideoServer;
import ru.cti.tve.rest.transformer.base.Transformer;

@Service("videoServerTransformer")
public class VideoServerTransformer extends Transformer<VideoServer> {
		
	@Override
	public Map<String,Object> toExternal(VideoServer domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		
		// resource
		map.put("id",toExternalId(domain));
		map.put("name",domain.getName());
		map.put("desc",domain.getDescription());
		map.put("status", domain.getStatus());
		
		// server
		map.put("hostname", domain.getHostname());
		
		// videoserver
		map.put("type", domain.getType());
		map.put("port", domain.getPort());
		map.put("controlInterface", domain.getControlInterface());
		map.put("priority", domain.getPriority());
			
		return map;
	}
	
	@Override
	public Object toExternalId(VideoServer domain) {
		return domain.getExternalId();
	}

	@Override
	public VideoServer toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
