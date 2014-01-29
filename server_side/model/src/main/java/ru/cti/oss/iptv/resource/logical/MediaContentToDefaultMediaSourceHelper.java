package ru.cti.oss.iptv.resource.logical;

import java.util.Set;


/**The Class <code>MediaContentToDefaultMediaSourceHelper</code> select default MediaSource from collection. Created after change one-to-one reference to one-to-many in MediaContent.<br/>
 *  <p>
 *  <b>Copyright: </b>Copyright (c) 2009
 *  </p>
 *  <p>
 *   <b>Company: </b>CTI
 *  </p>
 *  @since 11.03.2010
 *  @author n.makarov <br/> <b>e-mail</b>: n.makarov@cti.ru <br/>
 *  @version 1.0
*/
public class MediaContentToDefaultMediaSourceHelper {

	
	private static String defaultMediaAssetProtocol="rtsp";
	private static String defaultMulticastChannelProtocol="igmp";
	
	
	public static MediaSource getDefaultMediaAsset(Set<MediaSource> mediaSources) {
		return getMediaSource(mediaSources, defaultMediaAssetProtocol);
	}

	public static MediaSource getDefaultMulticastChannel(Set<MediaSource> mediaSources) {
		return getMediaSource(mediaSources, defaultMulticastChannelProtocol);
	}

	public static MediaSource getMediaSource(Set<MediaSource> mediaSources, String protocol) {
		if (protocol != null) {
			for (MediaSource mediaSource : mediaSources) {
				if (protocol.equals(mediaSource.getProtocol())) {
					return mediaSource;
				}
			}
		}
		return null;
	}

}
