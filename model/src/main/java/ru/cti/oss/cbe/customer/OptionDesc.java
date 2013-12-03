// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.customer;

import org.hibernate.annotations.Index;

/**
 * Autogenerated POJO EJB class for OptionDesc containing the
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
@javax.persistence.Table(name = "OPTION_DESC")
@javax.persistence.NamedQueries( {
        @javax.persistence.NamedQuery(name = "OptionDesc.findByKey", query = "from OptionDesc where key = :key"),
        @javax.persistence.NamedQuery(name = "OptionDesc.findAll", query = "select optionDesc from OptionDesc as optionDesc") })
public class OptionDesc implements java.io.Serializable, Comparable < OptionDesc >
{
    
    private static final long serialVersionUID = 5255104014592768759L;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.String name;
    private java.lang.String key;
    private java.lang.Long id;
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public OptionDesc() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param key Value for the key property
     */
    public OptionDesc(java.lang.String name, java.lang.String key) {
        setName(name);
        setKey(key);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the name property.
     * 
     * @return java.lang.String The value of name
     */
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    @Index(name = "name_index")
    public java.lang.String getName() {
        return name;
    }
    
    /**
     * Set the name property.
     * @param value the new value
     */
    public void setName(java.lang.String value) {
        this.name = value;
    }
    
    /**
     * Get the key property.
     * 
     * @return java.lang.String The value of key
     */
    @javax.persistence.Column(name = "KEY_VALUE", nullable = false, insertable = true, updatable = true)
    @Index(name = "key_index")
    public java.lang.String getKey() {
        return key;
    }
    
    /**
     * Set the key property.
     * @param value the new value
     */
    public void setKey(java.lang.String value) {
        this.key = value;
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
        return id;
    }
    
    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(java.lang.Long value) {
        this.id = value;
    }
    
    // ------------- Relations ------------------
    
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
        if (!(object instanceof OptionDesc)) {
            return false;
        }
        final OptionDesc that = (OptionDesc) object;
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
        int hashCode = 0;
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());
        
        return hashCode;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OptionDesc(=");
        sb.append("name: ");
        sb.append(getName());
        sb.append(", key: ");
        sb.append(getKey());
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(OptionDesc o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getName() != null) {
                cmp = (cmp != 0 ? cmp : this.getName().compareTo(o.getName()));
            }
            if (this.getKey() != null) {
                cmp = (cmp != 0 ? cmp : this.getKey().compareTo(o.getKey()));
            }
        }
        return cmp;
    }
}