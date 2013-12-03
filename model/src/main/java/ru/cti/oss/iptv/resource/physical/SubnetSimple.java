package ru.cti.oss.iptv.resource.physical;


/**
 * Class is describe SubnetSimple entity (subnet. (ipAddres and mask properties ONLY))
 * 
 * 
 * @author Alexander Babin (mailto:a.babin@cti.ru)
 * @since 19.03.2012
 * @version 1.0
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SUBNET_SIMPLE")
@javax.persistence.NamedQueries( {
		@javax.persistence.NamedQuery(name = "SubnetSimple.findAll", query = "select subnet from Subnet AS subnet")

})
public class SubnetSimple implements java.io.Serializable, Comparable<SubnetSimple>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	// ----------- Attribute Definitions ------------
    private java.lang.Long id;
    private String ipAddress;
    private String mask;
        
    // --------------- Constructors -----------------
    
    public SubnetSimple(){}
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param ipAddress Value for the ipAddress property
     * @param mask Value for the mask property
     */
    public SubnetSimple(String ipAddress, String mask){
        setIpAddress(ipAddress);
        setMask(mask);
    }
    

    // -------- Attribute Accessors ----------

    /**
     * Get the ipAddress property.
     *
     * @return byte[] The value of ipAddress
     */
    @javax.persistence.Column(name = "IP_ADDRESS", nullable = false, insertable = true, updatable = true)
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Set the ipAddress property.
     * @param value the new value
     */
    public void setIpAddress(String value){
        this.ipAddress = value;
    }

    /**
     * Get the mask property.
     *
     * @return byte[] The value of mask
     */
    @javax.persistence.Column(name = "MASK", nullable = false, insertable = true, updatable = true)
    public String getMask() {
        return mask;
    }

    /**
     * Set the mask property.
     * @param value the new value
     */
    public void setMask(String value){
        this.mask = value;
    }

    /**
     * Get the id property.
     *
     * @return java.lang.Long The value of id
     */
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public java.lang.Long getId(){
        return id;
    }

    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(java.lang.Long value){
        this.id = value;
    }


    // -------- Common Methods -----------

    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(Object object){
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof SubnetSimple))
        {
            return false;
        }
        final SubnetSimple that = (SubnetSimple)object;
        if (this.getId() == null || that.getId() == null || !this.getId().equals(that.getId()))
        {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    public int hashCode()
    {
        int hashCode = 0;
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());

        return hashCode;
    }

    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Subnet(=");
        sb.append("ipAddress: ");
        sb.append(getIpAddress());
        sb.append(", mask: ");
        sb.append(getMask());
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(SubnetSimple o)
    {
        int cmp = 0;
        if (this.getId() != null)
        {
            cmp = this.getId().compareTo(o.getId());
        }
        else
        {
        }
        return cmp;
    }
}