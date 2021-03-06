// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.resource;

import org.hibernate.annotations.Index;

/**
 * Autogenerated POJO EJB class for PhysicalResource containing the
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
@javax.persistence.Table(name = "PHYSICAL_RESOURCE")
@javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@javax.persistence.NamedQuery(name = "PhysicalResource.findAll", query = "select physicalResource from PhysicalResource AS physicalResource")
public class PhysicalResource
    extends ru.cti.oss.cbe.resource.Resource
    implements java.io.Serializable
{

    private static final long serialVersionUID = -7817130196888680528L;

    // ----------- Attribute Definitions ------------

    private ru.cti.oss.cbe.datatypes.AvailabilityStatus availabilityStatus;

    // --------------- Constructors -----------------

    /**
     * Default empty constructor
     */
    public PhysicalResource() {}

    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param availabilityStatus Value for the availabilityStatus property
     */
    public PhysicalResource(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.datatypes.LifeCycleState status, ru.cti.oss.cbe.datatypes.AvailabilityStatus availabilityStatus)
    {
        super(name, description, status);
        setName(name);
        setDescription(description);
        setStatus(status);
        setAvailabilityStatus(availabilityStatus);
    }



    // -------- Attribute Accessors ----------

    /**
     * Get the availabilityStatus property.
     * 
     * @return ru.cti.oss.cbe.datatypes.AvailabilityStatus The value of availabilityStatus
     */
    @javax.persistence.Column(name = "AVAILABILITY_STATUS", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    @Index(name="availability_status_index")
    public ru.cti.oss.cbe.datatypes.AvailabilityStatus getAvailabilityStatus()
    {
        return availabilityStatus;
    }

    /**
     * Set the availabilityStatus property.
     * @param value the new value
     */
    public void setAvailabilityStatus(ru.cti.oss.cbe.datatypes.AvailabilityStatus value)
    {
        this.availabilityStatus = value;
    }


    // ------------- Relations ------------------

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
        if (!(object instanceof PhysicalResource))
        {
            return false;
        }
        final PhysicalResource that = (PhysicalResource)object;
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
        sb.append("PhysicalResource(=");
        sb.append(super.toString());
        sb.append("availabilityStatus: ");
        sb.append(getAvailabilityStatus());
        sb.append(")");
        return sb.toString();
    }

}