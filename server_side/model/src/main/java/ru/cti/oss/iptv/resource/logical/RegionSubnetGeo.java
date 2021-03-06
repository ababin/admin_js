// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.iptv.resource.logical;

import org.hibernate.annotations.Index;

@javax.persistence.Entity
@javax.persistence.Table(name = "REGION_SUBNET_GEO")
@javax.persistence.NamedQueries({
        @javax.persistence.NamedQuery(name = "RegionSubnetGeo.findAll", query = "select regionSubnet from RegionSubnetGeo AS regionSubnet"),
        @javax.persistence.NamedQuery(name = "RegionSubnetGeo.findAllRegionId", query = "select regionSubnet from RegionSubnetGeo AS regionSubnet where regionSubnet.region.id = (:ID)")
})
public class RegionSubnetGeo implements java.io.Serializable, Comparable < RegionSubnetGeo >
{
    private static final long serialVersionUID = 41379328401848302L;
    // ----------- Attribute Definitions ------------
    
    private java.lang.Long id;
    private String ipAddress;
    private String mask;
    
    private Float yandexX;
    private Float yandexY;
    
    
    // --------- Relationship Definitions -----------
    private RegionGeo region;

    private String city;
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public RegionSubnetGeo() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param ipAddress Value for the ipAddress property
     * @param mask Value for the mask property
     */
    public RegionSubnetGeo(final String ipAddress, final String mask) {
        this.setIpAddress(ipAddress);
        this.setMask(mask);
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    @Override
    public int compareTo(final RegionSubnetGeo o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
        }
        return cmp;
    }
    
    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof RegionSubnetGeo)) {
            return false;
        }
        final RegionSubnetGeo that = (RegionSubnetGeo) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the id property.
     *
     * @return java.lang.Long The value of id
     */
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public java.lang.Long getId() {
        return this.id;
    }
    
    /**
     * Get the ipAddress property.
     *
     * @return String The value of ipAddress
     */
    @javax.persistence.Column(name = "IP_ADDRESS", nullable = false, insertable = true, updatable = true)
    @Index(name = "ip_index")
    public String getIpAddress() {
        return this.ipAddress;
    }
    
    /**
     * Get the mask property.
     *
     * @return String. The value of mask
     */
    
    @javax.persistence.Column(name = "MASK", nullable = false, insertable = true, updatable = true)
    public String getMask() {
        return this.mask;
    }
    
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    @javax.persistence.JoinColumn(name = "REGION_GEO")
    @Index(name = "region_geo_index")
    public RegionGeo getRegion() {
        return this.region;
    }
            
    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(final java.lang.Long value) {
        this.id = value;
    }
    
    /**
     * Set the ipAddress property.
     * @param value the new value
     */
    public void setIpAddress(final String value) {
        this.ipAddress = value;
    }
    
    /**
     * Set the mask property.
     * @param value the new value
     */
    public void setMask(final String value) {
        this.mask = value;
    }
    
    public void setRegion(final RegionGeo region) {
        this.region = region;
    }
    
    public void setYandexX(Float yandexX) {
		this.yandexX = yandexX;
	}

	@javax.persistence.Column(name = "YANDEX_M_X", nullable = false, insertable = true, updatable = true)
	public Float getYandexX() {
		return yandexX;
	}

	public void setYandexY(Float yandexY) {
		this.yandexY = yandexY;
	}

	@javax.persistence.Column(name = "YANDEX_M_Y", nullable = false, insertable = true, updatable = true)
	public Float getYandexY() {
		return yandexY;
	}

    @javax.persistence.Column(name = "CITY", nullable = false, insertable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 29 * hashCode + (this.getId() == null ? 0 : this.getId().hashCode());
        
        return hashCode;
    }
    
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegionSubnetGeo(=");
        sb.append("ipAddress: ");
        sb.append(this.getIpAddress());
        sb.append(", mask: ");
        sb.append(this.getMask());
        sb.append(", yandexX: ");
        sb.append(this.getYandexX());
        sb.append(", yandexY: ");
        sb.append(this.getYandexX());
        sb.append(", id: ");
        sb.append(this.getId());
        sb.append(")");
        return sb.toString();
    }

	}