package ru.cti.tve.rest.transformer.base;

import ru.cti.oss.iptv.common.Category;
import ru.cti.oss.iptv.common.Rating;
import ru.cti.oss.iptv.resource.logical.BtvChannel;
import ru.cti.oss.iptv.resource.logical.Region;
import ru.cti.tve.rest.transformer.AgeRatingTransformer;
import ru.cti.tve.rest.transformer.BtvChannelTransformer;
import ru.cti.tve.rest.transformer.GenreTransformer;
import ru.cti.tve.rest.transformer.RegionTransformer;
import ru.cti.tve.rest.transformer.base.Transformer;


public class TransformerFactory {
	
	
	public static Transformer create(Class entityClass){
		if(BtvChannel.class.getSimpleName().equals(entityClass.getSimpleName())){
			return new BtvChannelTransformer();
		}if(Rating.class.getSimpleName().equals(entityClass.getSimpleName())){
			return new AgeRatingTransformer();
		}if(Category.class.getSimpleName().equals(entityClass.getSimpleName())){
			return new GenreTransformer();
		}if(Region.class.getSimpleName().equals(entityClass.getSimpleName())){
			return new RegionTransformer();
		}else{
			return null;
		}
	}
	
}
