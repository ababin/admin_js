// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.iptv.service.btv;

import javax.persistence.NamedQuery;

/**
 * Autogenerated POJO EJB class for BtvServiceSpec containing the
 * bulk of the entity implementation.
 *
 * This is autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 *
 * 
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "BTV_SERVICE_SPEC")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "BtvServiceSpec.findAll", query = "select btvServiceSpec from BtvServiceSpec AS btvServiceSpec"),
        @NamedQuery(name = "BtvServiceSpec.findChannelsByServiceSpecId", query = "select bss.btvChannels from BtvServiceSpec bss where bss.id=:id"),
        @NamedQuery(name = "BtvServiceSpec.findChannelsByServiceSpecExtId", query = "select bss.btvChannels from BtvServiceSpec bss where bss.externalId=:id"),
        @NamedQuery(name = "BtvServiceSpec.findChannels", query = "select ch from BtvServiceSpec as btvssp " +
        															"join btvssp.btvChannels as ch " +
        															"join ch.mediaSources as ms " +
        															"left outer join ch.regions as region " +
        															"left outer join ms.bandwidthRating as bwr " +
        															
        														  "where " +
        															"( (btvssp.externalId in (:availServices)) and " +
        															"(region is null or region.code in (:region)) and " +
        															"(bwr is null or bwr.level <= :bandwidthRating) and " +
        															"(ms.encoding is null or ms.encoding in (:codecs)) and " +
        															"(ms.encryption is null or ms.encryption = 'NONE' or ms.encryption in (:encryptions)) and " +
        															"(ms.resolution is null or ms.resolution in (:resolutions)) ) " +
        															"and ch.status='ACTIVE' " +
        														  "order by ch.sortOrder, ch.id " ) ,
        @NamedQuery(name = "BtvServiceSpec.findChannelsIds", query = "select ch.id from BtvServiceSpec btvssp  join btvssp.btvChannels"
                + " ch where  btvssp.externalId in ( :availServices ) and ch.rating.level<= :accountRatingLevel"),
                
        // CasBean
        @NamedQuery(name = "BtvServiceSpec.findByChannelParams", 
        	query = "SELECT bss FROM BtvServiceSpec bss " +
    				"INNER JOIN bss.btvChannels bc " +
    				"INNER JOIN bc.mediaSources ms " +
    				"WHERE " +
    				"  ms MEMBER OF MulticastChannel " +
    				"  AND ms.mcastAddress = :mcastAddress " +
    				"  AND ms.mcastPortNumber = :mcastPortNumber") 

})        
public class BtvServiceSpec extends ru.cti.oss.cbe.service.ServiceSpecification implements java.io.Serializable
{
    
    private static final long serialVersionUID = -5449229341836634514L;
    
    
    // --------- Relationship Definitions -----------
    
    private java.util.Set < ru.cti.oss.iptv.resource.logical.BtvChannel > btvChannels = new java.util.TreeSet < ru.cti.oss.iptv.resource.logical.BtvChannel >();
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public BtvServiceSpec() {
    }
    
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
     */
    public BtvServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo) {
        super(name, description, status, externalId, logo);
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     * @param btvChannels Value for the btvChannels relation
     */
    public BtvServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo, java.util.Set < ru.cti.oss.iptv.resource.logical.BtvChannel > btvChannels) {
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
        
        setBtvChannels(btvChannels);
    }
    
   
    // ------------- Relations ------------------
    
    /**
     * Get the btvChannels Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.BtvChannel>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.EAGER)
    @javax.persistence.JoinTable(name = "BTV_CHANNELS2BTV_SERVICE_SPECS", joinColumns = { @javax.persistence.JoinColumn(name = "BTV_SERVICE_SPEC_IDC", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "BTV_CHANNEL_IDC", referencedColumnName = "ID") })
    public java.util.Set < ru.cti.oss.iptv.resource.logical.BtvChannel > getBtvChannels() {
        return this.btvChannels;
    }
    
    /**
     * Set the btvChannels
     *
     * @param btvChannels
     */
    public void setBtvChannels(java.util.Set < ru.cti.oss.iptv.resource.logical.BtvChannel > btvChannels) {
        this.btvChannels = btvChannels;
    }
    
    /**
     * Add BtvChannel
     *
     * @param btvChannel
     */
    
    public void addToBtvChannels(ru.cti.oss.iptv.resource.logical.BtvChannel btvChannel) {
        if (btvChannel == null)
            return;
        this.getBtvChannels().add(btvChannel);
    }
    
    /**
     * Remove BtvChannel
     *
     * @param btvChannel
     */
    
    public void removeFromBtvChannels(ru.cti.oss.iptv.resource.logical.BtvChannel btvChannel) {
        if (btvChannel == null)
            return;
        this.getBtvChannels().remove(btvChannel);
    }
    
    /*
     * (non-Javadoc)
     * @see ru.cti.oss.cbe.service.ServiceSpecification#countTypeSpecs()
     */
    @Override
    public int countTypeSpecs() {
    	if (btvChannels != null) {
    		return btvChannels.size();
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BtvServiceSpec)) {
            return false;
        }
        final BtvServiceSpec that = (BtvServiceSpec) object;
        if (this.getId() == null || that.getId() == null || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    public int hashCode() {
        int hashCode = super.hashCode();
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());
        
        return hashCode;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString() {
        return super.toString();
    }

	
    
}