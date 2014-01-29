/**
 * 
 */
package ru.cti.oss.cbe.service;

import org.hibernate.annotations.Index;

/**The Class <code>TariffPlanSpecificationRelation</code> is responsible for the matrix of mutually exclusive, and supplements <br/>
 * 
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2009-2011
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *  @since 28.07.2011
 *	@author e.abdrazakov <br/> <b>e-mail</b>: e.abdrazakov@cti.ru
 *	@version 1.0
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "TARIFF_PL_SPEC_RELATION")
public class TariffPlanSpecificationRelation implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3519010895351910736L;
	
	private java.lang.Long id;

	private TariffPlanSpecification tariffPlanSpecificationMaster;
	
	private TariffPlanSpecification tariffPlanSpecificationSlave;
	
	private Integer mode;
	
	/**
	 * @return the id
	 */
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
	 * @param id the id to set
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}

	/**
	 * @return the tariffPlanSpecificationMaster
	 */
	@javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
	@javax.persistence.JoinColumn(name = "TRF_PLAN_SPEC_MASTER", nullable = false, insertable = true, updatable = true)
	public TariffPlanSpecification getTariffPlanSpecificationMaster() {
		return tariffPlanSpecificationMaster;
	}

	/**
	 * @param tariffPlanSpecificationMaster the tariffPlanSpecificationMaster to set
	 */
	public void setTariffPlanSpecificationMaster(
			TariffPlanSpecification tariffPlanSpecificationMaster) {
		this.tariffPlanSpecificationMaster = tariffPlanSpecificationMaster;
	}

	/**
	 * @return the tariffPlanSpecificationSlave
	 */
	@javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
	@javax.persistence.JoinColumn(name = "TRF_PLAN_SPEC_SLAVE", nullable = false, insertable = true, updatable = true)
	public TariffPlanSpecification getTariffPlanSpecificationSlave() {
		return tariffPlanSpecificationSlave;
	}

	/**
	 * @param tariffPlanSpecificationSlave the tariffPlanSpecificationSlave to set
	 */
	public void setTariffPlanSpecificationSlave(
			TariffPlanSpecification tariffPlanSpecificationSlave) {
		this.tariffPlanSpecificationSlave = tariffPlanSpecificationSlave;
	}

	/**
	 * @return the mode
	 */
	@javax.persistence.Column(name = "MODE_", nullable = false, insertable = true, updatable = true)
	public Integer getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Integer mode) {
		this.mode = mode;
	}
		
}
