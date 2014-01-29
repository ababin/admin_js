package ru.cti.oss.billing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.cti.oss.cbe.service.ServiceSpecification;
import ru.cti.oss.cbe.service.TariffPlanSpecification;


@SuppressWarnings("serial")
@Entity
@Table(name = "offer")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Offer.findByRentalPeriodAndServiceSpec", 
    		query = "SELECT offer FROM Offer offer " +
    				"WHERE " +
    				"  rentalPeriod = :rentalPeriod " +
    				"  AND serviceSpecification.externalId = :externalId"),
    		
	@javax.persistence.NamedQuery(name = "Offer.findByRentalPeriodAndServiceSpecAndTariffPlanSpec", 
			query = "SELECT offer FROM Offer offer " +
					"WHERE " +
					"  rentalPeriod = :rentalPeriod " +
					"  AND serviceSpecification.externalId = :ssExternalId " +
					"  AND tariffPlanSpecification.externalId= :tpsExternalId"),
    				
    @javax.persistence.NamedQuery(name = "Offer.findOffersByServiceSpecExternalId", 
    	    query =	"SELECT off FROM Offer off " +
            		"WHERE " +
            		"   off.serviceSpecification.externalId=:serviceSpecificationId " +
            		"	AND (" +
            		"     	  off.tariffPlanSpecification IS NULL " +
            		"         OR EXISTS (" +
            		"				    	SELECT tp.tariffPlanSpecification.id FROM TariffPlan tp " + 
                    "						WHERE " +
                    "						  tp.customer.name=:accountNumber " +
                    "						  AND tp.tariffPlanSpecification.id = off.tariffPlanSpecification.id " +
            		"					)" +
            		"	   )")
    })
public class Offer implements Serializable {

	private Long id;
	
	private Double price;
	
	private Long rentalPeriod;
	
	private ServiceSpecification serviceSpecification;
	
	private TariffPlanSpecification tariffPlanSpecification;

	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}	
	
	@Column(name = "rental_period", nullable = true)
	public Long getRentalPeriod() {
		return rentalPeriod;
	}

	public void setRentalPeriod(Long rentalPeriod) {
		this.rentalPeriod = rentalPeriod;
	}

	@ManyToOne
	@JoinColumn(name = "service_specification_id", nullable = false)
	public ServiceSpecification getServiceSpecification() {
		return serviceSpecification;
	}

	public void setServiceSpecification(ServiceSpecification serviceSpecification) {
		this.serviceSpecification = serviceSpecification;
	}	
	
	@ManyToOne
	@JoinColumn(name = "tariff_pl_spec_id", nullable = true)
	public TariffPlanSpecification getTariffPlanSpecification() {
		return tariffPlanSpecification;
	}

	public void setTariffPlanSpecification(
			TariffPlanSpecification tariffPlanSpecification) {
		this.tariffPlanSpecification = tariffPlanSpecification;
	}

	@Override
	public boolean equals(Object value) {
		if (!(value instanceof Offer)) {
			return false;
		}
		Offer obj = (Offer)value;
		if (!obj.getId().equals(id)) {
			return false;
		} 
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = 37;		
		result = 29*result + (price != null ? price.hashCode() : 0);
		result = 29*result + (serviceSpecification != null ? serviceSpecification.hashCode() : 0);
		result = 29*result + (tariffPlanSpecification != null ? tariffPlanSpecification.hashCode() : 0);
		return result;
	}	
	
}
