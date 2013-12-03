package ru.cti.oss.iptv.resource.physical;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import ru.cti.oss.cbe.resource.IResource;
import ru.cti.oss.iptv.resource.logical.EncryptionType;
import ru.cti.oss.iptv.resource.logical.MediaEncodingType;
import ru.cti.oss.iptv.resource.logical.NetworkType;

/**
 * The Class {@code DeviceProfile} is entity presented profile for STB
 * <p>
 * <b>Copyright: </b>Copyright (c) 2007-2011
 * </p>
 * <p>
 * <b>Company: </b>CTI
 * </p>
 *
 * @author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 * @version 1.2
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "DEVICE_PROFILE")
@javax.persistence.NamedQueries({
        @javax.persistence.NamedQuery(name = "DeviceProfile.getCount", query = "select count(*) from DeviceProfile"),
        @javax.persistence.NamedQuery(name = "DeviceProfile.findAll", query = "select deviceProfile from DeviceProfile AS deviceProfile"),
        @javax.persistence.NamedQuery(name = "DeviceProfile.findByName", query = "select deviceProfile from DeviceProfile deviceProfile where deviceProfile.name = :name")})
public class DeviceProfile implements Comparable<DeviceProfile>, Serializable, IResource {

    private static final long serialVersionUID = -3839978825879650029L;

    // ----------- Attribute Definitions ------------
    private java.lang.Long id;

    private String name;

    private String resolutions;

    private String encryptions;

    private String encodings;

    private String allowpip;
    
    private String allowEPGForDifferentChannel;
    
    private String networks;


	/**
     * Default empty constructor
     */
    public DeviceProfile() {
    }

	/**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param mac                Value for the mac property
     * @param serialNumber       Value for the serialNumber property
     * @param name               Value for the name property
     * @param availabilityStatus Value for the availabilityStatus property
     * @param description        Value for the description property
     * @param status             Value for the status property
     * @param customer           Value for the customer relation
     */
    public DeviceProfile(final java.lang.String name, final List<ResolutionType> resolutions, final List<EncryptionType> encryptions,
                         final List<MediaEncodingType> encodings) {
        this.setName(name);
        this.setResolutionsFromList(resolutions);
        this.setEncryptionsFromList(encryptions);
        this.setEncodingsFromList(encodings);
        this.setAllowpip(allowpip);
    }

    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    @Override
    public int compareTo(final DeviceProfile o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getName() != null) {
                cmp = (this.getName().compareTo(o.getName()));
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
        if (!(object instanceof DeviceProfile)) {
            return false;
        }
        final DeviceProfile that = (DeviceProfile) object;

        return !((that.getId() == null) || !this.getId().equals(that.getId()));
    }

    @javax.persistence.Column(name = "ENCODINGS", insertable = true, updatable = true)
    public String getEncodings() {
        return this.encodings;
    }

    @Transient
    public List<MediaEncodingType> getEncodingsAsList() {
        List<MediaEncodingType> result = new ArrayList<MediaEncodingType>();
        if (this.encodings == null) return result;

        String[] encodingsArray = this.encodings.split(",");
        for (String encString : encodingsArray) {
            if (MediaEncodingType.literals().contains(encString)) {
                result.add(MediaEncodingType.valueOf(encString));
            }
        }
        return result;
    }

    @javax.persistence.Column(name = "ENCRYPTIONS", insertable = true, updatable = true)
    public String getEncryptions() {
        return this.encryptions;
    }

    @Transient
    public List<EncryptionType> getEncryptionsAsList() {
        List<EncryptionType> result = new ArrayList<EncryptionType>();
        if (this.encryptions == null) return result;

        String[] encryptionsArray = this.encryptions.split(",");
        for (String encString : encryptionsArray) {
            if (EncryptionType.literals().contains(encString)) {
                result.add(EncryptionType.valueOf(encString));
            }
        }
        return result;
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

    @javax.persistence.Column(name = "NAME", insertable = true, updatable = true)
    @Index(name = "name_index")
    public String getName() {
        return this.name;
    }

    @javax.persistence.Column(name = "RESOLUTIONS", insertable = true, updatable = true)
    public String getResolutions() {
        return this.resolutions;
    }

    @javax.persistence.Column(name = "ALLOWPIP", insertable = true, updatable = true)
    public String getAllowpip() {
    	return this.allowpip;
    }
    
    @javax.persistence.Column(name = "ALLOW_EPG_DIFF_CHAN", insertable = true, updatable = true)
    public String getAllowEPGForDifferentChannel() {
		return allowEPGForDifferentChannel;
	}

    
    @Transient
    public List<ResolutionType> getResolutionsAsList() {
        List<ResolutionType> result = new ArrayList<ResolutionType>();
        if (this.resolutions == null) return result;

        String[] resolutionsArray = this.resolutions.split(",");
        for (String resString : resolutionsArray) {
            if (ResolutionType.literals().contains(resString)) {
                result.add(ResolutionType.valueOf(resString));
            }
        }
        return result;
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

    public void setEncodings(final String encodings) {
        this.encodings = encodings;
    }

    @Transient
    public void setEncodingsFromList(final List<MediaEncodingType> encodings) {
        StringBuilder result = new StringBuilder();
        int currentElement = 0;
        for (MediaEncodingType mediaEncodingType : encodings) {
            result.append(mediaEncodingType);
            if (currentElement < encodings.size() - 1) {
                result.append(",");
            }
            currentElement++;
        }

        this.encodings = result.toString();
    }

    public void setEncryptions(final String encryptions) {
        this.encryptions = encryptions;
    }

    @Transient
    public void setEncryptionsFromList(final List<EncryptionType> encryptions) {
        StringBuilder result = new StringBuilder();
        int currentElement = 0;
        for (EncryptionType encryptionType : encryptions) {
            result.append(encryptionType);
            if (currentElement < encryptions.size() - 1) {
                result.append(",");
            }
            currentElement++;
        }

        this.encryptions = result.toString();
    }

    /**
     * Set the id property.
     *
     * @param value the new value
     */
    public void setId(final java.lang.Long value) {
        this.id = value;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setResolutions(final String resolutions) {
        this.resolutions = resolutions;
    }
    

	public void setAllowpip(String allowpip) {
		this.allowpip = allowpip;
	}

	public void setAllowEPGForDifferentChannel(String allowEPGForDifferentChannel) {
		this.allowEPGForDifferentChannel = allowEPGForDifferentChannel;
	}
	
    @Transient
    public void setResolutionsFromList(final List<ResolutionType> resolutions) {
        StringBuilder result = new StringBuilder();
        int currentElement = 0;
        for (ResolutionType resolutionType : resolutions) {
            result.append(resolutionType);
            if (currentElement < resolutions.size() - 1) {
                result.append(",");
            }
            currentElement++;
        }

        this.resolutions = result.toString();
    }
    
    @javax.persistence.Column(name = "NETWORKS", insertable = true, updatable = true)
	public String getNetworks() {
		return networks;
	}
    
    public void setNetworks(String networks) {
		this.networks = networks;
	}
    
    @Transient
    public List <NetworkType> getNetworksAsList() {
        return new ArrayList <NetworkType>(getNetworksAsSet());
    }
    
    @Transient
    public Set <NetworkType> getNetworksAsSet() {
        return NetworkType.fromListString(networks);
    }

	@Transient
    public void setNetworksFromList(final Collection <NetworkType> networks) {
		setNetworks(NetworkType.toListString(networks));
    }
    
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceProfile(=");
        sb.append("name: ");
        sb.append(this.getName());
        sb.append(", resolutionType: ");
        sb.append(this.getResolutions());
        sb.append(", encodingType: ");
        sb.append(this.getEncodings());
        sb.append(", encryptionType: ");
        sb.append(this.getEncryptions());
        sb.append(", networks: ");
        sb.append(this.getNetworks());
        sb.append(", id: ");
        sb.append(this.getId());
        sb.append(")");
        return sb.toString();
    }

}