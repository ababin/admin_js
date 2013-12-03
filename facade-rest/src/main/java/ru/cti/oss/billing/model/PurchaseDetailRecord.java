package ru.cti.oss.billing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "detail_record")
@javax.persistence.NamedQueries( {
	@javax.persistence.NamedQuery(name = "PurchaseDetailRecord.findForCdr", 
		query = "SELECT rec FROM PurchaseDetailRecord rec " +
				"WHERE rec.processed IS NULL OR rec.processed = FALSE"),    
	
	@javax.persistence.NamedQuery(name = "PurchaseDetailRecord.countForCdr", 
		query = "SELECT COUNT(rec) FROM PurchaseDetailRecord rec " +
				"WHERE rec.processed IS NULL OR rec.processed = FALSE"),
	
	@javax.persistence.NamedQuery(name = "PurchaseDetailRecord.findRecords", 
		query = "SELECT pd FROM PurchaseDetailRecord pd " +
				"WHERE pd.accountNumber=:accNumber " +
				"  AND pd.purchaseDate>= :beginDate " +
				"  AND pd.purchaseDate<= :endDate " +
				"  AND pd.serviceType=:type " +
				"ORDER BY pd.purchaseDate DESC")
		})

public class PurchaseDetailRecord {
	
	private Long id;

	private String accountNumber;

	private Double price;

	private Date purchaseDate;

	private String comment;

	private Long resourceId;
			
	private String contentType;
	
	private Boolean isPeriodical;
	
	private Boolean isPackage;
		
	private Long offerId;

	private String serviceId;

	private String serviceType;

	private PurchaseRecordType recordType;
	
	private Boolean processed;
	
	private Date startDate;
	
	private Date endDate;
	
	private String customerName;
	
	private String specExtId;
	

	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "description")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	@javax.persistence.Column(name = "RECORD_TYPE", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	public PurchaseRecordType getRecordType() {
		return recordType;
	}
	
	public void setRecordType(PurchaseRecordType recordType) {
		this.recordType = recordType;
	}
	
	@Override
	public boolean equals(Object value) {
		if (!(value instanceof PurchaseDetailRecord)) {
			return false;
		}
		PurchaseDetailRecord obj = (PurchaseDetailRecord)value;
		if (!obj.getId().equals(getId())) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = 37;		
		result = 29*result + ((accountNumber != null && !accountNumber.equals("")) ? accountNumber.hashCode() : 0);		
		result = 29*result + (price != null ? price.hashCode() : 0);
		result = 29*result + ((purchaseDate != null && !purchaseDate.equals("")) ? purchaseDate.hashCode() : 0);
		result = 29*result + ((comment != null && !comment.equals("")) ? comment.hashCode() : 0);
		result = 29*result + (resourceId != null ? resourceId.hashCode() : 0);
		result = 29*result + (offerId != null ? offerId.hashCode() : 0);
		result = 29*result + (serviceId != null ? serviceId.hashCode() : 0);
		result = 29*result + ((serviceType != null && !serviceType.equals("")) ? serviceType.hashCode() : 0);
		result = 29*result + ((processed != null && !processed.equals("")) ? processed.hashCode() : 0);
		result = 29*result + ((recordType != null && !recordType.equals("")) ? recordType.hashCode() : 0);
		return result;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setIsPeriodical(Boolean isPeriodical) {
		this.isPeriodical = isPeriodical;
	}

	public Boolean getIsPeriodical() {
		return isPeriodical;
	}

	public void setIsPackage(Boolean isPackage) {
		this.isPackage = isPackage;
	}

	public Boolean getIsPackage() {
		return isPackage;
	}

	@javax.persistence.Column(name = "START_DATE", insertable = true, updatable = true)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@javax.persistence.Column(name = "END_DATE", insertable = true, updatable = true)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@javax.persistence.Column(name = "CUSTOMER_NAME", insertable = true, updatable = true)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@javax.persistence.Column(name = "SPEC_EXT_ID", insertable = true, updatable = true)
	public String getSpecExtId() {
		return specExtId;
	}
	
	public void setSpecExtId(String specExtId) {
		this.specExtId = specExtId;
	}
				 
	
}
