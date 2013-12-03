// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.iptv.resource.logical;

import java.util.Set;

import javax.persistence.CascadeType;

import org.hibernate.annotations.Index;

/**
 *The class <code>Region</code> is rating for bandwidth. <br/>
 * 
 
 *	<p>
 *  <b> Copyright: </b>Copyright (c) 2009-2011
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *	@author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 *  
 *  <b>Last refactoring: 24.02.2011 </b> <br/>
 *  @since 24.02.2011
 *	@version 1.0.0
 *
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "REGION")
@javax.persistence.NamedQueries({
        @javax.persistence.NamedQuery(name = "Region.findAll", query = "select region from Region AS region order by code"),
        @javax.persistence.NamedQuery(name = "Region.findRegionByName", query = "from Region r where r.name= :name"),
        @javax.persistence.NamedQuery(name = "Region.findRegionByCode", query = "from Region r where r.code= :code") })
public class Region implements java.io.Serializable, Comparable < Region >
{
    
    private static final long serialVersionUID = -3188576950991030629L;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.String name;
    private java.lang.String code;
    private java.lang.String description;
    private java.lang.Long id;
    
    // ---- Manageable Display Attributes (Transient) -----
    private Set < RegionSubnet > regionSubnets;
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public Region() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param code Value for the code property
     * @param description Value for the description property
     * @param level Value for the level property
     */
    public Region(final java.lang.String name, final java.lang.String code, final java.lang.String description) {
        this.setName(name);
        this.setCode(code);
        this.setDescription(description);
        
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    @Override
    public int compareTo(final Region o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getName() != null) {
                cmp = (cmp != 0 ? cmp : this.getName().compareTo(o.getName()));
            }
            if (this.getCode() != null) {
                cmp = (cmp != 0 ? cmp : this.getCode().compareTo(o.getCode()));
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
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Region)) {
            return false;
        }
        final Region that = (Region) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the code property.
     * 
     * @return java.lang.String The value of code
     */
    @javax.persistence.Column(name = "CODE", nullable = false, insertable = true, updatable = true , unique = true)
    @Index(name = "code_index")
    public java.lang.String getCode() {
        return this.code;
    }
    
    /**
     * Get the description property.
     * 
     * @return java.lang.String The value of description
     */
    @javax.persistence.Column(name = "DESCRIPTION", insertable = true, updatable = true)
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
     * Get the name property.
     * 
     * @return java.lang.String The value of name
     */
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    @Index(name = "name_index")
    public java.lang.String getName() {
        return this.name;
    }
    
    @javax.persistence.OneToMany(mappedBy = "region", fetch = javax.persistence.FetchType.EAGER, cascade = { CascadeType.MERGE,
            CascadeType.REMOVE, CascadeType.REFRESH })
    public Set < RegionSubnet > getRegionSubnets() {
        return this.regionSubnets;
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
     * Set the code property.
     * @param value the new value
     */
    public void setCode(final java.lang.String value) {
        this.code = value;
    }
    
    /**
     * Set the description property.
     * @param value the new value
     */
    public void setDescription(final java.lang.String value) {
        this.description = value;
    }
    
    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(final java.lang.Long value) {
        this.id = value;
    }
    
    // -------- Common Methods -----------
    
    /**
     * Set the name property.
     * @param value the new value
     */
    public void setName(final java.lang.String value) {
        this.name = value;
    }
    
    public void setRegionSubnets(final Set < RegionSubnet > regionSubnets) {
        this.regionSubnets = regionSubnets;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rating(=");
        sb.append("name: ");
        sb.append(this.getName());
        sb.append(", code: ");
        sb.append(this.getCode());
        sb.append(", description: ");
        sb.append(this.getDescription());
        sb.append(", id: ");
        sb.append(this.getId());
        sb.append(")");
        return sb.toString();
    }
}