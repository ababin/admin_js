package ru.cti.oss.billing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_resource")
public class ServiceResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String description;

	private Long externalId;

	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 3800)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "external_id",nullable=false)
	public Long getExternalId() {
		return externalId;
	}

	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}

	@Override
	public boolean equals(Object value) {
		if (!(value instanceof ServiceResource)) {
			return false;
		}
		ServiceResource obj = (ServiceResource)value;
		if (!obj.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 37;		
		result = 29*result + ((name != null && !name.equals("")) ? name.hashCode() : 0);
		result = 29*result + ((description != null && !description.equals("")) ? description.hashCode() : 0);
		return result;
	}

}
