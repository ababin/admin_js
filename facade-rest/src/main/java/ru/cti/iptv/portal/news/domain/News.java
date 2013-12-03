package ru.cti.iptv.portal.news.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * News entity class
 * 
 */
@Entity
@Table(name = "news_news")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "News.findAll", query = "from News order by pubDate DESC , id"),
        @NamedQuery(name = "News.findAllByCategoryId", query = "from News where categoryId=:categoryId order by pubDate DESC , id"),
        @NamedQuery(name = "News.findAllByCategoryListId", query = "from News where categoryId in (:categoryIdList) order by pubDate DESC , id"),
        @NamedQuery(name = "News.findCountNewsInCategory", query = "select count(*) from News where categoryId=:categoryId") })
public class News extends AbstractEntity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * News's title
     */
    private String title;
    
    /**
     * News's description
     */
    private String description;
    
    /**
     * News's link
     */
    private String link;
    
    /**
     * News's publish date
     */
    private Date pubDate;
    
    /**
     * News's category id
     */
    private int categoryId;
    
    /**
     * Getter method for title of news
     * 
     * @return title
     */
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    
    /**
     * Setter method for title of news
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return the description
     */
    @Column(name = "description", length = 3800)
    public String getDescription() {
        return description;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the link
     */
    @Column(name = "link")
    public String getLink() {
        return link;
    }
    
    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    /**
     * @return the pubDate
     */
    @Column(name = "pub_date")
    public Date getPubDate() {
        return pubDate;
    }
    
    /**
     * @param pubDate the pubDate to set
     */
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
    
    /**
     * @return the categoryId
     */
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }
    
    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
}
