package ru.cti.oss.iptv.group;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinTable;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Index;

@javax.persistence.Entity
@javax.persistence.Table(name = "group_subscribe")
@javax.persistence.NamedQueries( {
        @javax.persistence.NamedQuery(name = "Group.findByExternalId", query = "select group from Group group where group.externalId=:externalId"),
        @javax.persistence.NamedQuery(name = "Group.findAllWithExternalId", query = "select group from Group group where group.externalId > 0")
		})
public class Group implements java.io.Serializable , Comparable <Group>{

	/**
	 *  serial version ID
	 */
	private static final long serialVersionUID = 3865080419321698206L;
	
	/**
	 * ID
	 */
	private Long id;
	
	private Long externalId;
	
	/**
	 * group name
	 */
	private String name;
	
	/**
	 * group description
	 */
	private String description;
	
	/**
	 * group type
	 */
	private GroupType type;
	
	/**
	 * attached customers set 
	 */
	private List <Long> items = new ArrayList <Long> ();
	
	
	/**
	 * default constructor
	 */
	public Group(){
	}

	/**
	 * Set name
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get name
	 * @return String
	 */
	@javax.persistence.Column(name = "GR_NAME", insertable = true, updatable = true , nullable = false)
	public String getName() {
		return name;
	}
	
	/**
	 * Set group description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get group description
	 * @return String
	 */
	@javax.persistence.Column(name = "GR_DESC", insertable = true, updatable = true , nullable = true)
	public String getDescription() {
		return description;
	}

	/**
	 * Set items set
	 * @param items List <Long>
	 */
	public void setItems(List <Long> items) {
		this.items = items;
	}
	
	/**
	 * Get items set
	 * @return Set <Long>
	 */
	@CollectionOfElements
	@JoinTable(name = "group_items")
	public List <Long> getItems() {
		return items;
	}
	
	/**
     * Add to items
     * @param item
     */
    public void addToItems(Long item) {
        if (item != null) {
        	this.getItems().add(item);
        }
    }
    
    /**
     * Remove from items
     * @param item
     */
    public void removeFromItems(Long item) {
        if (item != null) {
        	this.getItems().remove(item);
        }
    }

	/**
	 * Set entity ID
	 * @param id
	 */
    public void setId(Long id) {
		this.id = id;
	}

    /**
     * Get entity ID
     * @return Long
     */
	@javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Index(name = "id_index")
	public Long getId() {
		return id;
	}

	/**
	 * Set group type
	 * @param type GroupType
	 */
	public void setType(GroupType type) {
		this.type = type;
	}

	/**
	 * Get group type
	 * @return GroupType
	 */
	@javax.persistence.Column(name = "GR_TYPE", nullable = false, insertable = true, updatable = false)
	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	public GroupType getType() {
		return type;
	}
	
	
	/**
	 * compareTo override method for entity
	 */
	@Override
	public int compareTo(Group o) {
		int cmp = 0;
        if (this.getId() != null) {
            cmp = this.getId().compareTo(o.getId());
        } else {
            if (this.getName() != null) {
                cmp = (cmp != 0 ? cmp : this.getName().compareTo(o.getName()));
            }
            if (this.getDescription() != null) {
                cmp = (cmp != 0 ? cmp : this.getDescription().compareTo(o.getDescription()));
            }
        }
        return cmp;
	}

	/**
	 * Get external ID
	 * @return Long
	 */
	@javax.persistence.Column(name = "EXTERNAL_ID", insertable = true, updatable = true , nullable = true)
	public Long getExternalId() {
		return externalId;
	}
	
	/**
	 * Set external ID
	 * @param externalId Long
	 */
	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}

	
	    
	
}