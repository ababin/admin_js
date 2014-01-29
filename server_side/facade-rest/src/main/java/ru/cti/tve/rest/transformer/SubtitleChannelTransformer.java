package ru.cti.tve.rest.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.cti.oss.iptv.resource.logical.SubtitleChannel;
import ru.cti.tve.rest.transformer.base.Transformer;
import ru.cti.tve.rest.util.HibernateUtil;

@Service("subtitleChannelTransformer")
public class SubtitleChannelTransformer extends Transformer<SubtitleChannel>{

	private IptvLocaleTransformer iptvLocaleTransformer = new IptvLocaleTransformer();
	
	@Override
	public Map<String, Object> toExternal(SubtitleChannel domain) {
		Map <String,Object> map = new HashMap<String , Object>();
		domain = HibernateUtil.deproxy(domain, SubtitleChannel.class);
				
		map.put("id", toExternalId(domain));
		map.put("locale", iptvLocaleTransformer.toExternal(domain.getLocale()));
		map.put("name", domain.getName());
		map.put("pid", domain.getPid());
				
		return map;
	}

	@Override
	public Object toExternalId(SubtitleChannel domain) {
		// TODO : необходима реализация для externalId
		return domain.getId();
	}

	@Override
	public SubtitleChannel toInternal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
