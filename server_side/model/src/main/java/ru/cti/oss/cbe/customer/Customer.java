package ru.cti.oss.cbe.customer;

import javax.persistence.CascadeType;

import org.hibernate.annotations.Index;

@javax.persistence.Entity
@javax.persistence.Table(name = "CUSTOMER")
@javax.persistence.EntityListeners({ ru.cti.oss.cbe.customer.CustomerListener.class })
@javax.persistence.NamedQueries({
        @javax.persistence.NamedQuery(name = "Customer.findAll", query = "select customer from Customer AS customer"),
        @javax.persistence.NamedQuery(name = "Customer.findByName", query = "select customer from Customer as customer where customer.name = :name"),
        @javax.persistence.NamedQuery(name = "Customer.findAttemptsByName", query = "select customer.activationAttempts from Customer as customer where customer.name = :name"),
        @javax.persistence.NamedQuery(name = "Customer.findByIds", query = "select customer from Customer customer where customer.id in (:ids)"),
        @javax.persistence.NamedQuery(name = "Customer.findAllIds", query = "select customer.id from Customer AS customer"),
        @javax.persistence.NamedQuery(name = "Customer.findNamesByIds", query = "select customer.name from Customer customer where customer.id in (:ids)"),
        @javax.persistence.NamedQuery(name = "Customer.findAllIdsByName", query = "select customer.id from Customer AS customer where customer.name = :name"),
        @javax.persistence.NamedQuery(name = "Customer.findByLoginWithoutUpperCase", query = "select c from Customer c where c.login = :login"),
        @javax.persistence.NamedQuery(name = "Customer.findBySetTopBox", query = "select c from Customer c where c.id in (select s.owner from SetTopBox s where s.externalId = :externalId)"),
        @javax.persistence.NamedQuery(name = "Customer.findByIndividual", query = "select c from Customer c where c.id in (select pr.id from PartyRole pr where pr.party in (select p.id from Party p where p.externalId = :externalId))")
})
public class
         Customer extends ru.cti.oss.cbe.party.PartyRole implements java.io.Serializable
{
    
    private static final long serialVersionUID = 7546152601721213456L;
    
    //private java.lang.Integer customerRank;
    private ru.cti.oss.cbe.customer.CustomerStatus customerStatus;
    private Boolean stbActivationEnabled;
    private java.lang.String pin;
    private java.lang.Long activationAttempts;
    private java.lang.Double balance;
    private java.lang.Double creditLimit;
    private java.lang.String customerStatusDescription;
    
    private ru.cti.oss.cbe.customer.CustomerAccount activeAccount;
    private java.util.Set < ru.cti.oss.cbe.customer.CustomerAccount > accounts = new java.util.TreeSet < ru.cti.oss.cbe.customer.CustomerAccount >();
    private java.util.Set < ru.cti.oss.iptv.resource.physical.SetTopBox > stbs = new java.util.TreeSet < ru.cti.oss.iptv.resource.physical.SetTopBox >();
    
    private java.lang.String login;
    private java.lang.String password;
    
    private java.lang.String telephonyId;
    private java.lang.String telephonyPassword;
    
    public Customer() {
    }
    
    public void addToAccounts(final ru.cti.oss.cbe.customer.CustomerAccount customerAccount) {
        if (customerAccount == null) {
            return;
        }
        this.getAccounts().add(customerAccount);
        customerAccount.setCustomer(this);
    }
    
    public void addToStbs(final ru.cti.oss.iptv.resource.physical.SetTopBox setTopBox) {
        if (setTopBox == null) {
            return;
        }
        this.getStbs().add(setTopBox);
        setTopBox.setOwner(this);
    }
    
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Customer)) {
            return false;
        }
        final Customer that = (Customer) object;
        if ((this.getId() == null) || (that.getId() == null) || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    @javax.persistence.OneToMany(mappedBy = "customer", fetch = javax.persistence.FetchType.EAGER, cascade = { CascadeType.MERGE,
            CascadeType.REMOVE, CascadeType.REFRESH })
    //@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public java.util.Set < ru.cti.oss.cbe.customer.CustomerAccount > getAccounts() {
        return this.accounts;
    }
    
    @javax.persistence.Column(name = "ACTIVATION_ATTEMPTS", nullable = false, insertable = true, updatable = true)
    @Index(name = "activation_attempts_index")
    public java.lang.Long getActivationAttempts() {
        return this.activationAttempts;
    }
    
    @javax.persistence.OneToOne(fetch = javax.persistence.FetchType.EAGER)
    @javax.persistence.JoinColumn(name = "ACTIVEACCOUNT")
    public ru.cti.oss.cbe.customer.CustomerAccount getActiveAccount() {
        return this.activeAccount;
    }
    
    @javax.persistence.Column(name = "BALANCE", nullable = false, insertable = true, updatable = true)
    @Index(name = "balance_index")
    public java.lang.Double getBalance() {
        return this.balance;
    }
    
    @javax.persistence.Column(name = "CREDIT_LIMIT", nullable = false, insertable = true, updatable = true)
    @Index(name = "credit_limit_index")
    public java.lang.Double getCreditLimit() {
        return this.creditLimit;
    }
    
    @javax.persistence.Column(name = "CUSTOMER_STATUS", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    @Index(name = "customer_status_index")
    public ru.cti.oss.cbe.customer.CustomerStatus getCustomerStatus() {
        return this.customerStatus;
    }
    
    @javax.persistence.Column(name = "CUSTOMER_STATUS_DESC", insertable = true, updatable = true)
    public java.lang.String getCustomerStatusDescription() {
        return this.customerStatusDescription;
    }
    
    public java.lang.String getLogin() {
        return this.login;
    }
    
    public java.lang.String getPassword() {
        return this.password;
    }
    
    @javax.persistence.Column(name = "PIN", nullable = true, insertable = true, updatable = true)
    @Index(name = "pin_index")
    public java.lang.String getPin() {
        return this.pin;
    }
    
    @javax.persistence.Column(name = "STB_ACTIV_ENABLED")
    public Boolean getStbActivationEnabled() {
        return this.stbActivationEnabled;
    }
    
    @javax.persistence.OneToMany(mappedBy = "owner", fetch = javax.persistence.FetchType.EAGER, cascade = { CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.REMOVE })
    public java.util.Set < ru.cti.oss.iptv.resource.physical.SetTopBox > getStbs() {
        return this.stbs;
    }
    
    /*
    @javax.persistence.Column(name = "CUSTOMER_RANK", nullable = false, insertable = true, updatable = true)
    @Index(name = "customer_rank_index")
    public java.lang.Integer getCustomerRank() {
        return this.customerRank;
    }
    */

    @javax.persistence.Column(name = "TELEPHONY_ID", nullable = true, insertable = true, updatable = true)
    public java.lang.String getTelephonyId() {
        return this.telephonyId;
    }
    
    
    @javax.persistence.Column(name = "TELEPHONY_PASSWORD", nullable = true, insertable = true, updatable = true)
    public java.lang.String getTelephonyPassword() {
        return this.telephonyPassword;
    }
    
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        hashCode = 29 * hashCode + (this.getId() == null ? 0 : this.getId().hashCode());
        return hashCode;
    }
    
    public void removeFromAccounts(final ru.cti.oss.cbe.customer.CustomerAccount customerAccount) {
        if (customerAccount == null) {
            return;
        }
        this.getAccounts().remove(customerAccount);
        customerAccount.setCustomer(null);
    }
    
    public void removeFromStbs(final ru.cti.oss.iptv.resource.physical.SetTopBox setTopBox) {
        if (setTopBox == null) {
            return;
        }
        this.getStbs().remove(setTopBox);
        setTopBox.setOwner(null);
    }
    
    public void setAccounts(final java.util.Set < ru.cti.oss.cbe.customer.CustomerAccount > accounts) {
        this.accounts = accounts;
    }
    
    public void setActivationAttempts(final java.lang.Long value) {
        this.activationAttempts = value;
    }
    
    public void setActiveAccount(final ru.cti.oss.cbe.customer.CustomerAccount activeAccount) {
        this.activeAccount = activeAccount;
    }
    
    public void setBalance(final java.lang.Double value) {
        this.balance = value;
    }
    
    public void setCreditLimit(final java.lang.Double value) {
        this.creditLimit = value;
    }
    
    public void setCustomerStatus(final ru.cti.oss.cbe.customer.CustomerStatus value) {
        this.customerStatus = value;
    }
    
    public void setCustomerStatusDescription(final java.lang.String customerStatusDescription) {
        this.customerStatusDescription = customerStatusDescription;
    }
    
    public void setLogin(final java.lang.String login) {
        this.login = login;
    }
    
    /*
    public void setCustomerRank(java.lang.Integer value) {
        this.customerRank = value;
    }
    */

    public void setPassword(final java.lang.String password) {
        this.password = password;
    }
    
    public void setPin(final java.lang.String value) {
        this.pin = value;
    }
    
    public void setStbActivationEnabled(final Boolean stbActivationEnabled) {
        this.stbActivationEnabled = stbActivationEnabled;
    }
    
    public void setStbs(final java.util.Set < ru.cti.oss.iptv.resource.physical.SetTopBox > stbs) {
        this.stbs = stbs;
    }
    
    public void setTelephonyId(final java.lang.String telephonyId) {
        this.telephonyId = telephonyId;
    }
    
    public void setTelephonyPassword(final java.lang.String telephonyPassword) {
        this.telephonyPassword = telephonyPassword;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer(=");
        sb.append(super.toString());
        sb.append(", customerStatus: ");
        sb.append(this.getCustomerStatus());
        sb.append(", pin: ");
        sb.append(this.getPin());
        sb.append(", activationAttempts: ");
        sb.append(this.getActivationAttempts());
        sb.append(", balance: ");
        sb.append(this.getBalance());
        sb.append(", creditLimit: ");
        sb.append(this.getCreditLimit());
        sb.append(")");
        return sb.toString();
    }
    
}