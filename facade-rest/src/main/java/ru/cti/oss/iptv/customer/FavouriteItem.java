// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.iptv.customer;

import org.hibernate.annotations.Index;

/**
 * Autogenerated POJO EJB class for FavouriteItem containing the
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
@javax.persistence.Table(name = "FAVOURITE_ITEM")
@javax.persistence.NamedQueries( {
        @javax.persistence.NamedQuery(name = "FavouriteItem.findAll", query = "select favouriteItem from FavouriteItem AS favouriteItem"),
        @javax.persistence.NamedQuery(name = "VodFavouriteItem.findIds", query = "select fv.itemId from FavouriteItem fv where fv.customerAccount.id = :account and fv.itemId in (select mov.id from Movie mov where mov.status='ACTIVE' and (mov.licenseExpired is null or mov.licenseExpired >= :now) ) and fv.type = 0"),
        @javax.persistence.NamedQuery(name = "FavouriteItem.findByItemAndAccount", query = "select fv from FavouriteItem fv where fv.itemId=:item and fv.customerAccount.id = :account"),
        @javax.persistence.NamedQuery(name = "FavouriteItem.findByAccount", query = "select fv from FavouriteItem fv where fv.customerAccount.id = :account"),
        @javax.persistence.NamedQuery(name = "FavouriteItem.findByAccountAndType", query = "select fv from FavouriteItem fv where fv.customerAccount.id = :account and fv.type = :type")})
public class FavouriteItem implements java.io.Serializable, Comparable < FavouriteItem >
{
    
    private static final long serialVersionUID = -2362013222897391584L;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.String name;
    private long itemId;
    private int type;
    private java.lang.Long id;
    
    // --------- Relationship Definitions -----------
    
    private ru.cti.oss.cbe.customer.CustomerAccount customerAccount;
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public FavouriteItem() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param itemId Value for the itemId property
     * @param type Value for the type property
     */
    public FavouriteItem(java.lang.String name, long itemId, int type) {
        setName(name);
        setItemId(itemId);
        setType(type);
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param itemId Value for the itemId property
     * @param type Value for the type property
     * @param customerAccount Value for the customerAccount relation
     */
    public FavouriteItem(java.lang.String name, long itemId, int type, ru.cti.oss.cbe.customer.CustomerAccount customerAccount) {
        setName(name);
        setItemId(itemId);
        setType(type);
        
        setCustomerAccount(customerAccount);
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
     * Get the itemId property.
     * 
     * @return long The value of itemId
     */
    @javax.persistence.Column(name = "ITEM_ID", nullable = false, insertable = true, updatable = true)
    @Index(name = "item_id_index")
    public long getItemId() {
        return itemId;
    }
    
    /**
     * Set the itemId property.
     * @param value the new value
     */
    public void setItemId(long value) {
        this.itemId = value;
    }
    
    /**
     * Get the type property.
     * 0 - vod
     * 1 - btv
     * 
     * @return int The value of type
     */
    @javax.persistence.Column(name = "TYPE", nullable = false, insertable = true, updatable = true)
    public int getType() {
        return type;
    }
    
    /**
     * Set the type property.
     * @param value the new value
     */
    public void setType(int value) {
        this.type = value;
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
    
    /**
     * Get the customerAccount
     *
     * @return ru.cti.oss.cbe.customer.CustomerAccount
     */
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinColumn(name = "CUSTOMERACCOUNT")
    public ru.cti.oss.cbe.customer.CustomerAccount getCustomerAccount() {
        return this.customerAccount;
    }
    
    /**
     * Set the customerAccount
     *
     * @param customerAccount
     */
    public void setCustomerAccount(ru.cti.oss.cbe.customer.CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
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
        if (!(object instanceof FavouriteItem)) {
            return false;
        }
        final FavouriteItem that = (FavouriteItem) object;
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
        sb.append("FavouriteItem(=");
        sb.append("name: ");
        sb.append(getName());
        sb.append(", itemId: ");
        sb.append(getItemId());
        sb.append(", type: ");
        sb.append(getType());
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(FavouriteItem o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getName() != null) {
                cmp = (cmp != 0 ? cmp : this.getName().compareTo(o.getName()));
            }
        }
        return cmp;
    }
}