package ru.cti.oss.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Account number lock entity for chat
 * 
 */
@Entity
@Table(name = "chat_lock")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "AccountLock.findByNumber", query = "FROM AccountLock WHERE accountNumber=:accountNumber")
        })
public class AccountLock extends AbstractEntity{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * customerAccountId from iptvmw database
     */
    private String accountNumber;
    
    /**
     * status (Active , Locked)
     */
    private String cause;
            

    @Column(name = "account_number", unique = true, nullable = false, insertable = true, updatable = true)
    public String getAccountNumber() {
		return accountNumber;
	}
    
    public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
    
    @Column(name = "cause")
    public String getCause() {
		return cause;
	}
    
    public void setCause(String cause) {
		this.cause = cause;
	}
    
}
