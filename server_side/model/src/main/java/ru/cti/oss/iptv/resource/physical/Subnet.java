// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.iptv.resource.physical;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

/**
 * Autogenerated POJO EJB class for Subnet containing the
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
@javax.persistence.Table(name = "SUBNET")
@javax.persistence.NamedQueries( {
		@javax.persistence.NamedQuery(name = "Subnet.findAll", query = "select subnet from Subnet AS subnet"),
		@javax.persistence.NamedQuery(name = "Subnet.findSubnetOnServers", query = "select subnet from Subnet subnet, VideoServer server join subnet.servers servers where server in servers")

})
public class Subnet
    implements java.io.Serializable, Comparable<Subnet>
{

    private static final long serialVersionUID = 6622152688809592346L;

    // ----------- Attribute Definitions ------------

    private Integer[] ipAddress;
    private Integer[] mask;
    private java.lang.Long id;


    // --------- Relationship Definitions -----------

    private java.util.Set<ru.cti.oss.iptv.resource.physical.Server> servers = new java.util.TreeSet<ru.cti.oss.iptv.resource.physical.Server>();

    // ---- Manageable Display Attributes (Transient) -----


    // --------------- Constructors -----------------

    /**
     * Default empty constructor
     */
    public Subnet() {}

    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param ipAddress Value for the ipAddress property
     * @param mask Value for the mask property
     */
    public Subnet(Integer[] ipAddress, Integer[] mask)
    {
        setIpAddress(ipAddress);
        setMask(mask);
    }

    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param ipAddress Value for the ipAddress property
     * @param mask Value for the mask property
     * @param servers Value for the servers relation
     */
    public Subnet(Integer[] ipAddress, Integer[] mask, java.util.Set<ru.cti.oss.iptv.resource.physical.Server> servers)
    {
        setIpAddress(ipAddress);
        setMask(mask);

        setServers(servers);
    }


    // -------- Attribute Accessors ----------

    /**
     * Get the ipAddress property.
     *
     * @return byte[] The value of ipAddress
     */
    @javax.persistence.Lob
    @javax.persistence.Column(name = "IP_ADDRESS", nullable = false, insertable = true, updatable = true)
    public Integer[] getIpAddress()
    {
        return ipAddress;
    }

    /**
     * Set the ipAddress property.
     * @param value the new value
     */
    public void setIpAddress(Integer[] value)
    {
        this.ipAddress = value;
    }

    /**
     * Get the mask property.
     *
     * @return byte[] The value of mask
     */
    @javax.persistence.Lob
    @javax.persistence.Column(name = "MASK", nullable = false, insertable = true, updatable = true)
    public Integer[] getMask()
    {
        return mask;
    }

    /**
     * Set the mask property.
     * @param value the new value
     */
    public void setMask(Integer[] value)
    {
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
    public java.lang.Long getId()
    {
        return id;
    }

    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(java.lang.Long value)
    {
        this.id = value;
    }


    // ------------- Relations ------------------

    /**
     * Get the servers Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.physical.Server>
     */
    @javax.persistence.ManyToMany
    @JoinTable(
    		name = "subnets2servers",
    		joinColumns = {@JoinColumn(name = "subnet_idc")},
    		inverseJoinColumns = {@JoinColumn(name = "server_idc")}
    )
    public java.util.Set<ru.cti.oss.iptv.resource.physical.Server> getServers()
    {
        return this.servers;
    }

    /**
     * Set the servers
     *
     * @param servers
     */
    public void setServers (java.util.Set<ru.cti.oss.iptv.resource.physical.Server> servers)
    {
        this.servers = servers;
    }

    /**
     * Add to servers
     *
     * @param server
     */

    public void addToServers (ru.cti.oss.iptv.resource.physical.Server server)
    {
        if (server == null ) return;
        this.getServers().add(server);
    }

    /**
     * Remove from servers
     *
     * @param server
     */

    public void removeFromServers (ru.cti.oss.iptv.resource.physical.Server server)
    {
        if (server == null ) return;
        this.getServers().remove(server);
    }

    // -------- Common Methods -----------

    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Subnet))
        {
            return false;
        }
        final Subnet that = (Subnet)object;
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
    public int compareTo(Subnet o)
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