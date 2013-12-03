package ru.cti.oss.iptv.resource.logical;

public enum PictureOrientationProportionType implements java.io.Serializable
{
    /**
     *  
     */
    PORTRAIT,
    /**
     *  
     */
    LANDSCAPE_4_3,
    /**
     *  
     */
    LANDSCAPE_16_9;

    private PictureOrientationProportionType()
    {
    }

    /**
     * Return the PictureOrientationProportionType from a string value
     * @return PictureOrientationProportionType enum object
     */
    public static PictureOrientationProportionType fromString(java.lang.String value)
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