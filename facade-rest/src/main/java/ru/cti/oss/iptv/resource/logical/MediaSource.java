package ru.cti.oss.iptv.resource.logical;

import javax.persistence.CascadeType;

import org.hibernate.annotations.Index;

import ru.cti.oss.iptv.resource.physical.BandwidthRating;
import ru.cti.oss.iptv.resource.physical.ResolutionType;

/**
 * Autogenerated POJO EJB class for MediaSource containing the
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
@javax.persistence.Table(name = "MEDIA_SOURCE")
@javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@javax.persistence.NamedQuery(name = "MediaSource.findAll", query = "select mediaSource from MediaSource AS mediaSource")
public class MediaSource extends ru.cti.oss.cbe.resource.LogicalResource implements java.io.Serializable
{
    
    private static final long serialVersionUID = 5948233974656606586L;
    
    // ----------- Attribute Definitions ------------
    
    private ru.cti.oss.iptv.resource.logical.EncryptionType encryption;
    private java.lang.Boolean copyProtection;
    private ru.cti.oss.iptv.resource.logical.MediaEncodingType encoding;
    private java.lang.Long bitrate;
    private java.lang.Integer volume;
    private java.lang.String protocol;
    private java.lang.String application;
    private BandwidthRating bandwidthRating;
    private ResolutionType resolution;
    private Integer casId;
    private NetworkType networkType;
    
    // --------- Relationship Definitions -----------
    
    private java.util.Set < ru.cti.oss.iptv.resource.logical.SubtitleChannel > subtitleChannels = new java.util.TreeSet < ru.cti.oss.iptv.resource.logical.SubtitleChannel >();
    private ru.cti.oss.iptv.resource.logical.MediaContent mediaContent;
    private java.util.Set < ru.cti.oss.iptv.resource.logical.AudioChannel > audioChannels = new java.util.TreeSet < ru.cti.oss.iptv.resource.logical.AudioChannel >();
            
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public MediaSource() {
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param encryption Value for the encryption property
     * @param copyProtection Value for the copyProtection property
     * @param encoding Value for the encoding property
     * @param bitrate Value for the bitrate property
     * @param volume Value for the volume property
     * @param subtitleChannels Value for the subtitleChannels relation
     * @param mediaContent Value for the mediaContent relation
     * @param audioChannels Value for the audioChannels relation
     */
    public MediaSource(final java.lang.String name, final java.lang.String description,
            final ru.cti.oss.cbe.datatypes.LifeCycleState status, final ru.cti.oss.iptv.resource.logical.EncryptionType encryption,
            final java.lang.Boolean copyProtection, final ru.cti.oss.iptv.resource.logical.MediaEncodingType encoding,
            final java.lang.Long bitrate, final java.lang.Integer volume,
            final java.util.Set < ru.cti.oss.iptv.resource.logical.SubtitleChannel > subtitleChannels,
            final ru.cti.oss.iptv.resource.logical.MediaContent mediaContent,
            final java.util.Set < ru.cti.oss.iptv.resource.logical.AudioChannel > audioChannels) {
        this.setName(name);
        this.setDescription(description);
        this.setStatus(status);
        this.setEncryption(encryption);
        this.setCopyProtection(copyProtection);
        this.setEncoding(encoding);
        this.setBitrate(bitrate);
        this.setVolume(volume);
        
        this.setSubtitleChannels(subtitleChannels);
        this.setMediaContent(mediaContent);
        this.setAudioChannels(audioChannels);
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param encryption Value for the encryption property
     * @param copyProtection Value for the copyProtection property
     * @param encoding Value for the encoding property
     * @param bitrate Value for the bitrate property
     * @param volume Value for the volume property
     */
    public MediaSource(final java.lang.String name, final java.lang.String description,
            final ru.cti.oss.cbe.datatypes.LifeCycleState status, final ru.cti.oss.iptv.resource.logical.EncryptionType encryption,
            final java.lang.Boolean copyProtection, final ru.cti.oss.iptv.resource.logical.MediaEncodingType encoding,
            final java.lang.Long bitrate, final java.lang.Integer volume, final String application, final String protocol) {
        super(name, description, status);
        this.setName(name);
        this.setDescription(description);
        this.setStatus(status);
        this.setEncryption(encryption);
        this.setCopyProtection(copyProtection);
        this.setEncoding(encoding);
        this.setBitrate(bitrate);
        this.setVolume(volume);
        this.setApplication(description);
        this.setProtocol(description);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Add to audioChannels
     *
     * @param audioChannel
     */
    
    public void addToAudioChannels(final ru.cti.oss.iptv.resource.logical.AudioChannel audioChannel) {
        if (audioChannel == null) {
            return;
        }
        this.getAudioChannels().add(audioChannel);
        audioChannel.setMediaSource(this);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Add to subtitleChannels
     *
     * @param subtitleChannel
     */
    
    public void addToSubtitleChannels(final ru.cti.oss.iptv.resource.logical.SubtitleChannel subtitleChannel) {
        if (subtitleChannel == null) {
            return;
        }
        this.getSubtitleChannels().add(subtitleChannel);
        subtitleChannel.setMediaSource(this);
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
        if (!(object instanceof MediaSource)) {
            return false;
        }
        final MediaSource that = (MediaSource) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    // -------- Common Methods -----------
    
    @javax.persistence.Column(name = "APPLICATION", insertable = true, updatable = true, nullable = true)
    public java.lang.String getApplication() {
        return this.application;
    }
    
    /**
     * Get the audioChannels Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.AudioChannel>
     */
    @javax.persistence.OneToMany(mappedBy = "mediaSource", fetch = javax.persistence.FetchType.LAZY, cascade = { CascadeType.MERGE,
            CascadeType.REMOVE, CascadeType.REFRESH })
    public java.util.Set < ru.cti.oss.iptv.resource.logical.AudioChannel > getAudioChannels() {
        return this.audioChannels;
    }
    
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.EAGER)
    @javax.persistence.JoinColumn(name = "BANDWIDTHRATING")
    public BandwidthRating getBandwidthRating() {
        return this.bandwidthRating;
    }
    
    /**
     * Get the bitrate property.
     * 
     * @return java.lang.Long The value of bitrate
     */
    @javax.persistence.Column(name = "BITRATE", insertable = true, updatable = true)
    @Index(name = "bitrate_index")
    public java.lang.Long getBitrate() {
        return this.bitrate;
    }
    
    /**
     * Get the copyProtection property.
     * 
     * @return java.lang.Boolean The value of copyProtection
     */
    @javax.persistence.Column(name = "COPY_PROTECTION", insertable = true, updatable = true)
    public java.lang.Boolean getCopyProtection() {
        return this.copyProtection;
    }
    
    /**
     * Get the encoding property.
     * 
     * @return ru.cti.oss.iptv.resource.logical.MediaEncodingType The value of encoding
     */
    @javax.persistence.Column(name = "ENCODING", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ru.cti.oss.iptv.resource.logical.MediaEncodingType getEncoding() {
        return this.encoding;
    }
    
    /**
     * Get the encryption property.
     * 
     * @return ru.cti.oss.iptv.resource.logical.EncryptionType The value of encryption
     */
    @javax.persistence.Column(name = "ENCRYPTION", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ru.cti.oss.iptv.resource.logical.EncryptionType getEncryption() {
        return this.encryption;
    }
            
    /**
     * Get the mediaContent
     *
     * @return ru.cti.oss.iptv.resource.logical.MediaContent
     */
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = true)
    @javax.persistence.JoinColumn(name = "CONTENT_ID", nullable = true)
    public ru.cti.oss.iptv.resource.logical.MediaContent getMediaContent() {
        return this.mediaContent;
    }
    
    @javax.persistence.Column(name = "PROTOCOL", insertable = true, updatable = true, nullable = false)
    public java.lang.String getProtocol() {
        return this.protocol;
    }
    
    @javax.persistence.Column(name = "RESOLUTION", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ResolutionType getResolution() {
        return this.resolution;
    }
    
    /**
     * Get the subtitleChannels Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.SubtitleChannel>
     */
    @javax.persistence.OneToMany(mappedBy = "mediaSource", fetch = javax.persistence.FetchType.LAZY, cascade = { CascadeType.MERGE,
            CascadeType.REMOVE, CascadeType.REFRESH })
    public java.util.Set < ru.cti.oss.iptv.resource.logical.SubtitleChannel > getSubtitleChannels() {
        return this.subtitleChannels;
    }
    
    /**
     * Get the volume property.
     * 
     * @return java.lang.Integer The value of volume
     */
    @javax.persistence.Column(name = "VOLUME", nullable = false, insertable = true, updatable = true)
    @Index(name = "volume_index")
    public java.lang.Integer getVolume() {
        return this.volume;
    }
    
    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        hashCode = (29 * hashCode) + (this.getId() == null ? 0 : this.getId().hashCode());
        
        return hashCode;
    }
    
    /**
     * Remove from audioChannels
     *
     * @param audioChannel
     */
    
    public void removeFromAudioChannels(final ru.cti.oss.iptv.resource.logical.AudioChannel audioChannel) {
        if (audioChannel == null) {
            return;
        }
        this.getAudioChannels().remove(audioChannel);
        audioChannel.setMediaSource(null);
    }
    
    /**
     * Remove from subtitleChannels
     *
     * @param subtitleChannel
     */
    
    public void removeFromSubtitleChannels(final ru.cti.oss.iptv.resource.logical.SubtitleChannel subtitleChannel) {
        if (subtitleChannel == null) {
            return;
        }
        this.getSubtitleChannels().remove(subtitleChannel);
        subtitleChannel.setMediaSource(null);
    }
    
    public void setApplication(final java.lang.String application) {
        this.application = application;
    }
    
    /**
     * Set the audioChannels
     *
     * @param audioChannels
     */
    public void setAudioChannels(final java.util.Set < ru.cti.oss.iptv.resource.logical.AudioChannel > audioChannels) {
        this.audioChannels = audioChannels;
    }
    
    // ------------- Relations ------------------
    
    public void setBandwidthRating(final BandwidthRating bandwidthRating) {
        this.bandwidthRating = bandwidthRating;
    }
    
    /**
     * Set the bitrate property.
     * @param value the new value
     */
    public void setBitrate(final java.lang.Long value) {
        this.bitrate = value;
    }
    
    /**
     * Set the copyProtection property.
     * @param value the new value
     */
    public void setCopyProtection(final java.lang.Boolean value) {
        this.copyProtection = value;
    }
    
    /**
     * Set the encoding property.
     * @param value the new value
     */
    public void setEncoding(final ru.cti.oss.iptv.resource.logical.MediaEncodingType value) {
        this.encoding = value;
    }
    
    /**
     * Set the encryption property.
     * @param value the new value
     */
    public void setEncryption(final ru.cti.oss.iptv.resource.logical.EncryptionType value) {
        this.encryption = value;
    }
            
    /**
     * Set the mediaContent
     *
     * @param mediaContent
     */
    public void setMediaContent(final ru.cti.oss.iptv.resource.logical.MediaContent mediaContent) {
        this.mediaContent = mediaContent;
    }
    
    public void setProtocol(final java.lang.String protocol) {
        this.protocol = protocol;
    }
    
    public void setResolution(final ResolutionType resolution) {
        this.resolution = resolution;
    }
    
    // -------- Common Methods -----------
    
    /**
     * Set the subtitleChannels
     *
     * @param subtitleChannels
     */
    public void setSubtitleChannels(final java.util.Set < ru.cti.oss.iptv.resource.logical.SubtitleChannel > subtitleChannels) {
        this.subtitleChannels = subtitleChannels;
    }
    
    /**
     * Set the volume property.
     * @param value the new value
     */
    public void setVolume(final java.lang.Integer value) {
        this.volume = value;
    }
    
    @javax.persistence.Column(name = "CAS_ID", nullable = true, insertable = true, updatable = true)
    public Integer getCasId() {
		return casId;
	}

	public void setCasId(Integer casId) {
		this.casId = casId;
	}

	@javax.persistence.Column(name = "NETWORK_TYPE", insertable = true, updatable = true, nullable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	public NetworkType getNetworkType() {
		return networkType;
	}

	public void setNetworkType(NetworkType networkType) {
		this.networkType = networkType;
	}
    
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediaSource(=");
        sb.append(super.toString());
        sb.append("encryption: ");
        sb.append(this.getEncryption());
        sb.append(", copyProtection: ");
        sb.append(this.getCopyProtection());
        sb.append(", encoding: ");
        sb.append(this.getEncoding());
        sb.append(", bitrate: ");
        sb.append(this.getBitrate());
        sb.append(", volume: ");
        sb.append(this.getVolume());
        sb.append(", protocol: ");
        sb.append(this.getProtocol());
        sb.append(", application: ");
        sb.append(this.getApplication());
        sb.append(", casId: ");
        sb.append(this.getCasId());
        sb.append(", networkType: ");
        sb.append(this.getNetworkType());
        sb.append(")");
        return sb.toString();
    }

	
}