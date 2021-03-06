// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.resource;

import javax.persistence.Column;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Autogenerated POJO EJB class for Resource containing the
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
@javax.persistence.Table(name = "RESOURCE_TABLE")
@org.hibernate.annotations.BatchSize(size = 20)
@javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@javax.persistence.NamedQuery(name = "Resource.findAll", query = "select resource from Resource AS resource")
public class Resource implements java.io.Serializable, Comparable < Resource >, IResource
{
    
    private static final long serialVersionUID = 265906204510520252L;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.String name;
    private java.lang.String description;
    private ru.cti.oss.cbe.datatypes.LifeCycleState status;
    private java.util.Map < java.util.Locale, java.lang.String > i18nName;
    private java.util.Map < java.util.Locale, java.lang.String > i18nDescription;
    private java.lang.Long id;
    private java.lang.String externalId;
    
    // --------- Relationship Definitions -----------
    
    private ru.cti.oss.cbe.party.PartyRole owner;
    
    // ---- Manageable Display Attributes (Transient) -----
    
    private java.lang.String ownerLabel; // Manageable display attribute
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public Resource() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     */
    public Resource(final java.lang.String name, final java.lang.String description, final ru.cti.oss.cbe.datatypes.LifeCycleState status) {
        this.setName(name);
        this.setDescription(description);
        this.setStatus(status);
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param owner Value for the owner relation
     */
    public Resource(final java.lang.String name, final java.lang.String description, final ru.cti.oss.cbe.datatypes.LifeCycleState status,
            final ru.cti.oss.cbe.party.PartyRole owner) {
        this.setName(name);
        this.setDescription(description);
        this.setStatus(status);
        
        this.setOwner(owner);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    @Override
    public int compareTo(final Resource o) {
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
        if (!(object instanceof Resource)) {
            return false;
        }
        final Resource that = (Resource) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    /**
     * Get the description property.
     * 
     * @return java.lang.String The value of description
     */
    @javax.persistence.Column(name = "DESCRIPTION", insertable = true, updatable = true, length = 3800)
    public java.lang.String getDescription() {
        return this.description;
    }
    
    /**
     * Get the externalId property.
     * 
     * @return java.lang.String The value of externalId
     */
    @javax.persistence.Column(name = "EXTERNAL_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public java.lang.String getExternalId() {
        return this.externalId;
    }
    
    @org.hibernate.annotations.CollectionOfElements(fetch = javax.persistence.FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Column(name = "element", length = 3800, nullable = false)
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "resource_i18n_description", joinColumns = { @javax.persistence.JoinColumn(name = "resource_id", nullable = false) })
    @org.hibernate.annotations.MapKey(columns = @Column(name = "mapkey", nullable = false))
    public java.util.Map < java.util.Locale, java.lang.String > getI18nDescription() {
        return this.i18nDescription;
    }
    
    @org.hibernate.annotations.CollectionOfElements(fetch = javax.persistence.FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "resource_i18n_name", joinColumns = { @javax.persistence.JoinColumn(name = "resource_id", nullable = false) })
    @org.hibernate.annotations.MapKey(columns = @Column(name = "mapkey", nullable = false))
    @Column(name = "element")
    public java.util.Map < java.util.Locale, java.lang.String > getI18nName() {
        return this.i18nName;
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
    
    /**
     * Get the owner
     *
     * @return ru.cti.oss.cbe.party.PartyRole
     */
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinColumn(name = "OWNER")
    public ru.cti.oss.cbe.party.PartyRole getOwner() {
        return this.owner;
    }
    
    /**
     * Get the ownerLabel
     *
     * @return java.lang.String
     */
    @javax.persistence.Transient
    public java.lang.String getOwnerLabel() {
        return this.ownerLabel;
    }
    
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
    
    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = (29 * hashCode) + (this.getId() == null ? 0 : this.getId().hashCode());
        
        return hashCode;
    }
    
    // ------------- Relations ------------------
    
    /**
     * Set the description property.
     * @param value the new value
     */
    public void setDescription(final java.lang.String value) {
        this.description = value;
    }
    
    /**
     * Set the externalId property.
     * @param value the new value
     */
    public void setExternalId(final java.lang.String value) {
        this.externalId = value;
    }
    
    // -------- Manageable Attribute Display -----------
    
    public void setI18nDescription(final java.util.Map < java.util.Locale, java.lang.String > i18nDescription) {
        this.i18nDescription = i18nDescription;
    }
    
    public void setI18nName(final java.util.Map < java.util.Locale, java.lang.String > i18nName) {
        this.i18nName = i18nName;
    }
    
    // -------- Common Methods -----------
    
    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(final java.lang.Long value) {
        this.id = value;
    }
    
    /**
     * Set the name property.
     * @param value the new value
     */
    public void setName(final java.lang.String value) {
        this.name = value;
    }
    
    /**
     * Set the owner
     *
     * @param owner
     */
    public void setOwner(final ru.cti.oss.cbe.party.PartyRole owner) {
        this.owner = owner;
    }
    
    /**
     * Set the ownerLabel
     *
     * @param ownerLabel
     */
    public void setOwnerLabel(final java.lang.String ownerLabel) {
        this.ownerLabel = ownerLabel;
    }
    
    /**
     * Set the status property.
     * @param value the new value
     */
    public void setStatus(final ru.cti.oss.cbe.datatypes.LifeCycleState value) {
        this.status = value;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resource(=");
        sb.append("name: ");
        sb.append(this.getName());
        sb.append(", description: ");
        sb.append(this.getDescription());
        sb.append(", status: ");
        sb.append(this.getStatus());
        sb.append(", id: ");
        sb.append(this.getId());
        sb.append(")");
        return sb.toString();
    }
    
}