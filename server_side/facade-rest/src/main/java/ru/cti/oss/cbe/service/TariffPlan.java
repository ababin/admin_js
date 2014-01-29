package ru.cti.oss.cbe.service;

import org.hibernate.annotations.Index;

import ru.cti.oss.cbe.customer.Customer;

@javax.persistence.Entity
@javax.persistence.Table(name = "TARIFF_PL")
@javax.persistence.NamedQueries({
        @javax.persistence.NamedQuery(name = "TariffPlan.findAll", query = "select tariffPlan from TariffPlan tariffPlan"),
        @javax.persistence.NamedQuery(name = "TariffPlan.findByCustomerName", query = "select tariffPlan from TariffPlan tariffPlan where tariffPlan.customer.name = :customerName"),
        @javax.persistence.NamedQuery(name = "TariffPlan.findByCustomer", query = "select tariffPlan from TariffPlan tariffPlan where tariffPlan.customer.id = :customerId"),
        @javax.persistence.NamedQuery(name = "TariffPlan.findByTariffPlanSpecExtId", query = "SELECT tariffPlan FROM TariffPlan tariffPlan WHERE tariffPlan.tariffPlanSpecification.externalId = :externalId"),
        @javax.persistence.NamedQuery(name = "TariffPlan.findByExternalId", query = "SELECT tp FROM TariffPlan tp WHERE tp.externalId = :externalId"),
        @javax.persistence.NamedQuery(name = "TariffPlan.findByCustomerNameAndTariffPlanSpecExtId", 
        	query = "SELECT tariffPlan FROM TariffPlan tariffPlan " +
        			"WHERE " +
        			"  tariffPlan.customer.name = :customerName " +
        			"  AND tariffPlan.tariffPlanSpecification.externalId=:externalId")
})
public class TariffPlan implements java.io.Serializable {

	private static final long serialVersionUID = 2661490729679446508L;

	private java.lang.Long id;

	// ----------- Attribute Definitions ------------

	private java.lang.String name;
	private String externalId;
	private Customer customer;

	// --------- Relationship Definitions -----------

	private TariffPlanSpecification tariffPlanSpecification;

	// -------- Attribute Accessors ----------

	@javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
	@javax.persistence.JoinColumn(name = "TRF_PLAN_SPEC", nullable = false, insertable = true, updatable = true)
	public TariffPlanSpecification getTariffPlanSpecification() {
		return tariffPlanSpecification;
	}

	public void setTariffPlanSpecification(
			TariffPlanSpecification tariffPlanSpecification) {
		this.tariffPlanSpecification = tariffPlanSpecification;
	}

	/**
	 * Get the name property.
	 * 
	 * @return java.lang.String The value of name
	 */
	@javax.persistence.Column(name = "NAME", nullable = true, insertable = true, updatable = true)
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

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	@javax.persistence.Column(name = "EXTERNAL_ID", unique = true, insertable = true, updatable = true)
	public String getExternalId() {
		return externalId;
	}

	// ------------- Relations ------------------

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
	@javax.persistence.JoinColumn(name = "CUSTOMER", nullable = false, insertable = true, updatable = true)
	public Customer getCustomer() {
		return customer;
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
		if (!(object instanceof TariffPlan)) {
			return false;
		}
		final TariffPlan that = (TariffPlan) object;
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
