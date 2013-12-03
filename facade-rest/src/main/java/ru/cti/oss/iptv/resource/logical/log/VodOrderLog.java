package ru.cti.oss.iptv.resource.logical.log;



@javax.persistence.Entity
@javax.persistence.Table(name = "VOD_ORDERS_LOG_N")
@javax.persistence.NamedQueries
({
    @javax.persistence.NamedQuery(name = "VodOrderLog.getMaxId", query = "select max(log.id) from VodOrderLog log")
})
public class VodOrderLog implements java.io.Serializable {

	private static final long serialVersionUID = 4183342200566705219L;

	Long id;
	java.util.Date orderDate;
	byte[] part1;
	byte[] part2;
	

	@javax.persistence.Id
	@javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@javax.persistence.Lob
	@javax.persistence.Column(name = "PART1", nullable = false, insertable = true, updatable = true,length=1280)
	public byte[] getPart1() {
		return part1;
	}

	public void setPart1(byte[] part1) {
		this.part1 = part1;
	}
	
	@javax.persistence.Lob
	@javax.persistence.Column(name = "PART2", nullable = true, insertable = true, updatable = true,length=256)
	public byte[] getPart2() {
		return part2;
	}

	public void setPart2(byte[] part2) {
		this.part2 = part2;
	}

	@javax.persistence.Column(name = "ORDER_DATE", nullable = false, insertable = true, updatable = true)
	public java.util.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}
	
	

}
