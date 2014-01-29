// license-header java merge-point
package ru.cti.oss.iptv.resource.logical;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Callback Listener for Entity POJO EJB ru.cti.oss.iptv.resource.logical.MediaAsset
 *
 * @see ru.cti.oss.iptv.resource.logical.MediaAsset
 */
public class MediaAssetListener
{
	private Log log = LogFactory.getLog(MediaAssetListener.class);
    /**
     * Default public no-args constructor
     */
    public MediaAssetListener()
    {
        // empty constructor
    }

    @javax.persistence.PrePersist
    public void prePersist(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset)
    {
		// pre persist implementation
	}

	@javax.persistence.PostPersist
	public void postPersist(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset)
	{
		/*
		if ((mediaAsset.getEncryption() != EncryptionType.NONE) && (!(mediaAsset.getMediaContent() instanceof Program))) {
			try {
				InitialContext ic = new InitialContext();
				IntegrationControllerLocal integrationService = (IntegrationControllerLocal)ic.lookup(ProjectInfoBean.getApplicationName() + "/IntegrationControllerBean/local");
				integrationService.addResource(mediaAsset);
			} catch (NamingException e) {
				log.warn("Failed notifying JMS integration controller : " + e.getMessage());
			}
		}
		*/
	}

	@javax.persistence.PreRemove
	public void preRemove(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset)
	{
		/*
		if (!(mediaAsset.getMediaContent() instanceof Program)) {
			if (mediaAsset.getEncryption() != EncryptionType.NONE) {
				try {
					InitialContext ic = new InitialContext();
					IntegrationControllerLocal integrationService = (IntegrationControllerLocal)ic.lookup(ProjectInfoBean.getApplicationName() + "/IntegrationControllerBean/local");
					integrationService.removeResource(mediaAsset);
				} catch (NamingException e) {
					log.warn("Failed notifying JMS integration controller : " + e.getMessage());
				}
			}
		} else {
			Program program = (Program)mediaAsset.getMediaContent();
			program.setMediaSource(null);
		}
		*/
	}

	@javax.persistence.PostRemove
	public void postRemove(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset) {
		// post remove implementation
	}

	@javax.persistence.PreUpdate
	public void preUpdate(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset) {
		// pre update implementation
	}

	@javax.persistence.PostUpdate
	public void postUpdate(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset)
	{
		/*
		if ((mediaAsset.getEncryption() != EncryptionType.NONE) && (!(mediaAsset.getMediaContent() instanceof Program))) {
			try {
				InitialContext ic = new InitialContext();
				IntegrationControllerLocal integrationService = (IntegrationControllerLocal)ic.lookup(ProjectInfoBean.getApplicationName() + "/IntegrationControllerBean/local");
				integrationService.modifyResource(mediaAsset);
			} catch (NamingException e) {
				log.warn("Failed notifying JMS integration controller : " + e.getMessage());
			}
		}
		*/
	}

	@javax.persistence.PostLoad
	public void postLoad(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset)
	{
		// post load implementation
	}
}
