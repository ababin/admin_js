package ru.cti.oss.cbe.service;

import java.util.Set;

import org.hibernate.annotations.Index;

@javax.persistence.Entity
@javax.persistence.Table(name = "TARIFF_PL_SPEC")
@javax.persistence.NamedQueries({
	@javax.persistence.NamedQuery(name = "TariffPlanSpecification.findByExternalId", query = "from TariffPlanSpecification as tariffPlSpec where tariffPlSpec.externalId = :externalId")
})
public class TariffPlanSpecification {

	private java.lang.Long id;
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.Long externalId;
	private Boolean btvEnabled;
	private Boolean vodEnabled;
	private Boolean servicesEnabled;
	private Boolean settingsEnabled;
	private Set <ServiceSpecification> serviceSpecifications;
	private ru.cti.oss.cbe.service.ServiceStatus status;

	
	
	@javax.persistence.Column(name = "BTV_ENABLED", nullable = true, insertable = true, updatable = true)
	public Boolean getBtvEnabled() {
		return btvEnabled;
	}

	public void setBtvEnabled(Boolean tvEnabled) {
		this.btvEnabled = tvEnabled;
	}
	
	@javax.persistence.Column(name = "VOD_ENABLED", nullable = true, insertable = true, updatable = true)
	public Boolean getVodEnabled() {
		return vodEnabled;
	}

	public void setVodEnabled(Boolean vodEnabled) {
		this.vodEnabled = vodEnabled;
	}

	@javax.persistence.Column(name = "SRV_ENABLED", nullable = true, insertable = true, updatable = true)
	public Boolean getServicesEnabled() {
		return servicesEnabled;
	}

	public void setServicesEnabled(Boolean servicesEnabled) {
		this.servicesEnabled = servicesEnabled;
	}

	@javax.persistence.Column(name = "STNGS_ENABLED", nullable = true, insertable = true, updatable = true)
	public Boolean getSettingsEnabled() {
		return settingsEnabled;
	}

	public void setSettingsEnabled(Boolean settingsEnabled) {
		this.settingsEnabled = settingsEnabled;
	}

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
	 * 
	 * @param value
	 *            the new value
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
	 * 
	 * @param value
	 *            the new value
	 */
	public void setDescription(java.lang.String value) {
		this.description = value;
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
	 * 
	 * @param value
	 *            the new value
	 */
	public void setExternalId(java.lang.Long value) {
		this.externalId = value;
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
	 * 
	 * @param value
	 *            the new value
	 */
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	/**
     * Get the serviceSpecifications Collection
     *
     * @return Set<ServiceSpecification>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "TARIFPLSPECS2SERVICESPECS", joinColumns = { 
    		@javax.persistence.JoinColumn(name = "TARIFPLSPEC_IDC", referencedColumnName = "ID") }, 
    		inverseJoinColumns = { @javax.persistence.JoinColumn(name = "SERVICESPEC_IDC", referencedColumnName = "ID") })
	public Set<ServiceSpecification> getServiceSpecifications() {
		return serviceSpecifications;
	}

	public void setServiceSpecifications(
			Set<ServiceSpecification> serviceSpecifications) {
		this.serviceSpecifications = serviceSpecifications;
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
	
	// -------- Common Methods -----------

	/**
	 * Indicates if the argument is of the same type and all values are equal.
	 * 
	 * @param object
	 *            The target object to compare with
	 * @return boolean True if both objects a 'equal'
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof TariffPlanSpecification)) {
			return false;
		}
		final TariffPlanSpecification that = (TariffPlanSpecification) object;
		if (this.getId() == null || that.getId() == null
				|| !this.getId().equals(that.getId())) {
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
}
