package ru.cti.oss.cbe.party;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.Index;

import ru.cti.oss.iptv.resource.physical.SubnetSimple;

/**
 * Class is describe Suboperator entity
 * 
 * 
 * @author Alexander Babin (mailto:a.babin@cti.ru)
 * @since 19.03.2012
 * @version 1.1 (last modifying at 20.03.2012 by Alexander Babin)
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SUBOPERATOR")
@javax.persistence.NamedQueries({
	@javax.persistence.NamedQuery(name = "Suboperator.findAll", query = "SELECT sub FROM Suboperator sub"),
    @javax.persistence.NamedQuery(name = "Suboperator.findByLogin", query = "SELECT s FROM Suboperator s WHERE login=:login"),
    
    @javax.persistence.NamedQuery(name = "Suboperator.findByPartyId", 
    	query = "SELECT s FROM Suboperator s " +
    			"INNER JOIN s.clients party " + 
    			"WHERE party.id=:id"),
    
    @javax.persistence.NamedQuery(name = "Suboperator.findByAccountNumber", 
    	query = "SELECT s FROM Suboperator s " + 
    			"INNER JOIN s.clients party " + 
    			"INNER JOIN party.partyRoles customer " + 
    			"WHERE customer.name=:accountNumber"),
    			
    @javax.persistence.NamedQuery(name = "Suboperator.findSubnets", 
       	query = "SELECT subnet from Suboperator s " +
       			"INNER JOIN s.subnets subnet " +
       			"WHERE s.id=:id")
    
    
	
	
    
})
public class Suboperator implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;	
	private String login;
	private String password;
	
	private java.lang.Long id;
	
	
	private Set <SubnetSimple> subnets = new HashSet<SubnetSimple>();
	private Set <Party> clients = new HashSet<Party>();
	
	
	
    // --------------- Constructors -----------------
	    
    
    @javax.persistence.OneToMany
    @JoinTable(name = "SUBOPERATOR2SUBNET",
    		joinColumns = {@JoinColumn(name = "SUBOPERATOR_ID")},
    		inverseJoinColumns = {@JoinColumn(name = "SUBNET_ID")}
    )
	public Set <SubnetSimple> getSubnets() {
		return subnets;
	}

	public void setSubnets(Set <SubnetSimple> subnets) {
		this.subnets = subnets;
	}

	@javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@javax.persistence.Column(name = "LOGIN", nullable = false, insertable = true, updatable = true , unique=true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@javax.persistence.Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@javax.persistence.OneToMany
    @JoinTable(name = "SUBOPERATOR2PARTY",
    		joinColumns = {@JoinColumn(name = "SUBOPERATOR_ID")},
    		inverseJoinColumns = {@JoinColumn(name = "PARTY_ID")}
    )
	public Set <Party> getClients() {
		return clients;
	}

	public void setClients(Set <Party> clients) {
		this.clients = clients;
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
        if (!(object instanceof Suboperator))
        {
            return false;
        }
        final Suboperator that = (Suboperator)object;
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
        int hashCode = super.hashCode();
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
        sb.append(this.getClass().getSimpleName() + "(=");
        sb.append(super.toString());
        sb.append(")");
        return sb.toString();
    }
    
}