// license-header java merge-point
/* Autogenerated by AndroMDA (Enumeration.vsl) - do not edit */
package ru.cti.oss.iptv.resource.logical;

/**
 * Autogenerated enumeration MediaEncodingType.
 *
 * 
 */
public enum MediaEncodingType implements java.io.Serializable
{
	/**
     * 
     */
    MP3,
	
	/**
     *  
     */
    MPEG2,

    /**
     *  
     */
    MPEG4,
        
    /**
     *  
     */
    MPEG4_AC3;
    
    /**
     * MediaEncodingType constructor
     */
    private MediaEncodingType()
    {
    }

    /**
     * Return the MediaEncodingType from a string value
     * @return MediaEncodingType enum object
     */
    public static MediaEncodingType fromString(java.lang.String value)
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