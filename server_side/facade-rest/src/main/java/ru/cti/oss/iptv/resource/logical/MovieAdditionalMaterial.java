package ru.cti.oss.iptv.resource.logical;


/**The Class <code>MovieAdditionalMaterial</code> is entity represent Vod movie additional material
 * @author n.makarov <br/> <b>e-mail</b>: n.makarov@cti.ru <br/>
 * @version 1.5
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "MOVIE_ADD_MATERIAL")
@org.hibernate.annotations.BatchSize(size = 20)
@javax.persistence.NamedQueries( {
	@javax.persistence.NamedQuery(name = "MovieAdditionalMaterial.getMovieAdditionalMaterialsByMovie",
			query =  	"SELECT mam FROM Movie m " + 
					 	"INNER JOIN m.additionalMaterials mam " +
					 	"INNER JOIN mam.mediaSources ms " +
					 	"LEFT OUTER JOIN mam.regions region " +
						"LEFT OUTER JOIN ms.bandwidthRating bwr " +
					 	"WHERE " + 
						"m.id = :movieId " +
						"AND (mam.regions IS EMPTY OR region.code = :regionCode) " +
						"AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
		                "AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
		                "AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
		                "AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) "),
						
	@javax.persistence.NamedQuery(name = "MovieAdditionalMaterial.getMovieCountAdditionalMaterialsByMovie",
			query =  	"SELECT COUNT(mam) FROM Movie m " + 
					 	"INNER JOIN m.additionalMaterials mam " +
					 	"INNER JOIN mam.mediaSources ms " +
					 	"LEFT OUTER JOIN mam.regions region " +
						"LEFT OUTER JOIN ms.bandwidthRating bwr " +
					 	"WHERE " + 
						"m.id = :movieId " +
						"AND (mam.regions IS EMPTY OR region.code = :regionCode) " +
						"AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
			            "AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
			            "AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
			            "AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) ")
})
public class MovieAdditionalMaterial extends ru.cti.oss.iptv.resource.logical.MediaContent implements java.io.Serializable
{
	private static final long serialVersionUID = -2666286961409803393L;
	
	// ----------- Attribute Definitions ------------
    private ru.cti.oss.iptv.Image poster = new ru.cti.oss.iptv.Image();
    private ru.cti.oss.iptv.resource.logical.ScreenFormat screenFormat;
    
	public MovieAdditionalMaterial() {
		super();
	}

    @javax.persistence.Embedded
    @javax.persistence.AttributeOverrides( {
            @javax.persistence.AttributeOverride(name = "width", column = @javax.persistence.Column(name = "POSTER_WIDTH", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "height", column = @javax.persistence.Column(name = "POSTER_HEIGHT", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "size", column = @javax.persistence.Column(name = "POSTER_SIZE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "contentType", column = @javax.persistence.Column(name = "POSTER_CONTENT_TYPE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "data", column = @javax.persistence.Column(name = "POSTER_DATA", insertable = true, updatable = true)) })
    public ru.cti.oss.iptv.Image getPoster() {
        return poster;
    }

	public void setPoster(ru.cti.oss.iptv.Image poster) {
		this.poster = poster;
	}

    @javax.persistence.Column(name = "SCREEN_FORMAT", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ru.cti.oss.iptv.resource.logical.ScreenFormat getScreenFormat() {
        return screenFormat;
    }

	public void setScreenFormat(ru.cti.oss.iptv.resource.logical.ScreenFormat screenFormat) {
		this.screenFormat = screenFormat;
	}
	
	public String toString() {
		return getName();
	}
}