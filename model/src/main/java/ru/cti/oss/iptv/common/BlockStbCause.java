package ru.cti.oss.iptv.common;

import org.hibernate.annotations.Index;

@javax.persistence.Entity
@javax.persistence.Table(name = "BLOCK_STB_CAUSE")
public class BlockStbCause
{
    
    private Long id;
    
    private String cause;
    
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
    
    @javax.persistence.Column(name = "CAUSE", nullable = true, insertable = true, updatable = true, length = 3800)
    public String getCause() {
        return cause;
    }
    
    public void setCause(String cause) {
        this.cause = cause;
    }
    
}
