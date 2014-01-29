package ru.cti.oss.messaging;

import java.util.Date;

import org.hibernate.annotations.Index;

import ru.cti.oss.cbe.datatypes.MessagePriority;
import ru.cti.oss.iptv.resource.logical.Message;

@javax.persistence.Entity
@javax.persistence.Table(name = "message_send")
@javax.persistence.NamedQueries({
	@javax.persistence.NamedQuery(name = "MessageSend.findAll", query = "select mes from MessageSend mes"),
	@javax.persistence.NamedQuery(name = "MessageSend.count", query = "select count(mes) from MessageSend mes")
})
public class MessageSend implements java.io.Serializable{
	
		
    /**
	 * 
	 */
	private static final long serialVersionUID = -1472108760980622969L;
	
	private Long id;
    private String sender;
    private String receiver;
    private String receiverType;
    private String type;    
    private MessagePriority priority;    
    private Date sendTime;    
    private String head;    
    private String body;
    private Integer countSend = 0;
    private Integer countReceive = 0;
    
    public MessageSend(){
    	
    }
    
    public MessageSend(Message mes , int countSend){
    	this.sender = mes.getSender();
    	this.receiver = mes.getReceiver();
    	this.receiverType = mes.getReceiverType();
    	this.type = mes.getType();
    	this.priority = mes.getPriority();
    	this.sendTime = mes.getSendTime();
    	this.head = mes.getHead();
    	this.body = mes.getBody();
    	this.countSend = countSend;
    }
    
    @javax.persistence.Column(name = "BODY", length=3800, nullable = true, insertable = true, updatable = true)
    public String getBody() {
        return this.body;
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
            
    @javax.persistence.Column(name = "MESSAGE_TYPE", nullable = true, insertable = true, updatable = true)  
    public String getType() {
        return this.type;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public void setHead(String head) {
        this.head = head;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public void setType(String type) {
        this.type = type;
    }

	public void setCountSend(Integer countSend) {
		this.countSend = countSend;
	}

	@javax.persistence.Column(name = "COUNT_SEND", nullable = false, insertable = true, updatable = true)
	public Integer getCountSend() {
		return countSend;
	}

	public void setCountReceive(Integer countReceive) {
		this.countReceive = countReceive;
	}

	@javax.persistence.Column(name = "COUNT_RECEIVE", nullable = false, insertable = true, updatable = true)
	public Integer getCountReceive() {
		return countReceive;
	}
	    
	
}