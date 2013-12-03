// license-header java merge-point
/* Autogenerated by AndroMDA (Enumeration.vsl) - do not edit */
package ru.cti.oss.cbe.party;

/**
 * Autogenerated enumeration MaritalStatus.
 *
 * 
 */
public enum MaritalStatus implements java.io.Serializable
{
    /**
     *  
     */
    MARRIED,

    /**
     *  
     */
    SINGLE,

    /**
     *  
     */
    DIVORCED;

    /**
     * MaritalStatus constructor
     */
    private MaritalStatus()
    {
    }

    /**
     * Return the MaritalStatus from a string value
     * @return MaritalStatus enum object
     */
    public static MaritalStatus fromString(java.lang.String value)
    {
        return valueOf(value);
    }
    
    /**
     * Return a Collection of all literal values for this enumeration
     * @return java.util.Collection literal values
     */
    public static java.util.Collection literals()
    {
        final java.util.Collection<String> literals = new java.util.ArrayList<String>(values().length);
        for (int i = 0; i < values().length; i++)
        {
            literals.add(values()[i].name());
        }
        return literals;
    }
}