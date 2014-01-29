package ru.cti.oss.iptv.service.pltv;

import javax.persistence.NamedQuery;


@javax.persistence.Entity
@javax.persistence.Table(name = "PLTV_SERVICE_SPEC")
@javax.persistence.NamedQueries({@NamedQuery(name = "PltvServiceSpec.findAll", query = "select pltvServiceSpec from PltvServiceSpec AS pltvServiceSpec"),
@NamedQuery(name = "PltvServiceSpec.findBySubsription", query = "select channel from PltvServiceSpec pltvspec join pltvspec.npvrChannels as channel "+
        "where pltvspec.externalId in (:availServices)")})

public class PltvServiceSpec
    extends ru.cti.oss.cbe.service.ServiceSpecification
    implements java.io.Serializable
{

   
    /**
	 * 
	 */
	private static final long serialVersionUID = -255543516318746417L;


	// ----------- Attribute Definitions ------------

    private java.lang.Long pauseTimeout;


    // --------- Relationship Definitions -----------

    private java.util.Set<ru.cti.oss.iptv.resource.logical.NpvrChannel> npvrChannels = new java.util.TreeSet<ru.cti.oss.iptv.resource.logical.NpvrChannel>();

    // ---- Manageable Display Attributes (Transient) -----


    // --------------- Constructors -----------------

    /**
     * Default empty constructor
     */
    public PltvServiceSpec() {}

    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     * @param pauseTimeout Value for the pauseTimeout property
     */
    public PltvServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status, java.lang.Long externalId, ru.cti.oss.iptv.Image logo, java.lang.Long pauseTimeout)
    {
        super(name, description, status, externalId, logo);
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
        setPauseTimeout(pauseTimeout);
    }

    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     * @param pauseTimeout Value for the pauseTimeout property
     * @param npvrChannels Value for the npvrChannels relation
     */
    public PltvServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status, java.lang.Long externalId, ru.cti.oss.iptv.Image logo, java.lang.Long pauseTimeout, java.util.Set<ru.cti.oss.iptv.resource.logical.NpvrChannel> npvrChannels)
    {
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
        setPauseTimeout(pauseTimeout);

        setNpvrChannels(npvrChannels);
    }


    // -------- Attribute Accessors ----------

    /**
     * Get the pauseTimeout property.
     * 
     * @return java.lang.Long The value of pauseTimeout
     */
    	@javax.persistence.Column(name = "PAUSE_TIMEOUT", nullable = false, insertable = true, updatable = true)
    public java.lang.Long getPauseTimeout()
    {
        return pauseTimeout;
    }

    /**
     * Set the pauseTimeout property.
     * @param value the new value
     */
    public void setPauseTimeout(java.lang.Long value)
    {
        this.pauseTimeout = value;
    }


    // ------------- Relations ------------------

    /**
     * Get the npvrChannels Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.NpvrChannel>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable
    (
        name = "NPVR_CHANNELS2PLTV_SERVICE_SPE",
        joinColumns = {@javax.persistence.JoinColumn(name = "PLTV_SERVICE_SPEC_IDC", referencedColumnName = "ID")},
        inverseJoinColumns = {@javax.persistence.JoinColumn(name = "NPVR_CHANNEL_IDC", referencedColumnName = "ID")}
    )
    public java.util.Set<ru.cti.oss.iptv.resource.logical.NpvrChannel> getNpvrChannels()
    {
        return this.npvrChannels;
    }

    /**
     * Set the npvrChannels
     *
     * @param npvrChannels
     */
    public void setNpvrChannels (java.util.Set<ru.cti.oss.iptv.resource.logical.NpvrChannel> npvrChannels)
    {
        this.npvrChannels = npvrChannels;
    }

    /**
     * Add NpvrChannel
     *
     * @param npvrChannel
     */

    public void addToNpvrChannels(ru.cti.oss.iptv.resource.logical.NpvrChannel npvrChannel)
    {
        if (npvrChannel == null ) return;
        this.getNpvrChannels().add(npvrChannel);
    }

    /**
     * Remove NpvrChannel
     *
     * @param npvrChannel
     */

    public void removeFromNpvrChannels (ru.cti.oss.iptv.resource.logical.NpvrChannel npvrChannel)
    {
        if (npvrChannel == null ) return;
        this.getNpvrChannels().remove(npvrChannel);
    }
    
    /*
     * (non-Javadoc)
     * @see ru.cti.oss.cbe.service.ServiceSpecification#countTypeSpecs()
     */
    @Override
    public int countTypeSpecs() {
    	if (npvrChannels != null) {
    		return npvrChannels.size();
    	}
    	return 0;
    }

    // -------- Common Methods -----------

    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof PltvServiceSpec))
        {
            return false;
        }
        final PltvServiceSpec that = (PltvServiceSpec)object;
        if (this.getId() == null || that.getId() == null || !this.getId().equals(that.getId()))
        {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    public int hashCode()
    {
        int hashCode = super.hashCode();
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());

        return hashCode;
    }

    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("PltvServiceSpec(=");
        sb.append(super.toString());
        sb.append("pauseTimeout: ");
        sb.append(getPauseTimeout());
        sb.append(")");
        return sb.toString();
    }

}