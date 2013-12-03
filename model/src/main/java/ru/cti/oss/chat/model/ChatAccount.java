package ru.cti.oss.chat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * News entity class
 * 
 */
@Entity
@Table(name = "chat_account")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "ChatAccount.findAll", query = "from ChatAccount order by name"),
        @NamedQuery(name = "ChatAccount.findByCustomerAccountId", query = "from ChatAccount where customerAccountId=:customerAccountId"),
        @NamedQuery(name = "ChatAccount.findCountByCustomerAccountId", query = "select count(*) from ChatAccount where customerAccountId=:customerAccountId"),
        @NamedQuery(name = "ChatAccount.findByName", query = "from ChatAccount where name=:name")
        //@NamedQuery(name = "News.findAllByCategoryListId", query = "from News where categoryId in (:categoryIdList) order by pubDate DESC"),
        //@NamedQuery(name = "News.findCountNewsInCategory", query = "select count(*) from News where categoryId=:categoryId") 
        })
public class ChatAccount extends AbstractEntity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * customerAccountId from iptvmw database
     */
    private Long customerAccountId;
    
    /**
     * status (Active , Locked)
     */
    private Integer status;
    
    /**
     * User name
     */
    private String name;
    
    /**
     * registered date
     */
    private Date registeredDate;

    

    @Column(name = "ca_id", unique = true, nullable = false, insertable = true, updatable = true)
    public Long getCustomerAccountId() {
		return customerAccountId;
	}
    
    @Column(name = "status")
    public Integer getStatus() {
		return status;
	}
    
    @Column(name = "user_name", unique = true, nullable = false, insertable = true, updatable = true)
    public String getName() {
		return name;
	}
    
    @Column(name = "reg_date")
    public Date getRegisteredDate() {
		return registeredDate;
	}
    
    
    
    
    public void setCustomerAccountId(Long customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	
    
}
