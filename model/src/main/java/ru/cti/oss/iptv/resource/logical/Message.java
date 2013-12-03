package ru.cti.oss.iptv.resource.logical;

import java.util.Date;

import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import ru.cti.oss.cbe.datatypes.MessagePriority;
import ru.cti.oss.cbe.datatypes.MessageState;

/**The Class <code>Message</code> is entity for present message in  TVE MQ
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2009-2011
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *	@author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 *  @author a.babin<br/> <b>e-mail</b>: a.babin@cti.ru <br/>
 *	@version 1.3.2
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "MESSAGE")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "Message.findByReceiver", query = "select message from Message as message where message.receiver=:receiver and ((message.timeExpires >:date and message.state = 'UNREAD') or (message.timeExpires is null and message.sendTime is not null) or (message.state = 'READ')) order by sendTime DESC"),
        @NamedQuery(name = "Message.findBySender", query = "select message from Message as message where message.sender=:sender order by sendTime DESC"),
        @NamedQuery(name = "Message.findPlannedByDate", query = "select message from Message as message where message.timeOfDelivery < :date and message.sendTime is null")})
public class Message implements java.io.Serializable
{
    
    private static final long serialVersionUID = 4762085949482237366L;
    private Long id;
    private String sender;
    private String receiver;
    private String receiverType;
    private String type;
    private MessagePriority priority;
    private MessageState state;
    private Date sendTime;
    private String head;
    private String body;
    private String display;
    private Boolean persisted = Boolean.FALSE;
    private String action;
    private Long timeOfDelivery;
    private Long timeExpires;
    private Long broadcastId;
    
    public Long getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(Long broadcastId) {
        this.broadcastId = broadcastId;
    }

    @Transient
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @javax.persistence.Column(name = "BODY", length = 3800, nullable = true, insertable = true, updatable = true)
    public String getBody() {
        return this.body;
    }
    
    @javax.persistence.Column(name = "DISPLAY", nullable = true, insertable = true, updatable = true)
    public String getDisplay() {
        return this.display;
    }
    
    @javax.persistence.Column(name = "HEAD", nullable = true, insertable = true, updatable = true)
    public String getHead() {
        return this.head;
    }
    
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Index(name = "id_index")
    public Long getId() {
        return this.id;
    }
    
    @javax.persistence.Column(name = "PRIORITY", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public MessagePriority getPriority() {
        return this.priority;
    }
    
    @javax.persistence.Column(name = "RECEIVER", nullable = true, insertable = true, updatable = true)
    @Index(name = "receiver_index")
    public String getReceiver() {
        return this.receiver;
    }
    
    @javax.persistence.Column(name = "RECEIVER_TYPE", nullable = true, insertable = true, updatable = true)
    public String getReceiverType() {
        return this.receiverType;
    }
    
    @javax.persistence.Column(name = "SENDER", nullable = true, insertable = true, updatable = true)
    @Index(name = "sender_index")
    public String getSender() {
        return this.sender;
    }
    
    @javax.persistence.Column(name = "SEND_TIME", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Index(name = "send_time_index")
    public Date getSendTime() {
        return this.sendTime;
    }
    
    @javax.persistence.Column(name = "STATE", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public MessageState getState() {
        return this.state;
    }
    
    @javax.persistence.Column(name = "MESSAGE_TYPE", nullable = true, insertable = true, updatable = true)
    public String getType() {
        return this.type;
    }
    
    @Transient
    public Boolean isPersisted() {
        return this.persisted;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public void setDisplay(String display) {
        this.display = display;
    }
    
    public void setHead(String head) {
        this.head = head;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setPersisted(Boolean persisted) {
        this.persisted = persisted;
    }
    
    public void setPriority(MessagePriority priority) {
        this.priority = priority;
    }
    
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    
    public void setState(MessageState state) {
        this.state = state;
    }
    
    public void setType(String type) {
        this.type = type;
    }

	/**
	 * @return the timeOfDelivery
	 */
    @javax.persistence.Column(name = "TIME_OF_DELIVERY", nullable = true, insertable = true, updatable = true)
	public Long getTimeOfDelivery() {
		return timeOfDelivery;
	}

	/**
	 * @param timeOfDelivery the timeOfDelivery to set
	 */
	public void setTimeOfDelivery(Long timeOfDelivery) {
		this.timeOfDelivery = timeOfDelivery;
	}

	/**
	 * @return the timeExpires
	 */
	@javax.persistence.Column(name = "TIME_EXPIRES", nullable = true, insertable = true, updatable = true)
	public Long getTimeExpires() {
		return timeExpires;
	}

	/**
	 * @param timeExpires the timeExpires to set
	 */
	public void setTimeExpires(Long timeExpires) {
		this.timeExpires = timeExpires;
	}	
        
}
