// license-header java merge-point
/* Autogenerated by AndroMDA (Enumeration.vsl) - do not edit */
package ru.cti.oss.iptv.customer;

/**
 * Autogenerated enumeration FavouriteItemType.
 *
 * 
 */
public enum FavouriteItemType implements java.io.Serializable
{
    /**
     *  
     */
    MOVIE;

    /**
     * FavouriteItemType constructor
     */
    private FavouriteItemType()
    {
    }

    /**
     * Return the FavouriteItemType from a string value
     * @return FavouriteItemType enum object
     */
    public static FavouriteItemType fromString(java.lang.String value)
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