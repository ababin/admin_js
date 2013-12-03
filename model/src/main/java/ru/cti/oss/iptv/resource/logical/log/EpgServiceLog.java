package ru.cti.oss.iptv.resource.logical.log;

import java.util.Date;

import javax.persistence.Transient;



@javax.persistence.Entity
@javax.persistence.Table(name = "EPG_SERVICE_LOG")
@javax.persistence.NamedQueries
({
    @javax.persistence.NamedQuery(name = "EpgServiceLog.getAll", 
    		query = "SELECT log FROM EpgServiceLog log")
})
public class EpgServiceLog implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date beginDate;
	private Date endDate;
	private byte[] data;
	private boolean resultSuccess;
	private String serverName;
			
	/**
     * Get the id property.
     * 
     * @return java.lang.Long The value of id
     */
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public java.lang.Long getId() {
        return id;
    }
	
	/**
     * Set the id property.
     * @param value the new value
     */
    public void setId(final java.lang.Long value) {
        id = value;
    }
	
    @javax.persistence.Column(name = "BEGIN_DATE", unique = false, nullable = false, insertable = true, updatable = true)
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@javax.persistence.Column(name = "END_DATE", unique = false, nullable = false, insertable = true, updatable = true)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@javax.persistence.Lob
    @javax.persistence.Column(name = "DATA", insertable = true, updatable = true)
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@javax.persistence.Column(name = "RESULT_SUCCESS", insertable = true, updatable = true, nullable=false)
	public boolean isResultSuccess() {
		return resultSuccess;
	}

	public void setResultSuccess(boolean resultSuccess) {
		this.resultSuccess = resultSuccess;
	}

	@javax.persistence.Column(name = "SERVER_NAME", insertable = true, updatable = true, nullable=false)
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String server) {
		this.serverName = server;
	}
    		
	/**
	 * TRANSIENT FIELDS =============================================================================
	 */
	
	@Transient
	public String getDataAsString(){
		return new String(data);
	}
	
	@Transient
	public int getDurationInS(){
		return (int) ((endDate.getTime() - beginDate.getTime()) / 1000);
	}
	
	private boolean selected;
	
	@Transient
    public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
    /**
     * COMMON METHODS ====================================================================================
     */
    
    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = (29 * hashCode) + (this.getId() == null ? 0 : this.getId().hashCode());
        
        return hashCode;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + "(=");
        sb.append("serverName: ");
        sb.append(this.getServerName());
        sb.append(", beginDate: ");
        sb.append(this.getBeginDate());
        sb.append(", endDate: ");
        sb.append(this.getEndDate());
        sb.append(", resultSuccess: ");
        sb.append(this.isResultSuccess());
        sb.append(", id: ");
        sb.append(this.getId());
        sb.append(")");
        return sb.toString();
    }
		
}
