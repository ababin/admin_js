package ru.cti.oss.iptv.resource.logical;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**The Class <code>FilmSeries</code> is entity for present film series 
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2010
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *	@author a.babin <br/> <b>e-mail</b>: a.babin@cti.ru <br/>
 *	@version 1.0
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "episode")
public class Episode implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private java.util.Map < java.util.Locale, java.lang.String > i18nName;
    
    // relationship---------------------
    private java.util.Set <MediaSource> mediaSources = new java.util.TreeSet <MediaSource>();
    //----------------------------------
    
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
	public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
		
	@org.hibernate.annotations.CollectionOfElements(fetch = javax.persistence.FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "episode_i18n_name", joinColumns = { @javax.persistence.JoinColumn(name = "episode_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nName() {
        return i18nName;
    }
    
    public void setI18nName(java.util.Map < java.util.Locale, java.lang.String > i18nName) {
        this.i18nName = i18nName;
    }
	   	
	/**
     * Get the mediaAssets Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.MediaAsset>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @org.hibernate.annotations.BatchSize(size=100)
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @javax.persistence.JoinTable
    (
        name = "EPISODE2MEDIA_SOURCE",
        joinColumns = {@javax.persistence.JoinColumn(name = "EPISODE_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@javax.persistence.JoinColumn(name = "MEDIA_SOURCE_ID", referencedColumnName = "ID" ,unique = true)}
    )
    public java.util.Set <MediaSource> getMediaSources() {
		return mediaSources;
	}
	
    public void setMediaSources(java.util.Set<ru.cti.oss.iptv.resource.logical.MediaSource> mediaSources) {
		this.mediaSources = mediaSources;
	}

	
	public void addToMediaSources(MediaSource mediaSource) {
		if (mediaSource!=null){
    		this.getMediaSources().add(mediaSource);
    	}
	}
    
	
}
