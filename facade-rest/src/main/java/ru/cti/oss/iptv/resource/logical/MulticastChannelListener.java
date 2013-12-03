// license-header java merge-point
package ru.cti.oss.iptv.resource.logical;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Callback Listener for Entity POJO EJB ru.cti.oss.iptv.resource.logical.MulticastChannel
 *
 * @see ru.cti.oss.iptv.resource.logical.MulticastChannel
 */
public class MulticastChannelListener
{
	private Log log = LogFactory.getLog(MulticastChannelListener.class);
    /**
     * Default public no-args constructor
     */
    public MulticastChannelListener()
    {
        // empty constructor
    }

    @javax.persistence.PrePersist
    public void prePersist(ru.cti.oss.iptv.resource.logical.MulticastChannel multicastChannel)
    {
		// pre persist implementation
	}

	@javax.persistence.PostPersist
	public void postPersist(ru.cti.oss.iptv.resource.logical.MulticastChannel multicastChannel)
	{
	}

	@javax.persistence.PreRemove
	public void preRemove(ru.cti.oss.iptv.resource.logical.MulticastChannel multicastChannel)
	{
		// pre remove implementation
	}

	@javax.persistence.PostRemove
	public void postRemove(ru.cti.oss.iptv.resource.logical.MulticastChannel multicastChannel)
	{
	}

	@javax.persistence.PreUpdate
	public void preUpdate(ru.cti.oss.iptv.resource.logical.MulticastChannel multicastChannel) {
		// pre update implementation
	}

	@javax.persistence.PostUpdate
	public void postUpdate(ru.cti.oss.iptv.resource.logical.MulticastChannel multicastChannel)
	{
	}

	@javax.persistence.PostLoad
	public void postLoad(ru.cti.oss.iptv.resource.logical.MulticastChannel multicastChannel)
	{
		// post load implementation
	}
}
