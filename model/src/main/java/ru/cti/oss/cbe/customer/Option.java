// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.customer;

import org.hibernate.annotations.Index;

/**
 * Autogenerated POJO EJB class for Option containing the
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
@javax.persistence.Table(name = "OPTION_TABLE")
@javax.persistence.NamedQuery(name = "Option.findAll", query = "select option from Option AS option")
public class Option implements java.io.Serializable, Comparable < Option >
{
    
    private static final long serialVersionUID = 618789326351944357L;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.Boolean enabled;
    private java.lang.String value;
    private java.lang.Long id;
    
    // --------- Relationship Definitions -----------
    
    private ru.cti.oss.cbe.customer.CustomerAccount account;
    private ru.cti.oss.cbe.customer.OptionDesc optionDesc;
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public Option() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param enabled Value for the enabled property
     * @param value Value for the value property
     */
    public Option(java.lang.Boolean enabled, java.lang.String value) {
        setEnabled(enabled);
        setValue(value);
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param enabled Value for the enabled property
     * @param value Value for the value property
     * @param account Value for the account relation
     * @param optionDesc Value for the optionDesc relation
     */
    public Option(java.lang.Boolean enabled, java.lang.String value, ru.cti.oss.cbe.customer.CustomerAccount account,
            ru.cti.oss.cbe.customer.OptionDesc optionDesc) {
        setEnabled(enabled);
        setValue(value);
        
        setAccount(account);
        setOptionDesc(optionDesc);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the enabled property.
     * 
     * @return java.lang.Boolean The value of enabled
     */
    @javax.persistence.Column(name = "ENABLED", nullable = false, insertable = true, updatable = true)
    @Index(name = "enabled_index")
    public java.lang.Boolean getEnabled() {
        return enabled;
    }
    
    /**
     * Set the enabled property.
     * @param value the new value
     */
    public void setEnabled(java.lang.Boolean value) {
        this.enabled = value;
    }
    
    /**
     * Get the value property.
     * 
     * @return java.lang.String The value of value
     */
    @javax.persistence.Column(name = "VALUE", insertable = true, updatable = true)
    @Index(name="value_index")
    public java.lang.String getValue() {
        return value;
    }
    
    /**
     * Set the value property.
     * @param value the new value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }
    
    /**
     * Get the id property.
     * 
     * @return java.lang.Long The value of id
     */
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Index(name="id_index")
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
    
    /**
     * Get the account
     *
     * @return ru.cti.oss.cbe.customer.CustomerAccount
     */
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinColumn(name = "ACCOUNT")
    public ru.cti.oss.cbe.customer.CustomerAccount getAccount() {
        return this.account;
    }
    
    /**
     * Set the account
     *
     * @param account
     */
    public void setAccount(ru.cti.oss.cbe.customer.CustomerAccount account) {
        this.account = account;
    }
    
    /**
     * Get the optionDesc
     *
     * @return ru.cti.oss.cbe.customer.OptionDesc
     */
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinColumn(name = "OPTIONDESC")
    public ru.cti.oss.cbe.customer.OptionDesc getOptionDesc() {
        return this.optionDesc;
    }
    
    /**
     * Set the optionDesc
     *
     * @param optionDesc
     */
    public void setOptionDesc(ru.cti.oss.cbe.customer.OptionDesc optionDesc) {
        this.optionDesc = optionDesc;
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
        if (!(object instanceof Option)) {
            return false;
        }
        final Option that = (Option) object;
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
        sb.append("Option(=");
        sb.append("enabled: ");
        sb.append(getEnabled());
        sb.append(", value: ");
        sb.append(getValue());
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(Option o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getEnabled() != null) {
                cmp = (cmp != 0 ? cmp : this.getEnabled().compareTo(o.getEnabled()));
            }
            if (this.getValue() != null) {
                cmp = (cmp != 0 ? cmp : this.getValue().compareTo(o.getValue()));
            }
        }
        return cmp;
    }
}