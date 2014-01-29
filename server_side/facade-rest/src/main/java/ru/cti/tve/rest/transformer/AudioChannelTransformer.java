package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.AudioChannel;
import ru.cti.tve.rest.transformer.base.Transformer;
import ru.cti.tve.rest.util.HibernateUtil;

@Service("audioChannelTransformer")
public class AudioChannelTransformer extends Transformer<AudioChannel>{

	private IptvLocaleTransformer iptvLocaleTransformer = new IptvLocaleTransformer();
	
	@Override
	public Map<String, Object> toExternal(AudioChannel domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		domain = HibernateUtil.deproxy(domain, AudioChannel.class);
				
		map.put("id", toExternalId(domain));
		map.put("locale", iptvLocaleTransformer.toExternal(domain.getLocale()));
		map.put("name", domain.getName());
		map.put("pid", domain.getPid());
				
		return map;
	}

	@Override
	public Object toExternalId(AudioChannel domain) {
		// TODO : необходима реализация для externalId
		return domain.getId();
	}

	@Override
	public AudioChannel toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
