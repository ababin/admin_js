package ru.cti.oss.iptv.common;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 
 * Класс предоставляет данные по категориям VOD-контента.
 * 
 * @author a.babin
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "VOD_CONTENT_TYPE")
@javax.persistence.NamedQueries( {
                
        // VodServiceImpl
		@javax.persistence.NamedQuery(name = "VodContentType.findForVod", 
        		query = "SELECT ct FROM VodContentType ct " +
        				"WHERE ct.id IN (SELECT ct.id FROM VodServiceSpec vss " +
        				"                 INNER JOIN vss.contentType ct " +
        				"                WHERE vss.status='ACTIVE' " +
        				"                  AND vss.periodical = FALSE " +
        				"                  AND vss.isPackage = FALSE " +
        				"				   AND vss.externalId IN (:vssIds) " +
        				"               ) " +
        				"ORDER BY COALESCE(ct.weightRating , 0) DESC  , ct.name ASC"),
        				
		// VodServiceImpl
		@javax.persistence.NamedQuery(name = "VodContentType.findForVodBasic", 
        		query = "SELECT ct FROM VodContentType ct " +
        				"WHERE ct.id IN (SELECT ct.id FROM VodServiceSpec vss " +
        				"                 INNER JOIN vss.contentType ct " +
        				"                WHERE vss.status='ACTIVE' " +
        				"                  AND vss.periodical = FALSE " +
        				"                  AND vss.isPackage = FALSE " +
        				"				   AND vss.externalId IN (:vssIds) " +
        				"				) " +
        				" AND ct.name LIKE '%basic%' " +
        				"ORDER BY COALESCE(ct.weightRating , 0) DESC  , ct.name ASC"),
        
        // PvodServiceImpl
        @javax.persistence.NamedQuery(name = "VodContentType.findForPvod", 
        		query = "SELECT ct FROM VodContentType ct " +
        				"WHERE ct.id IN (SELECT ct.id FROM VodServiceSpec vss " +
        				"                 INNER JOIN vss.contentType ct " +
        				"                WHERE vss.status='ACTIVE' " +
        				"                  AND vss.isPackage = TRUE " +
        				"				   AND vss.externalId IN (:vssIds) " +
        				"				) " +
        				"ORDER BY COALESCE(ct.weightRating , 0) DESC  , ct.name ASC"),
        				
		// PvodServiceImpl
        @javax.persistence.NamedQuery(name = "VodContentType.findForPvodBasic", 
        		query = "SELECT ct FROM VodContentType ct " +
        				"WHERE ct.id IN (SELECT ct.id FROM VodServiceSpec vss " +
        				"                 INNER JOIN vss.contentType ct " +
        				"                WHERE vss.status='ACTIVE' " +
        				"                  AND vss.isPackage = TRUE " +
        				"				   AND vss.externalId IN (:vssIds) " +
        				"				) " +
        				" AND ct.name LIKE '%basic%' " +
        				"ORDER BY COALESCE(ct.weightRating , 0) DESC  , ct.name ASC"),
        
        // PremiumMovieHome
        @javax.persistence.NamedQuery(name = "VodContentType.findByName", 
        		query = "SELECT ct FROM VodContentType ct " +
        				"WHERE ct.name = :name") 
})
public class VodContentType implements java.io.Serializable, Comparable < VodContentType >
{
    	        
	private static final long serialVersionUID = 6651076156044094268L;
	
	private java.lang.Long id;
	private java.lang.String name;
    private java.lang.String description;
    private java.lang.Integer weightRating = 0;
    
    private java.util.Map < java.util.Locale, java.lang.String > i18nName;
    
    
    
        
    /**
     * constructor
     */
    public VodContentType() {
    }
    
    /**
     * Constructor
     *
     * @param name Value for the name property
     * @param description Value for the description property
     */
    public VodContentType(java.lang.String name, java.lang.String description) {
        setName(name);
        setDescription(description);
    }
    
        
    /**
     * Get the name property.
     * 
     * @return java.lang.String The value of name
     */
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true , unique = true )
    @Index(name = "name_index")
    public java.lang.String getName() {
        return name;
    }
    
    public void setName(java.lang.String value) {
        this.name = value;
    }
    
    /**
     * Get the description property.
     * 
     * @return java.lang.String The value of description
     */
    @javax.persistence.Column(name = "DESCRIPTION", insertable = true, updatable = true)
    public java.lang.String getDescription() {
        return description;
    }
    
    public void setDescription(java.lang.String value) {
        this.description = value;
    }
    
    /**
     * Get the id property.
     * 
     * @return java.lang.Long The value of id
     */
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Index(name = "id_index")
    public java.lang.Long getId() {
        return id;
    }
    
    public void setId(java.lang.Long value) {
        this.id = value;
    }
    
    
    @org.hibernate.annotations.CollectionOfElements(fetch = javax.persistence.FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "vod_content_type_i18n_name", joinColumns = { @javax.persistence.JoinColumn(name = "vod_content_type_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nName() {
        return i18nName;
    }
    
    public void setI18nName(java.util.Map < java.util.Locale, java.lang.String > i18nName) {
        this.i18nName = i18nName;
    }
    
    
    /**
     * Get the weightRating property.
     * 
     * @return java.lang.Integer The value of weightRating
     */
    @javax.persistence.Column(name = "WEIGHT_RATING", nullable = true, insertable = true, updatable = true)
    public java.lang.Integer getWeightRating() {
        return weightRating;
    }
    
    /**
     * Set the weightRating property.
     * @param value the new value
     */
    public void setWeightRating(java.lang.Integer value) {
        this.weightRating = value;
    }
    
    /**
     * Equals method
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CategoryType)) {
            return false;
        }
        final CategoryType that = (CategoryType) object;
        if (this.getId() == null || that.getId() == null || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    public int hashCode() {
        int hashCode = 0;
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());
        
        return hashCode;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VodContentType(=");
        sb.append("name: ");
        sb.append(getName());
        sb.append(", description: ");
        sb.append(getDescription());
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(VodContentType o) {
        int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getName() != null) {
                cmp = (cmp != 0 ? cmp : this.getName().compareTo(o.getName()));
            }
            if (this.getDescription() != null) {
                cmp = (cmp != 0 ? cmp : this.getDescription().compareTo(o.getDescription()));
            }
        }
        return cmp;
    }
}