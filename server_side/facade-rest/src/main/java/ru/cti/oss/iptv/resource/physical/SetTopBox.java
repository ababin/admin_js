package ru.cti.oss.iptv.resource.physical;

import java.io.Serializable;

import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import ru.cti.oss.cbe.resource.IResource;

/**The Class <code>SetTopBox</code> is entity presented physical or pc Set Top Box 
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2007-2011
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *	@author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 *  @author n.makarov <br/> <b>e-mail</b>: n.makarov@cti.ru <br/>
 * 
 *	@version 2.0
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "SET_TOP_BOX")
@javax.persistence.NamedQueries({ @javax.persistence.NamedQuery(name = "SetTopBox.getCount", query = "select count(*) from SetTopBox"),
        @javax.persistence.NamedQuery(name = "SetTopBox.getCountByType", query = "SELECT count(*) FROM SetTopBox WHERE stbType=UPPER(:stbType)"),
        @javax.persistence.NamedQuery(name = "SetTopBox.getCountWithoutTypes", query = "SELECT count(*) FROM SetTopBox WHERE UPPER(stbType) NOT IN (:types)"),
        @javax.persistence.NamedQuery(name = "SetTopBox.findAll", query = "select setTopBox from SetTopBox AS setTopBox"),
        @javax.persistence.NamedQuery(name = "SetTopBox.findByMAC", query = "select s from SetTopBox s where upper(s.mac) =upper( :mac )"),
        @javax.persistence.NamedQuery(name = "SetTopBox.findByMACAndOwnerId", query = "SELECT s FROM SetTopBox s WHERE UPPER(s.mac) =UPPER(:mac) AND s.owner.id=:ownerId"),
        @javax.persistence.NamedQuery(name = "SetTopBox.findByOwner", query = "select s from SetTopBox s where s.owner.id = :customerId"),
        @javax.persistence.NamedQuery(name = "SetTopBox.findByMacWithoutUpperCase", query = "select s from SetTopBox s where s.mac = :mac"), 
        @javax.persistence.NamedQuery(name = "SetTopBox.findByEmptyMacAndSerialForCustomer", 
        	query = "SELECT s FROM SetTopBox s " +
        			"WHERE " +
        			"  ( s.mac IS NULL " +
        			"    OR TRIM(s.mac) = '' " +
        			"  ) " +
        			"  AND " +
        			"  ( s.serialNumber IS NULL " +
        			"    OR TRIM(s.serialNumber) = '' " +
        			"    OR s.serialNumber = :serialNumber" +
        			"  ) " +
        			"  AND s.owner.id = :customerId"),
        @javax.persistence.NamedQuery(name = "SetTopBox.getListOfTypeStbs", query = "select count(s.id), d.name from SetTopBox as s join s.deviceProfile as d " +
        																			" where " +
        																			"s.stbType = 'STB' " +
        																			"group by d.name"),
       	@javax.persistence.NamedQuery(name = "SetTopBox.findByExternalId", query = "select s from SetTopBox s where s.externalId = :externalId"),
       	@javax.persistence.NamedQuery(name = "SetTopBox.findByAccountNumber", query = "select stb from SetTopBox stb where stb.owner.id in (select c.id from Customer c where c.name = :accountNumber)"),
        @javax.persistence.NamedQuery(name = "SetTopBox.findByDeviceId", query = "SELECT s from SetTopBox s where s.deviceId = :deviceId")})
public class SetTopBox implements Comparable < SetTopBox >, Serializable, IResource
{
    
    private static final long serialVersionUID = -8919052312781185804L;
    
    private java.lang.String mac;
    
    private java.lang.String serialNumber;
    
    private java.lang.String externalId;
    
    private java.lang.String externalId1;

    private String deviceId;

    private String iccid;

    private String phone;
    
    @Transient
    public static final String STATUS_VMX_CERTIFICATE_REVOKED = "VMX_CERTIFICATE_REVOKED"; 
    
    // ----------- Attribute Definitions ------------
    
    private ru.cti.oss.cbe.datatypes.AvailabilityStatus availabilityStatus;
    private ru.cti.oss.cbe.datatypes.LifeCycleState status;
    private String statusDescription;
    private java.lang.Long id;
    private String stbType;
    private ru.cti.oss.cbe.customer.Customer owner;
    
    private java.lang.String ownerLabel; // Manageable display attribute
    
    private DeviceProfile deviceProfile;
    private BandwidthRating bandwidthRating;
    
    private java.lang.Long firstAccessTime;
    
    private java.lang.Long lastAccessTime;
    
    /**
     * Default empty constructor
     */
    public SetTopBox() {
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param mac Value for the mac property
     * @param serialNumber Value for the serialNumber property
     * @param name Value for the name property
     * @param availabilityStatus Value for the availabilityStatus property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param customer Value for the customer relation
     */
    public SetTopBox(final java.lang.String mac, final java.lang.String serialNumber, final java.lang.String name,
            final ru.cti.oss.cbe.datatypes.AvailabilityStatus availabilityStatus, final java.lang.String description,
            final ru.cti.oss.cbe.datatypes.LifeCycleState status, final ru.cti.oss.cbe.customer.Customer owner) {
        this.setMac(mac);
        this.setSerialNumber(serialNumber);
        this.setAvailabilityStatus(availabilityStatus);
        this.setStatus(status);
        this.setOwner(owner);
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param mac Value for the mac property
     * @param serialNumber Value for the serialNumber property
     * @param name Value for the name property
     * @param availabilityStatus Value for the availabilityStatus property
     * @param description Value for the description property
     * @param status Value for the status property
     */
    public SetTopBox(final java.lang.String mac, final java.lang.String serialNumber,
            final ru.cti.oss.cbe.datatypes.AvailabilityStatus availabilityStatus, final ru.cti.oss.cbe.datatypes.LifeCycleState status) {
        this.setMac(mac);
        this.setSerialNumber(serialNumber);
        this.setAvailabilityStatus(availabilityStatus);
        this.setStatus(status);
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    @Override
    public int compareTo(final SetTopBox o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getMac() != null) {
                cmp = (cmp != 0 ? cmp : this.getMac().compareTo(o.getMac()));
            }
            if (this.getSerialNumber() != null) {
                cmp = (cmp != 0 ? cmp : this.getSerialNumber().compareTo(o.getSerialNumber()));
            }
            if (this.getAvailabilityStatus() != null) {
                cmp = (cmp != 0 ? cmp : this.getAvailabilityStatus().compareTo(o.getAvailabilityStatus()));
            }
            if (this.getStatus() != null) {
                cmp = (cmp != 0 ? cmp : this.getStatus().compareTo(o.getStatus()));
            }
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
        if (!(object instanceof SetTopBox)) {
            return false;
        }
        final SetTopBox that = (SetTopBox) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    
    @javax.persistence.Column(name = "EXTERNAL_ID", unique = true, nullable = true, insertable = true, updatable = true)
    public java.lang.String getExternalId() {
		return externalId;
	}

	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}


    @javax.persistence.Column(name = "DEVICE_ID", unique = true, nullable = true, insertable = true, updatable = true)
    public java.lang.String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(java.lang.String deviceId) {
        this.deviceId = deviceId;
    }

    @javax.persistence.Column(name = "ICCID", unique = true, nullable = true, insertable = true, updatable = true)
    public java.lang.String getIccid() {
        return iccid;
    }

    public void setIccid(java.lang.String iccid) {
        this.iccid = iccid;
    }


    @javax.persistence.Column(name = "PHONE", unique = true, nullable = true, insertable = true, updatable = true)
    public java.lang.String getPhone() {
        return phone;
    }

    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }
	
	/**
	 * @return the externalId1
	 */
	@javax.persistence.Column(name = "EXTERNAL_ID1", unique = false, nullable = true, insertable = true, updatable = true)
	public java.lang.String getExternalId1() {
		return externalId1;
	}

	/**
	 * @param externalId1 the externalId1 to set
	 */
	public void setExternalId1(java.lang.String externalId1) {
		this.externalId1 = externalId1;
	}

	/**
     * Get the availabilityStatus property.
     * 
     * @return ru.cti.oss.cbe.datatypes.AvailabilityStatus The value of availabilityStatus
     */
    @javax.persistence.Column(name = "AVAILABILITY_STATUS", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    @Index(name = "availability_status_index")
    public ru.cti.oss.cbe.datatypes.AvailabilityStatus getAvailabilityStatus() {
        return this.availabilityStatus;
    }
    
    /**
     * Get the bandwidth rating
     *
     * @return ru.cti.oss.iptv.common.Rating
     */
    @javax.persistence.OneToOne(fetch = javax.persistence.FetchType.EAGER)
    @javax.persistence.JoinColumn(name = "BANDWIDTH_RATING")
    public BandwidthRating getBandwidthRating() {
        return this.bandwidthRating;
    }
    
    @javax.persistence.OneToOne(fetch = javax.persistence.FetchType.EAGER)
    @javax.persistence.JoinColumn(name = "DEVICE_PROFILE")
    public DeviceProfile getDeviceProfile() {
        return this.deviceProfile;
    }
    
    // --------- Relationship Definitions -----------
    
    public java.lang.Long getFirstAccessTime() {
        return this.firstAccessTime;
    }
    
    /**
     * Get the id property.
     * 
     * @return java.lang.Long The value of id
     */
    @Override
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public java.lang.Long getId() {
        return this.id;
    }
    
    /**
     * Get the mac property.
     * 
     * @return java.lang.String The value of mac
     */
    @javax.persistence.Column(name = "MAC", insertable = true, updatable = true)
    @Index(name = "mac_index")
    public java.lang.String getMac() {
        return this.mac;
    }
    
    /**
     * Get the customer
     *
     * @return ru.cti.oss.cbe.customer.Customer
     */
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.EAGER)
    @javax.persistence.JoinColumn(name = "OWNER")
    public ru.cti.oss.cbe.customer.Customer getOwner() {
        return this.owner;
    }
    
    /**
     * Get the customerLabel
     *
     * @return java.lang.String
     */
    @javax.persistence.Transient
    public java.lang.String getOwnerLabel() {
        return this.ownerLabel;
    }
    
    /**
     * Get the serialNumber property.
     * 
     * @return java.lang.String The value of serialNumber
     */
    @javax.persistence.Column(name = "SERIAL_NUMBER", insertable = true, updatable = true)
    public java.lang.String getSerialNumber() {
        return this.serialNumber;
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the status property.
     * 
     * @return ru.cti.oss.cbe.datatypes.LifeCycleState The value of status
     */
    @javax.persistence.Column(name = "STATUS", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ru.cti.oss.cbe.datatypes.LifeCycleState getStatus() {
        return this.status;
    }
    
    @javax.persistence.Column(name = "STATUS_DESC", insertable = true, updatable = true)
    public String getStatusDescription() {
        return this.statusDescription;
    }
    
    public String getStbType() {
        return this.stbType;
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
     * Set the availabilityStatus property.
     * @param value the new value
     */
    public void setAvailabilityStatus(final ru.cti.oss.cbe.datatypes.AvailabilityStatus value) {
        this.availabilityStatus = value;
    }
    
    public void setBandwidthRating(final BandwidthRating bandwidthRating) {
        this.bandwidthRating = bandwidthRating;
    }
    
    public void setDeviceProfile(final DeviceProfile deviceProfile) {
        this.deviceProfile = deviceProfile;
    }
    
    public void setFirstAccessTime(final java.lang.Long firstAccessTime) {
        this.firstAccessTime = firstAccessTime;
    }
    
    /**
    * Set the id property.
    * @param value the new value
    */
    public void setId(final java.lang.Long value) {
        this.id = value;
    }
    
    // ------------- Relations ------------------
    
    /**
     * Set the mac property.
     * @param value the new value
     */
    public void setMac(final java.lang.String value) {
        this.mac = value;
    }
    
    /**
        * Set the customer
        *
        * @param customer
        */
    public void setOwner(final ru.cti.oss.cbe.customer.Customer owner) {
        this.owner = owner;
    }
    
    // -------- Manageable Attribute Display -----------
    
    /**
     * Set the customerLabel
     *
     * @param customerLabel
     */
    public void setOwnerLabel(final java.lang.String ownerLabel) {
        this.ownerLabel = ownerLabel;
    }
    
    /**
     * Set the serialNumber property.
     * @param value the new value
     */
    public void setSerialNumber(final java.lang.String value) {
        this.serialNumber = value;
    }
    
    // -------- Common Methods -----------
    
    /**
     * Set the status property.
     * @param value the new value
     */
    public void setStatus(final ru.cti.oss.cbe.datatypes.LifeCycleState value) {
        this.status = value;
    }
    
    public void setStatusDescription(final String statusDescription) {
        this.statusDescription = statusDescription;
    }
    
    public void setStbType(final String stbType) {
        this.stbType = stbType;
    }
    
    public void setLastAccessTime(java.lang.Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public java.lang.Long getLastAccessTime() {
		return lastAccessTime;
	}
    
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SetTopBox(=");
        sb.append("mac: ");
        sb.append(this.getMac());
        sb.append(", serialNumber: ");
        sb.append(this.getSerialNumber());
        sb.append(", availabilityStatus: ");
        sb.append(this.getAvailabilityStatus());
        sb.append(", status: ");
        sb.append(this.getStatus());
        sb.append(", id: ");
        sb.append(this.getId());
        sb.append(")");
        return sb.toString();
    }
	    
}