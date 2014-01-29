package ru.cti.oss.iptv.service.stb;

import javax.persistence.NamedQuery;

/**The Class <code>PcClientServiceSpec</code> present pc client specification
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2010
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *	@author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 *	@version 1.0
 * @since 19.05.2010, TVE: 3.3-SP1.10 & 3.4.3 
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "PC_CLIENT_SERVICE_SPEC")
@javax.persistence.NamedQueries( { @NamedQuery(name = "PcClientServiceSpec.findAll", query = "select pcClientServiceSpec from PcClientServiceSpec AS pcClientServiceSpec") })
public class PcClientServiceSpec extends ru.cti.oss.cbe.service.ServiceSpecification implements java.io.Serializable
{
    
    private static final long serialVersionUID = -6073290531100398516L;
    
    /**
     * Default empty constructor
     */
    public PcClientServiceSpec() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     */
    public PcClientServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo) {
        super(name, description, status, externalId, logo);
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     * @param radioChannels Value for the radioChannels relation
     */
    public PcClientServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo,
            java.util.Set < ru.cti.oss.iptv.resource.logical.RadioChannel > radioChannels) {
        setName(name);
        setDescription(description);
        setStatus(status);
        setLogo(logo);
    }
    
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
        if (!(object instanceof PcClientServiceSpec)) {
            return false;
        }
        final PcClientServiceSpec that = (PcClientServiceSpec) object;
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
        int hashCode = super.hashCode();
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());
        
        return hashCode;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString() {
        return super.toString();
    }
    
}