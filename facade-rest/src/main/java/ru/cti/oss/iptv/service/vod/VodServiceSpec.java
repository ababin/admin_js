package ru.cti.oss.iptv.service.vod;

/**
 * Vod service specification class 
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "VOD_SRV_SPEC")
@org.hibernate.annotations.BatchSize(size=20)
@javax.persistence.NamedQueries( {
        
	// CasBean
	@javax.persistence.NamedQuery(name = "VodServiceSpec.findByRtspAssetName", 
       		query = "SELECT DISTINCT vss.id FROM VodServiceSpec vss " +
       				"INNER JOIN vss.movieCategories movieCategory " +
       				"INNER JOIN movieCategory.movies movie " +
       				"LEFT OUTER JOIN movie.mediaSources ms " +
       				"LEFT OUTER JOIN movie.episodes episode " +
       				"LEFT OUTER JOIN episode.mediaSources ems " +
       				"WHERE " +
       				"  vss.status = 'ACTIVE' " +
       				"  AND " +
       				"  ( " +
       				"    (ms MEMBER OF MediaAsset AND ms.fileName = :fileName AND ms.protocol = 'rtsp') " +
       				"    OR " +
       				"    (ems MEMBER OF MediaAsset AND ems.fileName = :fileName AND ems.protocol = 'rtsp') " +
       				"  )"),
       				
	// CasBean
	@javax.persistence.NamedQuery(name = "VodServiceSpec.findByIds", 
       		query = "SELECT vss FROM VodServiceSpec vss " +
       				"WHERE vss.id IN (:ids)"),
	
		// VodServiceSpecDaoBase
		@javax.persistence.NamedQuery(name = "VodServiceSpec.findAll", 
        		query = "select vodServiceSpec from VodServiceSpec AS vodServiceSpec"),
        
        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "VodServiceSpec.findAllIds", 
           		query = "SELECT vodServiceSpec.id FROM VodServiceSpec vodServiceSpec ORDER BY vodServiceSpec.id"),
           		
        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "VodServiceSpec.findAllPersonalIds", 
           		query = "SELECT spec.id FROM TariffPlan tp " +
           				"  INNER JOIN tp.tariffPlanSpecification tps " +
           				"  INNER JOIN tps.serviceSpecifications spec " +
           				"WHERE " +
           				"  tp.customer.name = :accountNumber " +
           				"  AND spec MEMBER OF VodServiceSpec " +
           				" ORDER BY spec.id"),
        
        // PvodServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.findMoviesIdsAndSpecsIdsBySpecsIds", 
        		query = "SELECT m.id , vss.externalId FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories mcat " +
        				"INNER JOIN mcat.movies m " +
        				"WHERE vss.externalId IN (:externalIds)"),
                        
        // PvodServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.findPackSpecsByContentType", 
        		query = "SELECT vss FROM VodServiceSpec vss " +
        				"WHERE " +
        				"  vss.contentType.name = :contentType " +
        				"  AND vss.status = 'ACTIVE' " +
        				"  AND vss.isPackage=TRUE " +
        				"  AND vss.externalId IN (:vssIds) " + 
        				"ORDER BY vss.name"),
        
        // PvodServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.findMoviesCount", 
        		query = "SELECT vss.id, COUNT(DISTINCT m.id) FROM VodServiceSpec vss "+
		        		"INNER JOIN vss.movieCategories cat "+
		        		"INNER JOIN cat.movies m "+
		        		"LEFT OUTER JOIN m.regions region " +
		        		"WHERE vss.status='ACTIVE' "+
		        		"  AND m.status='ACTIVE' " +
						"  AND m.rating.level <= :ratingLevel " +
						"  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
						"  AND (m.regions IS EMPTY OR region.code = :regionCode) " +
						"  AND ( " +
						"     EXISTS ( " +
						"          SELECT mov.id FROM Movie mov " +
						"          INNER JOIN mov.mediaSources mms " +
						"          LEFT OUTER JOIN mms.bandwidthRating bwr " +
						"          WHERE mov.id = m.id " +
						"          AND mov.episodes IS EMPTY " +
						"          AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
						"          AND (mms.encryption IS NULL OR mms.encryption='NONE' OR mms.encryption IN (:encryptions)) " +
						"          AND (mms.encoding IS NULL OR mms.encoding IN (:encodings))  " +
						"          AND (mms.resolution IS NULL OR mms.resolution IN (:resolutions)) " +
						"       ) OR " +
						"     EXISTS ( " +
						"          SELECT mov.id FROM Movie mov " +
						"          INNER JOIN mov.episodes episode " +
						"          INNER JOIN episode.mediaSources mms " +
						"          LEFT OUTER JOIN mms.bandwidthRating bwr " +
						"          WHERE mov.id = m.id " +
						"          AND mov.mediaSources IS EMPTY " +
						"          AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
						"          AND (mms.encryption IS NULL OR mms.encryption='NONE' OR mms.encryption IN (:encryptions)) " +
						"          AND (mms.encoding IS NULL OR mms.encoding IN (:encodings))  " +
						"          AND (mms.resolution IS NULL OR mms.resolution IN (:resolutions)) " +
						"       ) " +
						"   ) " +
						//"  AND (m.mediaSources IS NOT EMPTY OR m.episodes IS NOT EMPTY) " +
		        		"GROUP BY vss.id "),
		
		// PvodServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.findMoviesByExternalId", 
        		query = "SELECT m , contentType.name FROM VodServiceSpec vss "
		        		+ "INNER JOIN vss.movieCategories cat "
		        		+ "INNER JOIN cat.movies m " 
		        		+ "LEFT OUTER JOIN vss.contentType contentType "
		        		+ "LEFT OUTER JOIN m.regions region "  
		        		+ "WHERE " 
		        		// specification parameters
		        		+ " vss.status='ACTIVE' "
		        		+ " AND vss.externalId=:externalId "
		        		// movie parameters
		        		+ "  AND m.status='ACTIVE' " 
		        		+ "  AND m.rating.level <= :ratingLevel " 
		        		+ "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " 
		        		+ "  AND (m.regions IS EMPTY OR region.code = :regionCode) " 
						// mediaSource and episode parameters
		                + "  AND ( " 
		                + "     EXISTS ( "
		                + "          SELECT mov.id FROM Movie mov "
		                + "          INNER JOIN mov.mediaSources mms "
		                + "          LEFT OUTER JOIN mms.bandwidthRating bwr "
		                + "          WHERE mov.id = m.id "
		                + "          AND mov.episodes IS EMPTY "
		                + "          AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) "
		                + "          AND (mms.encryption IS NULL OR mms.encryption='NONE' OR mms.encryption IN (:encryptions)) "
		                + "          AND (mms.encoding IS NULL OR mms.encoding IN (:encodings))  "
		                + "          AND (mms.resolution IS NULL OR mms.resolution IN (:resolutions)) "
		                + "       ) OR "
		                + "     EXISTS ( "
		                + "          SELECT mov.id FROM Movie mov "
		                + "          INNER JOIN mov.episodes episode "
		                + "          INNER JOIN episode.mediaSources mms "
		                + "          LEFT OUTER JOIN mms.bandwidthRating bwr "
		                + "          WHERE mov.id = m.id "
		                + "          AND mov.mediaSources IS EMPTY "
		                + "          AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) "
		                + "          AND (mms.encryption IS NULL OR mms.encryption='NONE' OR mms.encryption IN (:encryptions)) "
		                + "          AND (mms.encoding IS NULL OR mms.encoding IN (:encodings))  "
		                + "          AND (mms.resolution IS NULL OR mms.resolution IN (:resolutions)) "
		                + "       ) "
		                + "   ) "
						
		        		+ "ORDER BY COALESCE(m.weightRating , 0) DESC , m.name , m.id ASC"),
		
		// PvodServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.findMoviesIdsByExternalIdAndMoviesIds", 
        		query = "SELECT m.id FROM VodServiceSpec vss "+
		        		"INNER JOIN vss.movieCategories cat "+
		        		"INNER JOIN cat.movies m "+
		        		"WHERE vss.status='ACTIVE' AND vss.externalId=:externalId "+
		        		"  AND m.id in (:ids) " +
		        		"  AND m.status='ACTIVE' " +
						"  AND m.rating.level <= :ratingLevel " +
						"  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
						"  AND (m.mediaSources IS NOT EMPTY OR m.episodes IS NOT EMPTY) "),
		
		// PvodServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.specInfoByExternalId", 
        		query = "SELECT vss.id , count(*) FROM VodServiceSpec vss "+
		        		"INNER JOIN vss.movieCategories cat "+
		        		"INNER JOIN cat.movies m "+
		        		"LEFT OUTER JOIN m.regions region " +
		        		"WHERE " 
		        		// specification parameters
		        		+ " vss.status='ACTIVE' AND vss.externalId=:externalId "
		        		// movie parameters
		        		+ "  AND m.status='ACTIVE' " 
		        		+ "  AND m.rating.level <= :ratingLevel " 
		        		+ "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " 
		        		+ "  AND (m.regions IS EMPTY OR region.code = :regionCode) " 
						// mediaSource and episode parameters
		                + "  AND ( " 
		                + "     EXISTS ( "
		                + "          SELECT mov.id FROM Movie mov "
		                + "          INNER JOIN mov.mediaSources mms "
		                + "          LEFT OUTER JOIN mms.bandwidthRating bwr "
		                + "          WHERE mov.id = m.id "
		                + "          AND mov.episodes IS EMPTY "
		                + "          AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) "
		                + "          AND (mms.encryption IS NULL OR mms.encryption='NONE' OR mms.encryption IN (:encryptions)) "
		                + "          AND (mms.encoding IS NULL OR mms.encoding IN (:encodings))  "
		                + "          AND (mms.resolution IS NULL OR mms.resolution IN (:resolutions)) "
		                + "       ) OR "
		                + "     EXISTS ( "
		                + "          SELECT mov.id FROM Movie mov "
		                + "          INNER JOIN mov.episodes episode "
		                + "          INNER JOIN episode.mediaSources mms "
		                + "          LEFT OUTER JOIN mms.bandwidthRating bwr "
		                + "          WHERE mov.id = m.id "
		                + "          AND mov.mediaSources IS EMPTY "
		                + "          AND (bwr IS NULL OR bwr.level <= :bandwidthLevel) "
		                + "          AND (mms.encryption IS NULL OR mms.encryption='NONE' OR mms.encryption IN (:encryptions)) "
		                + "          AND (mms.encoding IS NULL OR mms.encoding IN (:encodings))  "
		                + "          AND (mms.resolution IS NULL OR mms.resolution IN (:resolutions)) "
		                + "       ) "
		                + "   ) " +
		        		"GROUP BY vss.id"   		
						),
		
		// PvodServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.getLogoByExternalId", 
        		query = "SELECT vss.logo FROM VodServiceSpec vss " +
        				"WHERE vss.externalId=:externalId "),
		
        // PricingServiceImpl
		@javax.persistence.NamedQuery(name = "VodServiceSpec.findByExternalId", 
        		query = "SELECT vss FROM VodServiceSpec vss " +
        				"WHERE vss.externalId=:externalId "),
        
        @javax.persistence.NamedQuery(name = "VodServiceSpec.findByMovieInMovieCategories",
        		query = "select vss from VodServiceSpec vss " +
        				"inner join vss.movieCategories cat " +
        				"inner join cat.movies m " +
        				"where m.id = :id")
		        		
})
public class VodServiceSpec extends ru.cti.oss.cbe.service.ServiceSpecification implements java.io.Serializable
{
    
    private static final long serialVersionUID = 3273936984307651200L;
    
    // --------- Relationship Definitions -----------
    
    private java.util.Set < ru.cti.oss.iptv.resource.logical.MovieCategory > movieCategories = new java.util.TreeSet < ru.cti.oss.iptv.resource.logical.MovieCategory >();
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public VodServiceSpec() {
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
    public VodServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
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
     * @param movieCategories Value for the movieCategories relation
     */
    public VodServiceSpec(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.service.ServiceStatus status,
            java.lang.Long externalId, ru.cti.oss.iptv.Image logo,
            java.util.Set < ru.cti.oss.iptv.resource.logical.MovieCategory > movieCategories) {
        setName(name);
        setDescription(description);
        setStatus(status);
        setExternalId(externalId);
        setLogo(logo);
        
        setMovieCategories(movieCategories);
    }
    
    // ------------- Relations ------------------
    
    /**
     * Get the movieCategories Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.MovieCategory>
     */
    @javax.persistence.OneToMany(fetch = javax.persistence.FetchType.LAZY)
    public java.util.Set < ru.cti.oss.iptv.resource.logical.MovieCategory > getMovieCategories() {
        return this.movieCategories;
    }
    
    /**
     * Set the movieCategories
     *
     * @param movieCategories
     */
    public void setMovieCategories(java.util.Set < ru.cti.oss.iptv.resource.logical.MovieCategory > movieCategories) {
        this.movieCategories = movieCategories;
    }
    
    /**
     * Add to movieCategories
     *
     * @param movieCategory
     */
    
    public void addToMovieCategories(ru.cti.oss.iptv.resource.logical.MovieCategory movieCategory) {
        if (movieCategory == null)
            return;
        this.getMovieCategories().add(movieCategory);
    }
    
    /**
     * Remove from movieCategories
     *
     * @param movieCategory
     */
    
    public void removeFromMovieCategories(ru.cti.oss.iptv.resource.logical.MovieCategory movieCategory) {
        if (movieCategory == null)
            return;
        this.getMovieCategories().remove(movieCategory);
    }
    
    /*
     * (non-Javadoc)
     * @see ru.cti.oss.cbe.service.ServiceSpecification#countTypeSpecs()
     */
    @Override
    public int countTypeSpecs() {
    	if (movieCategories != null) {
    		return movieCategories.size();
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
        if (!(object instanceof VodServiceSpec)) {
            return false;
        }
        final VodServiceSpec that = (VodServiceSpec) object;
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