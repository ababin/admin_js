package ru.cti.oss.iptv.resource.common;

import java.io.Serializable;


public enum BtvProtocolType implements Serializable{
	
	igmp;

    
    /**
     * Return the NpvrRecordStatus from a string value
     * @return NpvrRecordStatus enum object
     */
    public static BtvProtocolType fromString(java.lang.String value){
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
