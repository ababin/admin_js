// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.service;

import javax.persistence.Column;

import org.hibernate.annotations.Index;

import ru.cti.oss.iptv.common.VodContentType;

/**
 * Autogenerated POJO EJB class for ServiceSpecification containing the
 * bulk of the entity implementation.
 *
 * This is autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 *
 * 
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SERVICE_SPECIFICATION")
@org.hibernate.annotations.BatchSize(size = 20)
@javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.JOINED)
@javax.persistence.NamedQueries({
        @javax.persistence.NamedQuery(name = "ServiceSpecification.findAll", query = "select serviceSpecification from ServiceSpecification AS serviceSpecification"),
        @javax.persistence.NamedQuery(name = "ServiceSpecification.findByExternalId", query = "from ServiceSpecification as serviceSpecification where serviceSpecification.externalId = :externalId"),
        @javax.persistence.NamedQuery(name = "ServiceSpecification.findServiceSpecs", query = "select sp from ServiceSpecification sp where sp.externalId in (:specsIds)"),
        @javax.persistence.NamedQuery(name = "ServiceSpecification.findPeriodicalServiceSpecs", query = "select sp from ServiceSpecification sp where sp.externalId in (:specsIds) and sp.periodical = true"),
        @javax.persistence.NamedQuery(name = "ServiceSpecification.findServiceSpec", query = "select sp from ServiceSpecification sp where sp.externalId = :specId"),
        @javax.persistence.NamedQuery(name = "ServiceSpecification.findByName", query = "select ss from ServiceSpecification ss where ss.name = :name"),
        @javax.persistence.NamedQuery(name = "ServiceSpecification.findServiceSpecsCountForSubscribe", 
        		query = "SELECT count(sp) FROM ServiceSpecification sp " +
        				"WHERE " +
        				"  sp.externalId IN (:specsIds) " +
        				"  AND sp.periodical = TRUE "),
        
		// Возвращает список пакетов, на которые пользователь УЖЕ подписан, и которые НЕ позволяют подписаться на текущий 
		// по данным матрицы
		@javax.persistence.NamedQuery(name = "ServiceSpecification.findAlreadyServiceSpecsFor", 
			query = "SELECT ss FROM TariffPlan tp " +
					"INNER JOIN tp.tariffPlanSpecification tps " +
					"INNER JOIN tps.serviceSpecifications ss " +
					"WHERE " +
					"  tp.customer.name = :accountNumber " +
					"  AND ss.status = 'ACTIVE' " +
					"  AND (tps.id IN (" +
					"                 SELECT tpsr.tariffPlanSpecificationMaster.id FROM TariffPlanSpecificationRelation tpsr " +
					"                 WHERE " +
					"                   tpsr.mode = 0 " +
					"                   AND tpsr.tariffPlanSpecificationSlave.externalId = :externalId " +
					"               )" +
					"  OR tps.id IN (" +
					"                 SELECT tpsr.tariffPlanSpecificationSlave.id FROM TariffPlanSpecificationRelation tpsr " +
					"                 WHERE " +
					"                   tpsr.mode = 0 " +
					"                   AND tpsr.tariffPlanSpecificationMaster.externalId = :externalId " +
					"               ))" +
					"  AND tps.id IN ( " +
					"                 SELECT s.tariffPlanSpecification.id FROM Service s " +
					"                 WHERE " +
					"                   s.customer.name = :accountNumber " +
					"                   AND s.timeEnd > :now " +
					"                )"),
			    
		// Возвращает список пакетов, на который абонент ДОЛЖЕН подписаться, прежде чем подписываться на текущий план	
		@javax.persistence.NamedQuery(name = "ServiceSpecification.findNeedServiceSpecsFor", 
			query = "SELECT ss FROM TariffPlan tp " +
					"INNER JOIN tp.tariffPlanSpecification tps " +
					"INNER JOIN tps.serviceSpecifications ss " +
					"WHERE " +
					"  tp.customer.name = :accountNumber " +
					"  AND ss.status = 'ACTIVE' " +
					"  AND tps.id IN (" +
					"                 SELECT tpsr.tariffPlanSpecificationMaster.id FROM TariffPlanSpecificationRelation tpsr " +
					"                 WHERE " +
					"                   tpsr.mode = 1 " +
					"                   AND tpsr.tariffPlanSpecificationSlave.externalId = :externalId " +
					"               )" +
					"  AND tps.id NOT IN ( " +
					"                     SELECT s.tariffPlanSpecification.id FROM Service s " +
					"                     WHERE " +
					"                       s.customer.name = :accountNumber " +
					"                       AND s.timeEnd > :now " +
					"                    )") 

})
public class ServiceSpecification implements java.io.Serializable, Comparable < ServiceSpecification >
{
    
    private static final long serialVersionUID = -2809783424851955803L;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.String name;
    private java.lang.String description;
    private java.util.Map < java.util.Locale, java.lang.String > i18nName;
    private java.util.Map < java.util.Locale, java.lang.String > i18nDescription;
    private ru.cti.oss.cbe.service.ServiceStatus status;
    private java.lang.Long externalId;
    private ru.cti.oss.iptv.Image logo = new ru.cti.oss.iptv.Image();
    private Boolean periodical;
    private Boolean isPackage = false;
    private VodContentType contentType;
    private java.lang.Long id;
    private String code;
    
    // --------- Relationship Definitions -----------
            
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public ServiceSpecification() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     */
    public ServiceSpecification(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo) {
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the name property.
     * 
     * @return java.lang.String The value of name
     */
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true)
    @Index(name = "name_index")
    public java.lang.String getName() {
        return name;
    }
    
    /**
     * Set the name property.
     * @param value the new value
     */
    public void setName(java.lang.String value) {
        this.name = value;
    }
    
    /**
     * Get the description property.
     * 
     * @return java.lang.String The value of description
     */
    @javax.persistence.Column(name = "DESCRIPTION", insertable = true, updatable = true, length = 3800)
    public java.lang.String getDescription() {
        return description;
    }
    
    /**
     * Set the description property.
     * @param value the new value
     */
    public void setDescription(java.lang.String value) {
        this.description = value;
    }
    
    @org.hibernate.annotations.CollectionOfElements
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "service_spec_i18n_name", joinColumns = { @javax.persistence.JoinColumn(name = "service_spec_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nName() {
        return i18nName;
    }
    
    public void setI18nName(java.util.Map < java.util.Locale, java.lang.String > i18nName) {
        this.i18nName = i18nName;
    }
    
    @org.hibernate.annotations.CollectionOfElements
    @Column(name = "element", length = 3800, nullable = false)
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "service_spec_i18n_desc", joinColumns = { @javax.persistence.JoinColumn(name = "service_spec_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nDescription() {
        return i18nDescription;
    }
    
    public void setI18nDescription(java.util.Map < java.util.Locale, java.lang.String > i18nDescription) {
        this.i18nDescription = i18nDescription;
    }
    
    /**
     * Get the status property.
     * 
     * @return ru.cti.oss.cbe.service.ServiceStatus The value of status
     */
    @javax.persistence.Column(name = "STATUS", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ru.cti.oss.cbe.service.ServiceStatus getStatus() {
        return status;
    }
    
    /**
     * Set the status property.
     * @param value the new value
     */
    public void setStatus(ru.cti.oss.cbe.service.ServiceStatus value) {
        this.status = value;
    }
    
    /**
     * Get the externalId property.
     * 
     * @return java.lang.Long The value of externalId
     */
    @javax.persistence.Column(name = "EXTERNAL_ID", unique = true, nullable = false, insertable = true, updatable = true)
    public java.lang.Long getExternalId() {
        return externalId;
    }
    
    /**
     * Set the externalId property.
     * @param value the new value
     */
    public void setExternalId(java.lang.Long value) {
        this.externalId = value;
    }
    
    /**
     * Get the logo property.
     * 
     * @return ru.cti.oss.iptv.Image The value of logo
     */
    @javax.persistence.Embedded
    @javax.persistence.AttributeOverrides({
            @javax.persistence.AttributeOverride(name = "width", column = @javax.persistence.Column(name = "LOGO_WIDTH", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "height", column = @javax.persistence.Column(name = "LOGO_HEIGHT", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "size", column = @javax.persistence.Column(name = "LOGO_SIZE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "contentType", column = @javax.persistence.Column(name = "LOGO_CONTENT_TYPE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "data", column = @javax.persistence.Column(name = "LOGO_DATA", insertable = true, updatable = true)) })
    public ru.cti.oss.iptv.Image getLogo() {
        return logo;
    }
    
    /**
     * Set the logo property.
     * @param value the new value
     */
    public void setLogo(ru.cti.oss.iptv.Image value) {
        this.logo = value;
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
    
    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(java.lang.Long value) {
        this.id = value;
    }
            
    // -------- Common Methods -----------
    
    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ServiceSpecification)) {
            return false;
        }
        final ServiceSpecification that = (ServiceSpecification) object;
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
        sb.append("ServiceSpecification(=");
        sb.append("name: ");
        sb.append(getName());
        sb.append(", description: ");
        sb.append(getDescription());
        sb.append(", status: ");
        sb.append(getStatus());
        sb.append(", externalId: ");
        sb.append(getExternalId());
        sb.append(", logo: ");
        if (getLogo() != null)
            sb.append(getLogo().toString());
        else
            sb.append("null");
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(ServiceSpecification o) {
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
            if (this.getStatus() != null) {
                cmp = (cmp != 0 ? cmp : this.getStatus().compareTo(o.getStatus()));
            }
            if (this.getExternalId() != null) {
                cmp = (cmp != 0 ? cmp : this.getExternalId().compareTo(o.getExternalId()));
            }
        }
        return cmp;
    }
    
	// PERIODICAL
    public void setPeriodical(Boolean periodical) {
        this.periodical = periodical;
    }
    
    @javax.persistence.Column(name = "PERIODICAL", nullable = false, insertable = true, updatable = true)
    public Boolean getPeriodical() {
        return periodical;
    }

	// IS_PACKAGE
	public void setIsPackage(Boolean isPackage) {
		this.isPackage = isPackage;
	}

	@javax.persistence.Column(name = "IS_PACKAGE", nullable = false, insertable = true, updatable = true)
	public Boolean getIsPackage() {
		return isPackage;
	}
	
	// CONTENT_TYPE
	public void setContentType(VodContentType contentType) {
		this.contentType = contentType;
	}

	@javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.EAGER, optional = true)
    @javax.persistence.JoinColumn(name = "CONTENT_TYPE")
	public VodContentType getContentType() {
		return contentType;
	}
	
	/**
	 * This method should be overloaded in child classes and return the count of input specification
	 * @return int
	 */
	public int countTypeSpecs() {
		return 0;
	}

	/**
	 * @return the code
	 */
	@javax.persistence.Column(name = "CODE", insertable = true, updatable = true)
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
}