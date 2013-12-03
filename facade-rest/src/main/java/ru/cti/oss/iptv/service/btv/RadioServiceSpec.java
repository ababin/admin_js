// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.iptv.service.btv;

import javax.persistence.NamedQuery;

/**
 * Autogenerated POJO EJB class for RadioServiceSpec containing the
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
@javax.persistence.Table(name = "RADIO_SERVICE_SPEC")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "RadioServiceSpec.findAll", query = "select radioServiceSpec from RadioServiceSpec AS radioServiceSpec"),
        @NamedQuery(name = "RadioServiceSpec.findChannels", query = "select ch from RadioServiceSpec radiossp  join radiossp.radioChannels"
                + " ch where  radiossp.externalId in ( :availServices ) order by ch.sortOrder "),
        @NamedQuery(name = "RadioServiceSpec.findChannelsByServiceSpecExtId", query = "select rss.radioChannels from RadioServiceSpec rss where rss.externalId=:id")})
public class RadioServiceSpec extends ru.cti.oss.cbe.service.ServiceSpecification implements java.io.Serializable
{
    
    private static final long serialVersionUID = -7568555952267525105L;
    
    // --------- Relationship Definitions -----------
    
    private java.util.Set < ru.cti.oss.iptv.resource.logical.RadioChannel > radioChannels = new java.util.TreeSet < ru.cti.oss.iptv.resource.logical.RadioChannel >();
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public RadioServiceSpec() {
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
    public RadioServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
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
     * @param radioChannels Value for the radioChannels relation
     */
    public RadioServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo,
            java.util.Set < ru.cti.oss.iptv.resource.logical.RadioChannel > radioChannels) {
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
        
        setRadioChannels(radioChannels);
    }
    
    // ------------- Relations ------------------
    
    /**
     * Get the radioChannels Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.RadioChannel>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "RADIO_CHANNELS2RADIO_SERVICE_S", joinColumns = { @javax.persistence.JoinColumn(name = "RADIO_SERVICE_SPEC_IDC", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "RADIO_CHANNEL_IDC", referencedColumnName = "ID") })
    public java.util.Set < ru.cti.oss.iptv.resource.logical.RadioChannel > getRadioChannels() {
        return this.radioChannels;
    }
    
    /**
     * Set the radioChannels
     *
     * @param radioChannels
     */
    public void setRadioChannels(java.util.Set < ru.cti.oss.iptv.resource.logical.RadioChannel > radioChannels) {
        this.radioChannels = radioChannels;
    }
    
    /**
     * Add RadioChannel
     *
     * @param radioChannel
     */
    
    public void addToRadioChannels(ru.cti.oss.iptv.resource.logical.RadioChannel radioChannel) {
        if (radioChannel == null)
            return;
        this.getRadioChannels().add(radioChannel);
    }
    
    /**
     * Remove RadioChannel
     *
     * @param radioChannel
     */
    
    public void removeFromRadioChannels(ru.cti.oss.iptv.resource.logical.RadioChannel radioChannel) {
        if (radioChannel == null)
            return;
        this.getRadioChannels().remove(radioChannel);
    }
    
    /*
     * (non-Javadoc)
     * @see ru.cti.oss.cbe.service.ServiceSpecification#countTypeSpecs()
     */
    @Override
    public int countTypeSpecs() {
    	if (radioChannels != null) {
    		return radioChannels.size();
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
        if (!(object instanceof RadioServiceSpec)) {
            return false;
        }
        final RadioServiceSpec that = (RadioServiceSpec) object;
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