// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.cbe.party;

import org.hibernate.annotations.Index;

/**
 * Autogenerated POJO EJB class for Individual containing the
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
@javax.persistence.Table(name = "INDIVIDUAL")
@javax.persistence.NamedQueries({
	@javax.persistence.NamedQuery(name = "Individual.findAll", query = "select individual from Individual AS individual"),
	@javax.persistence.NamedQuery(name = "Individual.findByCustomer", query = "select i.externalId from Individual i where i.id in (select c.party.id from Customer c where c.name = :name)")	
})
public class Individual extends ru.cti.oss.cbe.party.Party implements java.io.Serializable
{
    
    private static final long serialVersionUID = 3061275265728328434L;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.String firstName;
    private java.lang.String middleName;
    private java.lang.String lastName;
    private java.lang.String formOfAddress;
    private java.util.Date birthDate;
    private ru.cti.oss.cbe.party.Gender gender;
    private ru.cti.oss.cbe.party.MaritalStatus maritalStatus;
    private java.lang.String company;
    private java.lang.String occupation;
    private java.lang.String email;
    private java.lang.String phone;
    private java.lang.String mobilePhone;
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public Individual() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param externalId Value for the externalId property
     * @param firstName Value for the firstName property
     * @param middleName Value for the middleName property
     * @param lastName Value for the lastName property
     * @param formOfAddress Value for the formOfAddress property
     * @param birthDate Value for the birthDate property
     * @param gender Value for the gender property
     * @param maritalStatus Value for the maritalStatus property
     * @param company Value for the company property
     * @param occupation Value for the occupation property
     * @param email Value for the email property
     * @param phone Value for the phone property
     * @param mobilePhone Value for the mobilePhone property
     */
    public Individual(java.lang.String name, java.lang.String externalId, java.lang.String firstName, java.lang.String middleName,
            java.lang.String lastName, java.lang.String formOfAddress, java.util.Date birthDate, ru.cti.oss.cbe.party.Gender gender,
            ru.cti.oss.cbe.party.MaritalStatus maritalStatus, java.lang.String company, java.lang.String occupation,
            java.lang.String email, java.lang.String phone, java.lang.String mobilePhone) {
        super(name, externalId);
        setName(name);
        setExternalId(externalId);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setFormOfAddress(formOfAddress);
        setBirthDate(birthDate);
        setGender(gender);
        setMaritalStatus(maritalStatus);
        setCompany(company);
        setOccupation(occupation);
        setEmail(email);
        setPhone(phone);
        setMobilePhone(mobilePhone);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the firstName property.
     * 
     * @return java.lang.String The value of firstName
     */
    @javax.persistence.Column(name = "FIRST_NAME", nullable = false, insertable = true, updatable = true)
    @Index(name = "first_name_index")
    public java.lang.String getFirstName() {
        return firstName;
    }
    
    /**
     * Set the firstName property.
     * @param value the new value
     */
    public void setFirstName(java.lang.String value) {
        this.firstName = value;
    }
    
    /**
     * Get the middleName property.
     * 
     * @return java.lang.String The value of middleName
     */
    @javax.persistence.Column(name = "MIDDLE_NAME", insertable = true, updatable = true)
    @Index(name = "middle_name_index")
    public java.lang.String getMiddleName() {
        return middleName;
    }
    
    /**
     * Set the middleName property.
     * @param value the new value
     */
    public void setMiddleName(java.lang.String value) {
        this.middleName = value;
    }
    
    /**
     * Get the lastName property.
     * 
     * @return java.lang.String The value of lastName
     */
    @javax.persistence.Column(name = "LAST_NAME", nullable = false, insertable = true, updatable = true)
    @Index(name = "last_name_index")
    public java.lang.String getLastName() {
        return lastName;
    }
    
    /**
     * Set the lastName property.
     * @param value the new value
     */
    public void setLastName(java.lang.String value) {
        this.lastName = value;
    }
    
    /**
     * Get the formOfAddress property.
     * 
     * @return java.lang.String The value of formOfAddress
     */
    @javax.persistence.Column(name = "FORM_OF_ADDRESS", insertable = true, updatable = true)
    public java.lang.String getFormOfAddress() {
        return formOfAddress;
    }
    
    /**
     * Set the formOfAddress property.
     * @param value the new value
     */
    public void setFormOfAddress(java.lang.String value) {
        this.formOfAddress = value;
    }
    
    /**
     * Get the birthDate property.
     * 
     * @return java.util.Date The value of birthDate
     */
    @javax.persistence.Column(name = "BIRTH_DATE", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public java.util.Date getBirthDate() {
        return birthDate;
    }
    
    /**
     * Set the birthDate property.
     * @param value the new value
     */
    public void setBirthDate(java.util.Date value) {
        this.birthDate = value;
    }
    
    /**
     * Get the gender property.
     * 
     * @return ru.cti.oss.cbe.party.Gender The value of gender
     */
    @javax.persistence.Column(name = "GENDER", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    @Index(name = "gender_index")
    public ru.cti.oss.cbe.party.Gender getGender() {
        return gender;
    }
    
    /**
     * Set the gender property.
     * @param value the new value
     */
    public void setGender(ru.cti.oss.cbe.party.Gender value) {
        this.gender = value;
    }
    
    /**
     * Get the maritalStatus property.
     * 
     * @return ru.cti.oss.cbe.party.MaritalStatus The value of maritalStatus
     */
    @javax.persistence.Column(name = "MARITAL_STATUS", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ru.cti.oss.cbe.party.MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }
    
    /**
     * Set the maritalStatus property.
     * @param value the new value
     */
    public void setMaritalStatus(ru.cti.oss.cbe.party.MaritalStatus value) {
        this.maritalStatus = value;
    }
    
    /**
     * Get the company property.
     * 
     * @return java.lang.String The value of company
     */
    @javax.persistence.Column(name = "COMPANY", insertable = true, updatable = true)
    @Index(name = "company_index")
    public java.lang.String getCompany() {
        return company;
    }
    
    /**
     * Set the company property.
     * @param value the new value
     */
    public void setCompany(java.lang.String value) {
        this.company = value;
    }
    
    /**
     * Get the occupation property.
     * 
     * @return java.lang.String The value of occupation
     */
    @javax.persistence.Column(name = "OCCUPATION", insertable = true, updatable = true)
    @Index(name = "occupation_index")
    public java.lang.String getOccupation() {
        return occupation;
    }
    
    /**
     * Set the occupation property.
     * @param value the new value
     */
    public void setOccupation(java.lang.String value) {
        this.occupation = value;
    }
    
    /**
     * Get the email property.
     * 
     * @return java.lang.String The value of email
     */
    @javax.persistence.Column(name = "EMAIL", insertable = true, updatable = true)
    @Index(name = "email_index")
    public java.lang.String getEmail() {
        return email;
    }
    
    /**
     * Set the email property.
     * @param value the new value
     */
    public void setEmail(java.lang.String value) {
        this.email = value;
    }
    
    /**
     * Get the phone property.
     * 
     * @return java.lang.String The value of phone
     */
    @javax.persistence.Column(name = "PHONE", insertable = true, updatable = true)
    @Index(name = "phone_index")
    public java.lang.String getPhone() {
        return phone;
    }
    
    /**
     * Set the phone property.
     * @param value the new value
     */
    public void setPhone(java.lang.String value) {
        this.phone = value;
    }
    
    /**
     * Get the mobilePhone property.
     * 
     * @return java.lang.String The value of mobilePhone
     */
    @javax.persistence.Column(name = "MOBILE_PHONE", insertable = true, updatable = true)
    @Index(name = "mobile_phone_index")
    public java.lang.String getMobilePhone() {
        return mobilePhone;
    }
    
    /**
     * Set the mobilePhone property.
     * @param value the new value
     */
    public void setMobilePhone(java.lang.String value) {
        this.mobilePhone = value;
    }
    
    // ------------- Relations ------------------
    
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
        if (!(object instanceof Individual)) {
            return false;
        }
        final Individual that = (Individual) object;
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
        int hashCode = super.hashCode();
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
        sb.append("Individual(=");
        sb.append(super.toString());
        sb.append("firstName: ");
        sb.append(getFirstName());
        sb.append(", middleName: ");
        sb.append(getMiddleName());
        sb.append(", lastName: ");
        sb.append(getLastName());
        sb.append(", formOfAddress: ");
        sb.append(getFormOfAddress());
        sb.append(", birthDate: ");
        sb.append(getBirthDate());
        sb.append(", gender: ");
        sb.append(getGender());
        sb.append(", maritalStatus: ");
        sb.append(getMaritalStatus());
        sb.append(", company: ");
        sb.append(getCompany());
        sb.append(", occupation: ");
        sb.append(getOccupation());
        sb.append(", email: ");
        sb.append(getEmail());
        sb.append(", phone: ");
        sb.append(getPhone());
        sb.append(", mobilePhone: ");
        sb.append(getMobilePhone());
        sb.append(")");
        return sb.toString();
    }
    
}