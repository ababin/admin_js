package ru.cti.oss.iptv.resource.logical;

import org.hibernate.annotations.Index;

/**The Class <code>Picture</code> is entity for present all pictures in TVE (images, posters, etc.)
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2009
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *	@author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 *	@version 1.0
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "PICTURE")
@javax.persistence.NamedQuery(name = "Picture.findAll", query = "select picture from Picture AS picture")
public class Picture implements java.io.Serializable, Comparable < Picture >
{
    
    // ----------- Attribute Definitions ------------
    
    private static final long serialVersionUID = 1826327690848474757L;
    private java.lang.String name;
    private java.lang.String description;
    private ru.cti.oss.iptv.Image logo = new ru.cti.oss.iptv.Image();
    private java.lang.Long id;
    private Movie movie;
    private ru.cti.oss.iptv.resource.logical.PictureOrientationProportionType orientationProportionType;
    
    // --------------- Constructors -----------------
    
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinColumn(name = "MOVIE") 
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Default empty constructor
     */
    public Picture() {
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
    public Picture(final java.lang.String name, final java.lang.String description, final ru.cti.oss.iptv.Image logo) {
        this.setName(name);
        this.setDescription(description);
        this.setLogo(logo);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(final Picture o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getName() != null) {
                cmp = (cmp != 0 ? cmp : this.getName().compareTo(o.getName()));
            }
            if (this.getDescription() != null) {
                cmp = (cmp != 0 ? cmp : this.getDescription().compareTo(o.getDescription()));
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
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Picture)) {
            return false;
        }
        final Picture that = (Picture) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    
    

	@javax.persistence.Column(name = "TYPE", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.ORDINAL)
    public ru.cti.oss.iptv.resource.logical.PictureOrientationProportionType getOrientationProportionType() {
		return orientationProportionType;
	}

	public void setOrientationProportionType(ru.cti.oss.iptv.resource.logical.PictureOrientationProportionType orientationProportionType) {
		this.orientationProportionType = orientationProportionType;
	}

	/**
     * Get the description property.
     * 
     * @return java.lang.String The value of description
     */
    @javax.persistence.Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true)
    public java.lang.String getDescription() {
        return this.description;
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
    
    /**
     * Get the logo property.
     * 
     * @return ru.cti.oss.iptv.Image The value of logo
     */
    @javax.persistence.Embedded
    @javax.persistence.AttributeOverrides( {
            @javax.persistence.AttributeOverride(name = "width", column = @javax.persistence.Column(name = "LOGO_WIDTH", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "height", column = @javax.persistence.Column(name = "LOGO_HEIGHT", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "size", column = @javax.persistence.Column(name = "LOGO_SIZE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "contentType", column = @javax.persistence.Column(name = "LOGO_CONTENT_TYPE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "data", column = @javax.persistence.Column(name = "LOGO_DATA", insertable = true, updatable = true)) })
    public ru.cti.oss.iptv.Image getLogo() {
        return this.logo;
    }
    
    /**
     * Get the name property.
     * 
     * @return java.lang.String The value of name
     */
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    @Index(name = "name_index")
    public java.lang.String getName() {
        return this.name;
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
    
    /**
     * Set the description property.
     * @param value the new value
     */
    public void setDescription(final java.lang.String value) {
        this.description = value;
    }
    
    // ------------- Relations ------------------
    
    // -------- Common Methods -----------
    
    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(final java.lang.Long value) {
        this.id = value;
    }
    
    /**
     * Set the logo property.
     * @param value the new value
     */
    public void setLogo(final ru.cti.oss.iptv.Image value) {
        this.logo = value;
    }
    
    /**
     * Set the name property.
     * @param value the new value
     */
    public void setName(final java.lang.String value) {
        this.name = value;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Picture(=");
        sb.append("name: ");
        sb.append(this.getName());
        sb.append(", description: ");
        sb.append(this.getDescription());
        sb.append(", logo: ");
        if (this.getLogo() != null) {
            sb.append(this.getLogo().toString());
        } else {
            sb.append("null");
        }
        sb.append(", id: ");
        sb.append(this.getId());
        sb.append(")");
        return sb.toString();
    }
}