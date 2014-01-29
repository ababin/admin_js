package ru.cti.oss.iptv.resource.logical;

import java.util.Locale;
import java.util.Map;

import javax.persistence.Column;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import ru.cti.oss.iptv.Image;

@javax.persistence.Entity
@javax.persistence.Table(name = "MOVIE_ACTOR")
@org.hibernate.annotations.BatchSize(size = 20)
@javax.persistence.NamedQueries( {
	@javax.persistence.NamedQuery(name = "MovieActor.getMovieActorsByMovie",
			query = "select a from MovieActor a " +
					"where a.id in (select ma.id from Movie m inner join m.movieActors ma where m.id=:movieId) " +
					"or a.id in (select md.id from Movie m inner join m.movieDirector md where m.id=:movieId)"),
	@javax.persistence.NamedQuery(name = "MovieActor.getCountMovieActorsByMovie",
			query = "select count(a) from MovieActor a " +
					"where a.id in (select ma.id from Movie m inner join m.movieActors ma where m.id=:movieId) " +
					"or a.id in (select md.id from Movie m inner join m.movieDirector md where m.id=:movieId)")
})
public class MovieActor {

	private java.lang.Long id;
	private java.util.Map<java.util.Locale, java.lang.String> i18nName;
	private java.util.Map<java.util.Locale, java.lang.String> i18nDescription;
	private ru.cti.oss.iptv.Image poster = new ru.cti.oss.iptv.Image();
	private String externalId;

    @javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	@javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true)
	@Index(name = "id_index")
	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	@org.hibernate.annotations.CollectionOfElements(fetch = javax.persistence.FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.TRUE)
	@org.hibernate.annotations.MapKeyManyToMany
	@javax.persistence.JoinTable(name = "mvactor_i18n_name", joinColumns = { @javax.persistence.JoinColumn(name = "resource_id", nullable = false) })
	public java.util.Map<java.util.Locale, java.lang.String> getI18nName() {
		return i18nName;
	}

	public void setI18nName(java.util.Map<java.util.Locale, java.lang.String> i18nName) {
		this.i18nName = i18nName;
	}

	@org.hibernate.annotations.CollectionOfElements(fetch = javax.persistence.FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.TRUE)
	@Column(name = "element", length = 3800, nullable = false)
	@org.hibernate.annotations.MapKeyManyToMany
	@javax.persistence.JoinTable(name = "mvactor_i18n_description", joinColumns = { @javax.persistence.JoinColumn(name = "resource_id", nullable = false) })
	public java.util.Map<java.util.Locale, java.lang.String> getI18nDescription() {
		return i18nDescription;
	}

	public void setI18nDescription(java.util.Map<java.util.Locale, java.lang.String> i18nDescription) {
		this.i18nDescription = i18nDescription;
	}

	@javax.persistence.Column(name = "EXTERNAL_ID", unique = true, nullable = false, insertable = true, updatable = true)	
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

	public MovieActor(){
		
	}
	
	public MovieActor(Long id, Map<Locale, String> i18nName, Map<Locale, String> i18nDescription, Image poster, String externalId) {
		super();
		this.id = id;
		this.i18nName = i18nName;
		this.i18nDescription = i18nDescription;
		this.poster = poster;
		this.externalId = externalId;
	}

    @Override
    public String toString() {
        return "MovieActor [id=" + id + ", i18nName=" + i18nName + ", i18nDescription=" + i18nDescription + ", poster="
                        + poster + ", externalId=" + externalId + "]";
    }
}
