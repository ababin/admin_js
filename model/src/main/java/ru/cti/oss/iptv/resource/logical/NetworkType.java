package ru.cti.oss.iptv.resource.logical;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public enum NetworkType {
	
	IPTV,
	OTT,
	DVB;
	
	private static Set <String> literals = new HashSet <String> (); 
	
	static{
        for (int i = 0; i < values().length; i++) {
            literals.add(values()[i].name());
        }
	}
	
	/**
     * Return a Collection of all literal values for this enumeration
     * @return java.util.Collection literal values
     */
    public static Set < String > literals() {
        return literals;
    }
    
    public static Set <NetworkType> fromListString(String str){
    	Set<NetworkType> result = new HashSet<NetworkType>();
        if (str != null){
        	String[] networksArray = str.split("\\|");
            for (String networkString : networksArray) {
                if (literals.contains(networkString)) {
                    result.add(NetworkType.valueOf(networkString));
                }
            }
        } 
        return result;
    }
    
    public static String toListString(Collection <NetworkType> networks){
    	String res = "";		
        boolean first = true;
        for (NetworkType networkType : networks) {
            if(first){
            	first = false;
            }else{
            	res += "|";
            }
            res += networkType.name();
        }
        return "|" + res + "|";
    }
	
}
