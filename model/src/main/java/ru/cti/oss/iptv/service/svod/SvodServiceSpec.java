// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package ru.cti.oss.iptv.service.svod;


/**
 * Autogenerated POJO EJB class for SvodServiceSpec containing the
 * bulk of the entity implementation.
 *
 * This is autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 *
 * 
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "SVOD_SERVICE_SPEC")
@org.hibernate.annotations.BatchSize(size=20)
@javax.persistence.NamedQueries( {
        /*
		@javax.persistence.NamedQuery(name = "SvodServiceSpec.findAll", query = "select svodServiceSpec from SvodServiceSpec AS svodServiceSpec"),
        @javax.persistence.NamedQuery(name = "SvodServiceSpec.findByExternalId", query = "from SvodServiceSpec as svodServiceSpec where svodServiceSpec.externalId = :externalId"),
        @javax.persistence.NamedQuery(name = "SvodServiceSpec.findByMovieId", query = "select spec from SvodServiceSpec spec join spec.movies mov where mov.id=:resourseId"),
        @javax.persistence.NamedQuery(name = "SvodServiceSpec.findPackage", query = "select sp.id,count(distinct mov.id) from SvodServiceSpec sp join sp.movies mov "
                + " where  mov.status='ACTIVE' and mov.rating.level<= :customerRating"
                + " and (mov.licenseExpired is null or mov.licenseExpired >= :now) and sp.externalId= :id "
                + " group by sp.id"),
        @javax.persistence.NamedQuery(name = "SvodServiceSpec.findPublicMovies", query = "select mov from SvodServiceSpec sp join sp.movies mov"
                + " where  mov.status='ACTIVE' and mov.rating.level<= :customerRating"
                + " and (mov.licenseExpired is null or mov.licenseExpired >= :now)  and sp.externalId = :packageId "),
        @javax.persistence.NamedQuery(name = "SvodServiceSpec.findPublicPackages", query = "select sp.id,count(distinct mov.id) from SvodServiceSpec sp join sp.movies mov "
                + " where  sp.contentType.name= :packType "
                + " and mov.status='ACTIVE'  and mov.rating.level<= :customerRating and sp.status='ACTIVE'"
                + " and (mov.licenseExpired is null or mov.licenseExpired >= :now)  group by sp.id,sp.name order by sp.name "),
        @javax.persistence.NamedQuery(name = "SvodServiceSpec.findMoviesIds", query = "select mov.id from SvodServiceSpec sp join sp.movies mov"
                + " where  sp.externalId = :packageId"),
        @javax.persistence.NamedQuery(name = "SvodServiceSpec.findActiveMoviesIdsByRatingAndIds", 
        		query = "select mov.id from SvodServiceSpec sp join sp.movies mov " +
        				"where sp.externalId=:packageId and mov.id in(:ids) and mov.status='ACTIVE' " +
        				"and mov.rating.level<= :customerRating " +
        				"and (mov.licenseExpired is null or mov.licenseExpired >= :now)"),
       @javax.persistence.NamedQuery(name = "SvodServiceSpec.findActiveMoviesIdsByRating", 
        		query = "select mov.id from SvodServiceSpec sp join sp.movies mov " +
        				"where mov.status='ACTIVE' and mov.rating.level<= :customerRating " +
        				"and (mov.licenseExpired is null or mov.licenseExpired >= :now)")
		*/
})
public class SvodServiceSpec extends ru.cti.oss.cbe.service.ServiceSpecification implements java.io.Serializable
{
    
    private static final long serialVersionUID = -1374142192063529610L;
    
    // --------- Relationship Definitions -----------
    
    private java.util.Set < ru.cti.oss.iptv.resource.logical.Movie > movies = new java.util.TreeSet < ru.cti.oss.iptv.resource.logical.Movie >();
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public SvodServiceSpec() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     */
    public SvodServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo) {
        super(name, description, status, externalId, logo);
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param externalId Value for the externalId property
     * @param logo Value for the logo property
     * @param movies Value for the movies relation
     */
    public SvodServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo, java.util.Set < ru.cti.oss.iptv.resource.logical.Movie > movies) {
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
        
        setMovies(movies);
    }
    
    // ------------- Relations ------------------
    
    /**
     * Get the movies Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.Movie>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "MOVIES2SVOD_SERVICE_SPECS", joinColumns = { @javax.persistence.JoinColumn(name = "SVOD_SERVICE_SPEC_IDC", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_IDC", referencedColumnName = "ID") })
    public java.util.Set < ru.cti.oss.iptv.resource.logical.Movie > getMovies() {
        return this.movies;
    }
    
    /**
     * Set the movies
     *
     * @param movies
     */
    public void setMovies(java.util.Set < ru.cti.oss.iptv.resource.logical.Movie > movies) {
        this.movies = movies;
    }
    
    /**
     * Add Movie
     *
     * @param movie
     */
    
    public void addToMovies(ru.cti.oss.iptv.resource.logical.Movie movie) {
        if (movie == null)
            return;
        this.getMovies().add(movie);
    }
    
    /**
     * Remove Movie
     *
     * @param movie
     */
    
    public void removeFromMovies(ru.cti.oss.iptv.resource.logical.Movie movie) {
        if (movie == null)
            return;
        this.getMovies().remove(movie);
    }
    
    /*
     * (non-Javadoc)
     * @see ru.cti.oss.cbe.service.ServiceSpecification#countTypeSpecs()
     */
    @Override
    public int countTypeSpecs() {
    	if (movies != null) {
    		return movies.size();
    	}
    	return 0;
    }
    
    // -------- Common Methods -----------
    
    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SvodServiceSpec)) {
            return false;
        }
        final SvodServiceSpec that = (SvodServiceSpec) object;
        if (this.getId() == null || that.getId() == null || !this.getId().equals(that.getId())) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a hash code value for the object
     *
     * @return int The hash code value
     */
    public int hashCode() {
        int hashCode = super.hashCode();
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());
        
        return hashCode;
    }
    
    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString() {
        return super.toString();
    }
	    
}