package ru.cti.oss.chat.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractEntity.
 * Each persistence object should inherit AbstractEntity.
 */
@MappedSuperclass
public abstract class AbstractEntity implements IEntity {

	/** The entity unique identifier */
    protected Long id;

    /**
     * Returns the entity identifier.
     *
     * @return entity identifier
     */
    @Id @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Long getId() {
        return id;
    }

    /**
     * Sets the entity identifier
     *
     * @param id the entity identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("id", getId()).toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
	public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity)) return false;
        AbstractEntity castObject = (AbstractEntity) obj;
        return new EqualsBuilder().append(
            this.getId(), castObject.getId()).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
	public int hashCode() {
    	if (getId() == null) {
    		return super.hashCode();
    	}

        return new HashCodeBuilder().append(getId()).toHashCode();
    }
}
