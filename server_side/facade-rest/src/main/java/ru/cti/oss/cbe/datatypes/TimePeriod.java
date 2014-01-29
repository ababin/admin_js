// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EmbeddedValue.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.datatypes;

/**
 * 
 *
 * Migrated from using @javax.persistence.Embeddable annotation to mapping in orm.xml
 * Still use the attribute annotations
 */
public class TimePeriod
    implements java.io.Serializable
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -708617510741626676L;

    /**
     * Creates a new instance of {@link TimePeriod}
     * taking all properties.
     */
    public static TimePeriod newInstance(java.util.Date startTime,java.util.Date endTime)
    {
        TimePeriodImpl object = new TimePeriodImpl();
        object.setStartTime(startTime);
        object.setEndTime(endTime);
        object.initialize();
        return object;
    }

    /**
     * Creates a new instance from other TimePeriod instance.
     */
    public static TimePeriod newInstance(TimePeriod otherObject)
    {
        if (otherObject != null)
        {
            return newInstance(otherObject.getStartTime(),otherObject.getEndTime());
        }
        return null;
    }

    public TimePeriod()
    {
    }

    /**
     * Hook for initializing the object in the subclass
     */
    protected void initialize()
    {
    }

    private java.util.Date startTime;

    /**
     * 
     */
    @javax.persistence.Column(name = "START_TIME", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public java.util.Date getStartTime()
    {
        return this.startTime;
    }

    public void setStartTime(java.util.Date startTime)
    {
        this.startTime = startTime;
    }

    private java.util.Date endTime;

    /**
     * 
     */
    @javax.persistence.Column(name = "END_TIME", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public java.util.Date getEndTime()
    {
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime)
    {
        this.endTime = endTime;
    }

    /**
     * Indicates if the argument is of the same type and all values are equal.
     */
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
        if (!(object instanceof TimePeriod))
        {
            return false;
        }
        final TimePeriod that = (TimePeriod)object;
        if (this.getStartTime() == null || that.getStartTime() == null || !this.getStartTime().equals(that.getStartTime()))
        {
            return false;
        }
        if (this.getEndTime() == null || that.getEndTime() == null || !this.getEndTime().equals(that.getEndTime()))
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
        hashCode = 29 * hashCode + (getStartTime() == null ? 0 : getStartTime().hashCode());
        hashCode = 29 * hashCode + (getEndTime() == null ? 0 : getEndTime().hashCode());

        return hashCode;
    }

    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("TimePeriod(=");
        sb.append("startTime: ");
        sb.append(getStartTime());
        sb.append(", endTime: ");
        sb.append(getEndTime());
        sb.append(")");
        return sb.toString();
    }
}