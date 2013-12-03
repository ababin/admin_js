// license-header java merge-point
/* Autogenerated by AndroMDA (Enumeration.vsl) - do not edit */
package ru.cti.oss.cbe.datatypes;

/**
 * Autogenerated enumeration OperationalState.
 *
 * 
 */
public enum OperationalState implements java.io.Serializable
{
    /**
     *  
     */
    DISABLED,

    /**
     *  
     */
    ENABLED;

    /**
     * OperationalState constructor
     */
    private OperationalState()
    {
    }

    /**
     * Return the OperationalState from a string value
     * @return OperationalState enum object
     */
    public static OperationalState fromString(java.lang.String value)
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