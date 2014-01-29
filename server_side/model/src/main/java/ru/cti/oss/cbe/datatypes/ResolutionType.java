package ru.cti.oss.cbe.datatypes;

import java.util.Collection;

/**
 *The class <code>ResolutionType</code> is enum for DeviceResolution. <br/>
 * 
 *	<p>
 *  <b> Copyright: </b>Copyright (c) 2009-2011
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *	@author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 *  
 *  <b>Last refactoring: 24.02.2011 </b> <br/>
 *  @since 24.02.2011
 *	@version 1.0.0
 *
*/
public enum ResolutionType implements java.io.Serializable {
    /**
     *   Standard Definition 
     */
    SD,

    /**
     *  High-Definition
     */
    HD,

    /**
     * Full High-Definition
     */
    FULL_HD;
    
    /**
     * ResolutionType constructor
     */
    private ResolutionType() {
    }
    
    /**
     * Return the ResolutionType from a string value
     * @return ResolutionType enum object
     */
    public static ResolutionType fromString(java.lang.String value) {
        return valueOf(value);
    }
    
    /**
     * Return a Collection of all literal values for this enumeration
     * @return java.util.Collection literal values
     */
    public static Collection < String > literals() {
        final java.util.Collection < String > literals = new java.util.ArrayList < String >(values().length);
        for (int i = 0; i < values().length; i++) {
            literals.add(values()[i].name());
        }
        return literals;
    }
}