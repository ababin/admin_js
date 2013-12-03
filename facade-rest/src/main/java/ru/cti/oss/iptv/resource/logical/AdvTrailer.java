package ru.cti.oss.iptv.resource.logical;

import javax.persistence.Transient;

import org.hibernate.annotations.Index;

/**The Class <code>AdvTrailer</code> is entity for present all adv trailers in TVE 
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2009
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *	@author a.babin <br/> <b>e-mail</b>: a.babin@cti.ru <br/>
 * @author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 *	@version 1.1
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "ADVTRAILER")
@javax.persistence.NamedQuery(name = "AdvTrailer.findAll", query = "select advTrailer from AdvTrailer AS advTrailer")
public class AdvTrailer implements java.io.Serializable
{
    
    private static final long serialVersionUID = 1826327690848474757L;
    private java.lang.Long id;
    private java.lang.Boolean rewindable;
    private boolean selected =false;
    private ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset;
    
    // --------------- Constructors -----------------
    /**
     * Default empty constructor
     */
    public AdvTrailer() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param logo Value for the logo property
     */
    public AdvTrailer(final ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset, final java.lang.Boolean rewindable) {
        this.setMediaAsset(mediaAsset);
        this.setRewindable(rewindable);
    }
    
    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AdvTrailer)) {
            return false;
        }
        final AdvTrailer that = (AdvTrailer) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    /**
     * Get the id property.
     * 
     * @return java.lang.Long The value of id
     */
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Index(name = "id_index")
    public java.lang.Long getId() {
        return this.id;
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the mediaAsset property.
     * @return ru.cti.oss.iptv.resource.logical.MediaAsset
     */
    @javax.persistence.OneToOne(fetch = javax.persistence.FetchType.LAZY, optional = false)
    @javax.persistence.JoinColumn(name = "MEDIAASSET")
    public ru.cti.oss.iptv.resource.logical.MediaAsset getMediaAsset() {
        return this.mediaAsset;
    }
    
    /**
     * Get the rewindable property.
     * 
     * @return Boolean
     */
    @javax.persistence.Column(name = "REWINDABLE", nullable = false, insertable = true, updatable = true)
    public java.lang.Boolean getRewindable() {
        return this.rewindable;
    }
    
    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    public int hashCode() {
        int hashCode = 0;
        hashCode = 29 * hashCode + (this.getId() == null ? 0 : this.getId().hashCode());
        
        return hashCode;
    }
    
    @Transient
    public boolean isSelected() {
        return this.selected;
    }
    
    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(final java.lang.Long value) {
        this.id = value;
    }
    
    /**
     * Set the mediaAsset property.
     * @param mediaAsset ru.cti.oss.iptv.resource.logical.MediaAsset
     */
    public void setMediaAsset(final ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset) {
        this.mediaAsset = mediaAsset;
    }
    
    /**
     * Set rewindable property.
     * @param rewindable Boolean
     */
    public void setRewindable(final java.lang.Boolean rewindable) {
        this.rewindable = rewindable;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    // -------- Common Methods -----------
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("advTrailer(=");
        
        sb.append(", id: ");
        sb.append(this.getId());
        
        sb.append("mediaAsset:(=");
        if (this.getMediaAsset() != null) {
            sb.append("name: ");
            sb.append(this.getMediaAsset().getName());
            sb.append(", description: ");
            sb.append(this.getMediaAsset().getDescription());
            sb.append(")");
        } else {
            sb.append("null)");
        }
        
        sb.append(", rewindable: ");
        sb.append(this.getRewindable());
        sb.append(")");
        
        return sb.toString();
    }
    
}