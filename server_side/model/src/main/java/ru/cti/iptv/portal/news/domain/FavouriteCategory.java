package ru.cti.iptv.portal.news.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Favourite Category entity class
 * 
 */
@Entity
@Table(name = "news_favourite_categories")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "FavouriteCategory.findByProfileId", query = "select  categoryId from FavouriteCategory where userId = :profileId order by categoryId"),
        @NamedQuery(name = "FavouriteCategory.findByProfileIdAndCategoryId", query = "from FavouriteCategory where (userId = :profileId and categoryId = :categoryId) order by categoryId") })
public class FavouriteCategory extends AbstractEntity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Category's id
     */
    private int categoryId;
    
    /**
     * User's id for favourite category
     */
    private Long userId;
    
    /**
     * Getter method for category's id
     * 
     * @return the categoryId
     */
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }
    
    /**
     * Setter method for category's id
     * 
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    /**
     * Getter method for user's id
     * 
     * @return the userId
     */
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }
    
    /**
     * Setter method for user's id
     * 
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
