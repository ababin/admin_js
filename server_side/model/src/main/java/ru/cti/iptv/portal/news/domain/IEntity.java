package ru.cti.iptv.portal.news.domain;

import java.io.Serializable;

/**
 * An interface used to represent the generic
 * methods for each possible entity
 */
public interface IEntity extends Serializable {

    /**
     * Returns the entity identifier.
     *
     * @return entity identifier
     */
    Long getId();

}
