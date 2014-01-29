package ru.cti.oss.iptv.common;

import java.util.Date;

import org.hibernate.annotations.Index;

@javax.persistence.Entity
@javax.persistence.Table(name = "BLACKLIST")
@javax.persistence.NamedQueries({ @javax.persistence.NamedQuery(name = "BlackList.findByMACandSerial", query = "select bl from BlackList bl where bl.mac =:mac OR bl.serial =:serial") })
public class BlackList
{
    
    private Long id;
    private String mac;
    private String serial;
    private String cause;
    private String message;
    private Date date;
    private String userLogin;
    private String comment;
    
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Index(name = "id_index")
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @javax.persistence.Column(name = "MAC", nullable = true, insertable = true, updatable = true)
    @Index(name = "mac_index")
    public String getMac() {
        return mac;
    }
    
    public void setMac(String mac) {
        this.mac = mac;
    }
    
    @javax.persistence.Column(name = "SERIAL", nullable = true, insertable = true, updatable = true)
    public String getSerial() {
        return serial;
    }
    
    public void setSerial(String serial) {
        this.serial = serial;
    }
    
    @javax.persistence.Column(name = "CAUSE", nullable = true, insertable = true, updatable = true, length = 3800)
    public String getCause() {
        return cause;
    }
    
    public void setCause(String cause) {
        this.cause = cause;
    }

	/**
	 * @return the message
	 */
    @javax.persistence.Column(name = "MESSAGE", nullable = true, insertable = true, updatable = true, length = 3800)
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the date
	 */
	@javax.persistence.Column(name = "DATE_", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the userLogin
	 */
	@javax.persistence.Column(name = "USER_LOGIN", nullable = true, insertable = true, updatable = true)
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * @param userLogin the userLogin to set
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * @return the comment
	 */
	@javax.persistence.Column(name = "COMMENT_MSG", nullable = true, insertable = true, updatable = true, length = 3800)
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}  
    
}
