package ru.cti.oss.billing.model;

public enum PurchaseRecordType implements java.io.Serializable
{
    /**
     *  
     */
    PURCHASE,

    /**
     *  
     */
    PROLONGATION;

    /**
     * CustomerAccountStatus constructor
     */
    private PurchaseRecordType(){
    }

    /**
     * Return the CustomerAccountStatus from a string value
     * @return CustomerAccountStatus enum object
     */
    public static PurchaseRecordType fromString(java.lang.String value){
        return valueOf(value);
    }
    
    /**
     * Return a Collection of all literal values for this enumeration
     * @return java.util.Collection literal values
     */
    public static java.util.Collection literals(){
        final java.util.Collection<String> literals = new java.util.ArrayList<String>(values().length);
        for (int i = 0; i < values().length; i++)
        {
            literals.add(values()[i].name());
        }
        return literals;
    }
}
