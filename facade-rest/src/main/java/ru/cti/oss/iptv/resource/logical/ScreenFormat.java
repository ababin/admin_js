// license-header java merge-point
/* Autogenerated by AndroMDA (Enumeration.vsl) - do not edit */
package ru.cti.oss.iptv.resource.logical;

/**
 * Autogenerated enumeration ScreenFormat.
 *
 * 
 */
public enum ScreenFormat implements java.io.Serializable
{
    /**
     *  
     */
    WIDESCREEN,

    /**
     *  
     */
    LETTERBOX,

    /**
     *  
     */
    STANDART,

    /**
     *  
     */
    OAR;

    /**
     * ScreenFormat constructor
     */
    private ScreenFormat()
    {
    }

    /**
     * Return the ScreenFormat from a string value
     * @return ScreenFormat enum object
     */
    public static ScreenFormat fromString(java.lang.String value)
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