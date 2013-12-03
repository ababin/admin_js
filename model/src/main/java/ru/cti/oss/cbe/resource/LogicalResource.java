// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.resource;

/**
 * Autogenerated POJO EJB class for LogicalResource containing the
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
@javax.persistence.Table(name = "LOGICAL_RESOURCE")
@org.hibernate.annotations.BatchSize(size=20)
@javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@javax.persistence.NamedQuery(name = "LogicalResource.findAll", query = "select logicalResource from LogicalResource AS logicalResource")
public class LogicalResource
    extends ru.cti.oss.cbe.resource.Resource
    implements java.io.Serializable
{

    private static final long serialVersionUID = 699324930572687993L;
    // --------------- Constructors -----------------

    /**
     * Default empty constructor
     */
    public LogicalResource() {}

    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     */
    public LogicalResource(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.datatypes.LifeCycleState status)
    {
        super(name, description, status);
        setName(name);
        setDescription(description);
        setStatus(status);
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
        if (!(object instanceof LogicalResource))
        {
            return false;
        }
        final LogicalResource that = (LogicalResource)object;
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
        return super.toString();
    }

}