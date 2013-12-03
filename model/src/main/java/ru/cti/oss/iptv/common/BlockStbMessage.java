/**
 * 
 */
package ru.cti.oss.iptv.common;

import org.hibernate.annotations.Index;

/**
 * @author eldar
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "BLOCK_STB_MESSAGE")
public class BlockStbMessage {
	
	private Long id;
	
	private String message;

	/**
	 * @return the id
	 */
	@javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Index(name = "id_index")
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	
}
