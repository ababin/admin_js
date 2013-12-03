package ru.cti.iptv.portal.news.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Read News entity class
 * 
 */
@Entity
@Table (name="news_read_news")
@javax.persistence.NamedQueries( {
        @NamedQuery(name = "ReadNews.fineReadNewsIdsByUserId", query = "select rns.newsId from ReadNews rns where rns.userId= :id")      
})
public class ReadNews extends AbstractEntity {
    
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * News's id
	 */
	private Long newsId;
	
	/**
	 * User's id for favourite category
	 */
	private Long userId;
	
	/**
	 * Getter method for News's id
	 * 
	 * @return the newsId
	 */
	@Column (name="news_id")
	public Long getNewsId() {
		return newsId;
	}

	/**
	 * Setter method for category's id
	 * 
	 * @param categoryId the categoryId to set
	 */
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	/**
	 * Getter method for user's id
	 * 
	 * @return the userId
	 */
	@Column (name="user_id")
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
