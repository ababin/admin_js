package ru.cti.oss.iptv.resource.logical;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

/**The Class <code>Movie</code> is entity represent Vod movie
 *	<p>
 *  <b>Copyright: </b>Copyright (c) 2009-2011
 *  </p>
 *	<p>
 *   <b>Company: </b>CTI
 *  </p>
 *
 *	@author n.makarov <br/> <b>e-mail</b>: n.makarov@cti.ru <br/>
 * @author e.batogov <br/> <b>e-mail</b>: e.batogov@cti.ru <br/>
 * @author a.babin <br/> <b>e-mail</b>: a.babin@cti.ru <br/>
 * @author e.abdrazakov <br/> <b>e-mail</b>: e.abdrazakov@cti.ru <br/>
 *	@version 1.6.1
*/
@javax.persistence.Entity
@javax.persistence.Table(name = "MOVIE")
@org.hibernate.annotations.BatchSize(size = 20)
@javax.persistence.NamedQueries({
		
		// VodServiceImpl
		@javax.persistence.NamedQuery(name = "Movie.findMovieAndContentTypeById", 
			   	query = "SELECT movie, vss.contentType.name FROM VodServiceSpec vss " +
			   			"INNER JOIN vss.movieCategories movieCategory " +
			   			"INNER JOIN movieCategory.movies movie " +
		       			"WHERE " +
		       			"  movie.id = :id"),
	
		// CasBean
		@javax.persistence.NamedQuery(name = "Movie.findByEpisodeAsset", 
		       	query = "SELECT m FROM Movie m " +
		       			"INNER JOIN m.episodes episode " +
		       			"INNER JOIN episode.mediaSources ems " +
		       			"WHERE ems.id = :assetId"),
	
		// ExportResourceTest , MovieMigrationConverterTest
        @javax.persistence.NamedQuery(name = "Movie.findAll", 
        		query =	"SELECT m FROM Movie m " +
        				"ORDER BY COALESCE(m.weightRating , 0) DESC"),
        
        // PromoServiceImpl
        // find movies by IDS ONLY !!!!!! 
        @javax.persistence.NamedQuery(name = "Movie.findMoviesByIds", 
        	query = "SELECT m FROM Movie m  " + 
        			"WHERE " +
        			"  m.id IN (:ids) " +
                	"ORDER BY COALESCE(m.weightRating , 0) DESC, m.name , m.id ASC"),

        // MoviesMigrationServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.findMoviesOnlyByIds", 
        	query = "SELECT m FROM Movie m  " +
        			"WHERE " +
        			"  m.id in (:ids) "),

        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.findMoviesByContentTypeAndIds", 
        		query = "SELECT m FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories cat " +
        				"INNER JOIN cat.movies m " +
        				"WHERE " +
        				"  vss.externalId IN (:vssIds) " +
		        		"  AND vss.contentType.name=:contentType " +
		        		"  AND m.id in (:ids) " +
        				"ORDER BY COALESCE(m.weightRating , 0) DESC, m.name , m.id ASC"),

        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.findMoviesByCategoryAndContentType", 
        		query = "SELECT movie FROM Movie movie " +
        				"WHERE movie.id IN (" +
        				
						"SELECT DISTINCT m.id FROM VodServiceSpec vss " +
						"INNER JOIN vss.movieCategories cat " +
						"INNER JOIN cat.movies m " +
						"LEFT OUTER JOIN m.mediaSources ms " +
						"LEFT OUTER JOIN ms.bandwidthRating bwr " +
						"LEFT OUTER JOIN m.episodes episode " +
						"LEFT OUTER JOIN episode.mediaSources ems " +
						"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
						"INNER JOIN m.categories genre " +
                		"LEFT OUTER JOIN m.regions region " +
                		
                		"WHERE " +
                		"  vss.status='ACTIVE' " +
                		"  AND vss.contentType.name=:contentType " +
                		"  AND vss.externalId IN (:vssIds) " +
                		
                		"  AND genre.id=:genre " +
                		
                		// Movie parameters
                        "  AND m.id = movie.id " +
                		"  AND m.status='ACTIVE' " +
                        "  AND m.rating.level <= :ratingLevel " +
                        "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
                        "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
                        "  AND (region IS NULL OR region.code = :regionCode) " +
                                       
                        // mediaSource and episode parameters
                        "  AND ( "  +
                        "        ( " +
                        "          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
                        "          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
                        "          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
                        "          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
                        "		   AND (ms.videoservers IS NOT EMPTY) " +
                        
                        "       ) OR " +
                        "       ( " +
                        "          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
                        "          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
                        "          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
                        "          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
                        "		   AND (ems.videoservers IS NOT EMPTY) " +
                        "       ) " +
                        "     ) " +
                		
                        ")" +
                        
                		"ORDER BY COALESCE(movie.weightRating , 0) DESC , movie.name , movie.id ASC" ) ,

        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.findIdsByContentTypeAndIds", 
        		query = "SELECT DISTINCT m.id FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories cat " +
						"INNER JOIN cat.movies m " +
						"LEFT OUTER JOIN m.mediaSources ms " +
						"LEFT OUTER JOIN ms.bandwidthRating bwr " +
						"LEFT OUTER JOIN m.episodes episode " +
						"LEFT OUTER JOIN episode.mediaSources ems " +
						"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
                		"LEFT OUTER JOIN m.regions region " +
						
        				"WHERE " +
        				"  vss.status='ACTIVE' " +
        				"  AND vss.contentType.name=:contentType " +
        				"  AND vss.externalId IN (:vssIds) " +
                		                  
                		// Movie parameters
                		"  AND m.id IN (:ids) " +
                        "  AND m.status='ACTIVE' " +
                        "  AND m.rating.level <= :ratingLevel " +
                        "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :licenseExpired) " +
                        "  AND (m.beginDate IS NULL OR m.beginDate <= :licenseBegin)" +
                        "  AND (region IS NULL OR region.code = :regionCode) " +
                                       
						//mediaSource and episode parameters
						"  AND ( "  +
						"        ( " +
						"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
						"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
						"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
						"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
						"		   AND (ms.videoservers IS NOT EMPTY) " +
						
						"       ) OR " +
						"       ( " +
						"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
						"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
						"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
						"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
						"		   AND (ems.videoservers IS NOT EMPTY) " +
						"       ) " +
						"     ) ") ,

        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.findIdsByContentType", 
        		query = "SELECT DISTINCT m.id FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories cat " +
						"INNER JOIN cat.movies m " +
						"LEFT OUTER JOIN m.mediaSources ms " +
						"LEFT OUTER JOIN ms.bandwidthRating bwr " +
						"LEFT OUTER JOIN m.episodes episode " +
						"LEFT OUTER JOIN episode.mediaSources ems " +
						"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
                		"LEFT OUTER JOIN m.regions region " +
						
        				"WHERE " +
        				"  vss.status='ACTIVE' " +
        				"  AND vss.contentType.name=:contentType " +
        				"  AND vss.externalId IN (:vssIds) " +
                		                  
                		// Movie parameters
                        "  AND m.status='ACTIVE' " +
                        "  AND m.rating.level <= :ratingLevel " +
                        "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :licenseExpired) " +
                        "  AND (m.beginDate IS NULL OR m.beginDate <= :licenseBegin) " +
                        "  AND (region IS NULL OR region.code = :regionCode) " +
                                       
						//mediaSource and episode parameters
						"  AND ( "  +
						"        ( " +
						"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
						"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
						"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
						"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
						"		   AND (ms.videoservers IS NOT EMPTY) " +
						
						"       ) OR " +
						"       ( " +
						"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
						"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
						"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
						"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
						"		   AND (ems.videoservers IS NOT EMPTY) " +
						"       ) " +
						"     ) ") ,


        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.getRecommendedMovies", 
        		query = "SELECT rMovie, vss.contentType.name FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories cat " +
        				"INNER JOIN cat.movies rMovie " +
        				"WHERE " +
        				"  vss.status='ACTIVE' " +
        				"  AND vss.isPackage = :isPackage " +
        				"  AND rMovie.id IN ( " +
        		       		
	        					"SELECT DISTINCT rMovie.id FROM Movie movie " +
		        				"INNER JOIN movie.recommendedMovies rMovie " +
		        				"LEFT OUTER JOIN rMovie.mediaSources ms " +
								"LEFT OUTER JOIN ms.bandwidthRating bwr " +
								"LEFT OUTER JOIN rMovie.episodes episode " +
								"LEFT OUTER JOIN episode.mediaSources ems " +
								"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
		                		"LEFT OUTER JOIN rMovie.regions region " +
							   	
		                		"WHERE " +
		        				"  movie.id = :id " + 
		        				
		        				// Movie parameters
		                        "  AND rMovie.status='ACTIVE' " +
		                        "  AND rMovie.rating.level <= :ratingLevel " +
		                        "  AND (rMovie.licenseExpired IS NULL OR rMovie.licenseExpired >= :now) " +
		                        "  AND (rMovie.beginDate IS NULL OR rMovie.beginDate <= :now) " +
		                        "  AND (region IS NULL OR region.code = :regionCode) " +
		                                       
								//mediaSource and episode parameters
								"  AND ( "  +
								"        ( " +
								"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
								"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
								"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
								"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
								"		   AND (ms.videoservers IS NOT EMPTY) " +
								
								"       ) OR " +
								"       ( " +
								"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
								"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
								"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
								"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
								"		   AND (ems.videoservers IS NOT EMPTY) " +
								"       ) " +
								"     ) " +																										
						
						"  ) " +
                		
                		"ORDER BY COALESCE(rMovie.weightRating , 0) DESC , rMovie.name , rMovie.id ASC" ) ,

		// VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.getRecommendedMoviesCount", 
        		query = "SELECT COUNT(DISTINCT rMovie.id) FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories cat " +
        				"INNER JOIN cat.movies rMovie " +
        				"INNER JOIN vss.contentType contentType " +
        				"WHERE " +
        				"  vss.status='ACTIVE' " +
        				"  AND vss.isPackage = :isPackage " +
        				"  AND rMovie.id IN ( " +
        		       		
	        					"SELECT DISTINCT rMovie.id FROM Movie movie " +
		        				"INNER JOIN movie.recommendedMovies rMovie " +
		        				"LEFT OUTER JOIN rMovie.mediaSources ms " +
								"LEFT OUTER JOIN ms.bandwidthRating bwr " +
								"LEFT OUTER JOIN rMovie.episodes episode " +
								"LEFT OUTER JOIN episode.mediaSources ems " +
								"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
		                		"LEFT OUTER JOIN rMovie.regions region " +
							   	
		                		"WHERE " +
		        				"  movie.id = :id " + 
		        				
		        				// Movie parameters
		                        "  AND rMovie.status='ACTIVE' " +
		                        "  AND rMovie.rating.level <= :ratingLevel " +
		                        "  AND (rMovie.licenseExpired IS NULL OR rMovie.licenseExpired >= :now) " +
		                        "  AND (rMovie.beginDate IS NULL OR rMovie.beginDate <= :now) " +
		                        "  AND (region IS NULL OR region.code = :regionCode) " +
		                                       
								//mediaSource and episode parameters
								"  AND ( "  +
								"        ( " +
								"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
								"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
								"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
								"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
								"		   AND (ms.videoservers IS NOT EMPTY) " +
								
								"       ) OR " +
								"       ( " +
								"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
								"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
								"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
								"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
								"		   AND (ems.videoservers IS NOT EMPTY) " +
								"       ) " +
								"     ) " +																										
						
						"  ) ") ,
                		
        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.getRecommendedMoviesForBasic", 
        		query = "SELECT rMovie, vss.contentType.name FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories cat " +
        				"INNER JOIN cat.movies rMovie " +
        				"WHERE " +
        				"  vss.status='ACTIVE' " +
        				"  AND vss.isPackage = :isPackage " +
        				"  AND vss.contentType.name LIKE '%basic%' " +
        				"  AND rMovie.id IN ( " +
        		       		
	        					"SELECT DISTINCT rMovie.id FROM Movie movie " +
		        				"INNER JOIN movie.recommendedMovies rMovie " +
		        				"LEFT OUTER JOIN rMovie.mediaSources ms " +
								"LEFT OUTER JOIN ms.bandwidthRating bwr " +
								"LEFT OUTER JOIN rMovie.episodes episode " +
								"LEFT OUTER JOIN episode.mediaSources ems " +
								"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
		                		"LEFT OUTER JOIN rMovie.regions region " +
							   	
		                		"WHERE " +
		        				"  movie.id = :id " + 
		        				
		        				// Movie parameters
		                        "  AND rMovie.status='ACTIVE' " +
		                        "  AND rMovie.rating.level <= :ratingLevel " +
		                        "  AND (rMovie.licenseExpired IS NULL OR rMovie.licenseExpired >= :now) " +
		                        "  AND (rMovie.beginDate IS NULL OR rMovie.beginDate <= :now) " +
		                        "  AND (region IS NULL OR region.code = :regionCode) " +
		                                       
								//mediaSource and episode parameters
								"  AND ( "  +
								"        ( " +
								"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
								"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
								"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
								"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
								"		   AND (ms.videoservers IS NOT EMPTY) " +
								
								"       ) OR " +
								"       ( " +
								"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
								"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
								"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
								"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
								"		   AND (ems.videoservers IS NOT EMPTY) " +
								"       ) " +
								"     ) " +																										
						
						"  ) " +
                		
                		"ORDER BY COALESCE(rMovie.weightRating , 0) DESC , rMovie.name , rMovie.id ASC" ) ,

         

        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.getRecommendedMoviesCountForBasic", 
        		query = "SELECT COUNT(DISTINCT rMovie.id) FROM VodServiceSpec vss " +
        				"INNER JOIN vss.movieCategories cat " +
        				"INNER JOIN cat.movies rMovie " +
        				"INNER JOIN vss.contentType contentType " +
        				"WHERE " +
        				"  vss.status='ACTIVE' " +
        				"  AND vss.isPackage = :isPackage " +
        				"  AND contentType.name LIKE '%basic%' " +
        				"  AND rMovie.id IN ( " +
        		       		
	        					"SELECT DISTINCT rMovie.id FROM Movie movie " +
		        				"INNER JOIN movie.recommendedMovies rMovie " +
		        				"LEFT OUTER JOIN rMovie.mediaSources ms " +
								"LEFT OUTER JOIN ms.bandwidthRating bwr " +
								"LEFT OUTER JOIN rMovie.episodes episode " +
								"LEFT OUTER JOIN episode.mediaSources ems " +
								"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
		                		"LEFT OUTER JOIN rMovie.regions region " +
							   	
		                		"WHERE " +
		        				"  movie.id = :id " + 
		        				
		        				// Movie parameters
		                        "  AND rMovie.status='ACTIVE' " +
		                        "  AND rMovie.rating.level <= :ratingLevel " +
		                        "  AND (rMovie.licenseExpired IS NULL OR rMovie.licenseExpired >= :now) " +
		                        "  AND (rMovie.beginDate IS NULL OR rMovie.beginDate <= :now) " +
		                        "  AND (region IS NULL OR region.code = :regionCode) " +
		                                       
								//mediaSource and episode parameters
								"  AND ( "  +
								"        ( " +
								"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
								"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
								"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
								"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
								"		   AND (ms.videoservers IS NOT EMPTY) " +
								
								"       ) OR " +
								"       ( " +
								"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
								"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
								"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
								"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
								"		   AND (ems.videoservers IS NOT EMPTY) " +
								"       ) " +
								"     ) " +																										
						
						"  ) ") ,	
        																						 		 
        // PromoServiceImpl             
        @javax.persistence.NamedQuery(name = "Movie.findIdsByIdsWithPromo", 
        	query = "SELECT m.id, vss.externalId, vss.isPackage FROM VodServiceSpec vss " +
        			"INNER JOIN vss.movieCategories cat " +
        			"INNER JOIN cat.movies m " +
        			"INNER JOIN m.promoTrailers promo " +
        			"LEFT OUTER JOIN promo.bandwidthRating promo_bwr " +
					"LEFT OUTER JOIN m.mediaSources ms " +
					"LEFT OUTER JOIN ms.bandwidthRating bwr " +
					"LEFT OUTER JOIN m.episodes episode " +
					"LEFT OUTER JOIN episode.mediaSources ems " +
					"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
        			"LEFT OUTER JOIN m.regions region " +
                	"WHERE " +
                	"  vss.status='ACTIVE' " +
                	"  AND m.id IN (:ids) " +
                
                	// Movie parameters
                	"  AND m.status='ACTIVE' " +
                	"  AND m.rating.level <= :ratingLevel " +
                	"  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
                	"  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
                	"  AND (region IS NULL OR region.code = :regionCode) " +
                               
					//mediaSource and episode parameters
					"  AND ( "  +
					"        ( " +
					"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
					"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
					"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
					"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
					"		   AND (ms.videoservers IS NOT EMPTY) " +
					
					"       ) OR " +
					"       ( " +
					"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
					"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
					"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
					"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
					"		   AND (ems.videoservers IS NOT EMPTY) " +
					"       ) " +
					"     ) " +
                
        			// promo trailer parameters
        			"  AND ( "  +
					"          (promo_bwr IS NULL OR promo_bwr.level <= :bandwidthLevel) " +
					"          AND (promo.encryption IS NULL OR promo.encryption='NONE' OR promo.encryption IN (:encryptions)) " +
					"          AND (promo.encoding IS NULL OR promo.encoding IN (:encodings))  " +
					"          AND (promo.resolution IS NULL OR promo.resolution IN (:resolutions)) " +
					"     ) " +
                	
        			""),
        
        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.getMoviesByDirectorOrActor", 
        	query =	"SELECT m , vss.contentType.name FROM VodServiceSpec vss " +
        			"INNER JOIN vss.movieCategories movieCategory " +
        			"INNER JOIN movieCategory.movies m " +
				   	"WHERE m.id IN (" +
					
						   	"SELECT DISTINCT m.id FROM VodServiceSpec vss " +
						   	"INNER JOIN vss.movieCategories cat " +
						   	"INNER JOIN cat.movies m " +
						   	"LEFT OUTER JOIN m.movieActors actor " +
						   	"LEFT OUTER JOIN m.mediaSources ms " +
							"LEFT OUTER JOIN ms.bandwidthRating bwr " +
							"LEFT OUTER JOIN m.episodes episode " +
							"LEFT OUTER JOIN episode.mediaSources ems " +
							"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
						   	"LEFT OUTER JOIN m.regions region " +
						   	
						   	"WHERE " +
							
						   	"  vss.status='ACTIVE' " +
							"  AND vss.isPackage = :isPackage " +
						 	"  AND (m.movieDirector.id = :actorId OR actor.id = :actorId) " + 
						 	
						 	// Movie parameters
			                "  AND m.status='ACTIVE' " +
			                "  AND m.rating.level <= :ratingLevel " +
			                "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
			                "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
			                "  AND (region IS NULL OR region.code = :regionCode) " +
			                               
							//mediaSource and episode parameters
							"  AND ( "  +
							"        ( " +
							"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
							"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
							"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
							"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
							"		   AND (ms.videoservers IS NOT EMPTY) " +
							
							"       ) OR " +
							"       ( " +
							"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
							"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
							"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
							"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
							"		   AND (ems.videoservers IS NOT EMPTY) " +
							"       ) " +
							"     ) " +
			        		
							") " +
	        		
	        		"ORDER BY COALESCE(m.weightRating , 0) DESC , m.name , m.id ASC" ) ,
				 	
		// VodServiceImpl               
        @javax.persistence.NamedQuery(name = "Movie.getMoviesCountByDirectorOrActor", 
        	query =	"SELECT COUNT(DISTINCT m.id) FROM VodServiceSpec vss " +
				   	"INNER JOIN vss.movieCategories cat " +
				   	"INNER JOIN cat.movies m " +
				   	"INNER JOIN vss.contentType contentType " +
				   	"LEFT OUTER JOIN m.movieActors actor " +
				   	"LEFT OUTER JOIN m.mediaSources ms " +
					"LEFT OUTER JOIN ms.bandwidthRating bwr " +
					"LEFT OUTER JOIN m.episodes episode " +
					"LEFT OUTER JOIN episode.mediaSources ems " +
					"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
				   	"LEFT OUTER JOIN m.regions region " +
				   	
				   	"WHERE " +
					
				   	"  vss.status='ACTIVE' " +
					"  AND vss.isPackage = :isPackage " +
				 	"  AND (m.movieDirector.id = :actorId OR actor.id = :actorId) " + 
				 	
				 	// Movie parameters
	                "  AND m.status='ACTIVE' " +
	                "  AND m.rating.level <= :ratingLevel " +
	                "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
	                "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
	                "  AND (region IS NULL OR region.code = :regionCode) " +
	                               
					//mediaSource and episode parameters
					"  AND ( "  +
					"        ( " +
					"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
					"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
					"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
					"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
					"		   AND (ms.videoservers IS NOT EMPTY) " +
					
					"       ) OR " +
					"       ( " +
					"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
					"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
					"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
					"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
					"		   AND (ems.videoservers IS NOT EMPTY) " +
					"       ) " +
					"     ) " +
	        		
					""),

        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.getMoviesByDirectorOrActorForBasic", 
        	query =	"SELECT m , vss.contentType.name FROM VodServiceSpec vss " +
        			"INNER JOIN vss.movieCategories movieCategory " +
        			"INNER JOIN movieCategory.movies m " +
				   	"WHERE m.id IN (" +
					
						   	"SELECT DISTINCT m.id FROM VodServiceSpec vss " +
						   	"INNER JOIN vss.movieCategories cat " +
						   	"INNER JOIN cat.movies m " +
						   	"LEFT OUTER JOIN m.movieActors actor " +
						   	"LEFT OUTER JOIN m.mediaSources ms " +
							"LEFT OUTER JOIN ms.bandwidthRating bwr " +
							"LEFT OUTER JOIN m.episodes episode " +
							"LEFT OUTER JOIN episode.mediaSources ems " +
							"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
						   	"LEFT OUTER JOIN m.regions region " +
						   	
						   	"WHERE " +
							
						   	"  vss.status='ACTIVE' " +
							"  AND vss.isPackage = :isPackage " +
						   	"  AND vss.contentType.name LIKE '%basic%' " +
						 	"  AND (m.movieDirector.id = :actorId OR actor.id = :actorId) " + 
						 	
						 	// Movie parameters
			                "  AND m.status='ACTIVE' " +
			                "  AND m.rating.level <= :ratingLevel " +
			                "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
			                "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
			                "  AND (region IS NULL OR region.code = :regionCode) " +
			                               
							//mediaSource and episode parameters
							"  AND ( "  +
							"        ( " +
							"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
							"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
							"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
							"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
							"		   AND (ms.videoservers IS NOT EMPTY) " +
							
							"       ) OR " +
							"       ( " +
							"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
							"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
							"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
							"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
							"		   AND (ems.videoservers IS NOT EMPTY) " +
							"       ) " +
							"     ) " +
			        		
							") " +
	        		
	        		"ORDER BY COALESCE(m.weightRating , 0) DESC , m.name , m.id ASC" ) ,

        // VodServiceImpl               
        @javax.persistence.NamedQuery(name = "Movie.getMoviesCountByDirectorOrActorForBasic", 
        	query =	"SELECT COUNT(DISTINCT m.id) FROM VodServiceSpec vss " +
				   	"INNER JOIN vss.movieCategories cat " +
				   	"INNER JOIN cat.movies m " +
				   	"LEFT OUTER JOIN m.movieActors actor " +
				   	"LEFT OUTER JOIN m.mediaSources ms " +
					"LEFT OUTER JOIN ms.bandwidthRating bwr " +
					"LEFT OUTER JOIN m.episodes episode " +
					"LEFT OUTER JOIN episode.mediaSources ems " +
					"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
				   	"LEFT OUTER JOIN m.regions region " +
				   	
				   	"WHERE " +
					
				   	"  vss.status='ACTIVE' " +
					"  AND vss.isPackage = :isPackage " +
				   	"  AND vss.contentType.name LIKE '%basic%' " +
				 	"  AND (m.movieDirector.id = :actorId OR actor.id = :actorId) " + 
				 	
				 	// Movie parameters
	                "  AND m.status='ACTIVE' " +
	                "  AND m.rating.level <= :ratingLevel " +
	                "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
	                "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
	                "  AND (region IS NULL OR region.code = :regionCode) " +
	                               
					//mediaSource and episode parameters
					"  AND ( "  +
					"        ( " +
					"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
					"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
					"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
					"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
					"		   AND (ms.videoservers IS NOT EMPTY) " +
					
					"       ) OR " +
					"       ( " +
					"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
					"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
					"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
					"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
					"		   AND (ems.videoservers IS NOT EMPTY) " +
					"       ) " +
					"     ) " +
					
        			""),

        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.findGenresByContentType", 
        		query = "SELECT genre,COUNT(DISTINCT m.id) " + 
        				"FROM VodServiceSpec vss  " +
		                "INNER JOIN vss.movieCategories cat " +
		                "INNER JOIN cat.movies m " +
		                "INNER JOIN m.categories genre  " +
		                "LEFT OUTER JOIN m.mediaSources ms " +
						"LEFT OUTER JOIN ms.bandwidthRating bwr " +
						"LEFT OUTER JOIN m.episodes episode " +
						"LEFT OUTER JOIN episode.mediaSources ems " +
						"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
		                "LEFT OUTER JOIN m.regions region " +
		                "WHERE "  +
		
		                // VodServiceSpecification parameters
		                "  vss.status='ACTIVE' " +
        				"  AND vss.contentType.name=:contentType " +
		                "  AND vss.externalId IN (:vssIds) " +
		                
		                // Movie parameters
		                "  AND m.status='ACTIVE' " +
		                "  AND m.rating.level <= :ratingLevel " +
		                "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
		                "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
		                "  AND (region IS NULL OR region.code = :regionCode) " +
		                               
						//mediaSource and episode parameters
						"  AND ( "  +
						"        ( " +
						"          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
						"          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
						"          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
						"          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
						"		   AND (ms.videoservers IS NOT EMPTY) " +
						
						"       ) OR " +
						"       ( " +
						"          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
						"          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
						"          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
						"          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
						"		   AND (ems.videoservers IS NOT EMPTY) " +
						"       ) " +
						"     ) " +
		                
		                "GROUP BY genre.id,genre.name,genre.categoryType,genre.description,genre.rating,genre.sortOrder,genre.externalId " + 
		                "ORDER BY genre.name"),
        

        // AdvServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.getAdvTrailers", 
        		query = "SELECT movie.advTrailers FROM Movie movie " +
        				"LEFT OUTER JOIN movie.mediaSources ms " +
        				"WHERE " +
        				"  ms.id=:id " +
        				"  AND (ms.videoservers IS NOT EMPTY) " +
        				""),
        
        // VodServiceImpl
        @javax.persistence.NamedQuery(name = "Movie.updateRating", 
        		query = "UPDATE Movie m " +
        				"SET m.pointSumRating = COALESCE(m.pointSumRating , 0) + :points , " +
        				"m.voteCountRating = COALESCE(m.voteCountRating , 0) + 1 " +
        				"WHERE m.id=:id"),
        				
        @javax.persistence.NamedQuery(name = "Movie.getMoviesByCriteria",
        		query =	"SELECT movie FROM Movie movie " +
        				"WHERE movie.id IN (" +
        				
								"SELECT DISTINCT m.id FROM VodServiceSpec vss " +
								"INNER JOIN vss.movieCategories cat " +
								"INNER JOIN cat.movies m " +
								"LEFT OUTER JOIN m.mediaSources ms " +
								"LEFT OUTER JOIN ms.bandwidthRating bwr " +
								"LEFT OUTER JOIN m.episodes episode " +
								"LEFT OUTER JOIN episode.mediaSources ems " +
								"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
		                		"LEFT OUTER JOIN m.regions region " +
								"LEFT OUTER JOIN m.movieActors actor " +
		                		
		                		"WHERE " +
		                		"  vss.status='ACTIVE' " +
		                		"  AND vss.contentType.name=:contentType " +
		                		"  AND vss.externalId IN (:vssIds) " +
		                		
		                		// Movie parameters
		                        "  AND m.id = movie.id " +
		                		"  AND m.status='ACTIVE' " +
		                        "  AND m.rating.level <= :ratingLevel " +
		                        "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
		                        "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
		                        "  AND (region IS NULL OR region.code = :regionCode) " +
		                                       
		                        // mediaSource and episode parameters
		                        "  AND ( "  +
		                        "        ( " +
		                        "          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
		                        "          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
		                        "          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
		                        "          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
		                        "		   AND (ms.videoservers IS NOT EMPTY) " +
		                        
		                        "       ) OR " +
		                        "       ( " +
		                        "          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
		                        "          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
		                        "          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
		                        "          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
		                        "		   AND (ems.videoservers IS NOT EMPTY) " +
		                        "       ) " +
		                        "     ) " +
		                        
		                        // пришлось сделать через IN, так как хибер неправильно генерировал запрос...
		                        "  AND ( " +
		                        "    m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nName[:locale]) LIKE LOWER(:criteria)) " +
		                        "    OR m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nDescription[:locale]) LIKE LOWER(:criteria)) " +
		                        "    OR m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nActors[:locale]) LIKE LOWER(:criteria)) " +
		                        "    OR m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nDirector[:locale]) LIKE LOWER(:criteria)) " +
		                        "    OR actor.id IN (SELECT a.id FROM Actor a WHERE LOWER(a.i18nName[:locale]) LIKE LOWER(:criteria)) " +
		                        "    OR m.movieDirector.id IN (SELECT a.id FROM Actor a WHERE LOWER(a.i18nName[:locale]) LIKE LOWER(:criteria)) " +
		                        "  )" +
                		
                        ") " +
                        
                		"ORDER BY COALESCE(movie.weightRating , 0) DESC , movie.name , movie.id ASC" ) ,
        		
        @javax.persistence.NamedQuery(name = "Movie.getMoviesByCriteriaCount",
        		query =	"SELECT COUNT(DISTINCT m.id) FROM VodServiceSpec vss " +
						"INNER JOIN vss.movieCategories cat " +
						"INNER JOIN cat.movies m " +
						"LEFT OUTER JOIN m.mediaSources ms " +
						"LEFT OUTER JOIN ms.bandwidthRating bwr " +
						"LEFT OUTER JOIN m.episodes episode " +
						"LEFT OUTER JOIN episode.mediaSources ems " +
						"LEFT OUTER JOIN ems.bandwidthRating epbwr " +
                		"LEFT OUTER JOIN m.regions region " +
						"LEFT OUTER JOIN m.movieActors actor " +
                		
                		"WHERE " +
                		"  vss.status='ACTIVE' " +
                		"  AND vss.contentType.name=:contentType " +
                		"  AND vss.externalId IN (:vssIds) " +
                		
                		// Movie parameters
                		"  AND m.status='ACTIVE' " +
                        "  AND m.rating.level <= :ratingLevel " +
                        "  AND (m.licenseExpired IS NULL OR m.licenseExpired >= :now) " +
                        "  AND (m.beginDate IS NULL OR m.beginDate <= :now) " +
                        "  AND (region IS NULL OR region.code = :regionCode) " +
                                       
                        // mediaSource and episode parameters
                        "  AND ( "  +
                        "        ( " +
                        "          (bwr IS NULL OR bwr.level <= :bandwidthLevel) " +
                        "          AND (ms.encryption IS NULL OR ms.encryption='NONE' OR ms.encryption IN (:encryptions)) " +
                        "          AND (ms.encoding IS NULL OR ms.encoding IN (:encodings))  " +
                        "          AND (ms.resolution IS NULL OR ms.resolution IN (:resolutions)) " +
                        "		   AND (ms.videoservers IS NOT EMPTY) " +
                        
                        "       ) OR " +
                        "       ( " +
                        "          (epbwr IS NULL OR epbwr.level <= :bandwidthLevel) " +
                        "          AND (ems.encryption IS NULL OR ems.encryption='NONE' OR ems.encryption IN (:encryptions)) " +
                        "          AND (ems.encoding IS NULL OR ems.encoding IN (:encodings))  " +
                        "          AND (ems.resolution IS NULL OR ems.resolution IN (:resolutions)) " +
                        "		   AND (ems.videoservers IS NOT EMPTY) " +
                        "       ) " +
                        "     ) " +
                        
                        // пришлось сделать через IN, так как хибер неправильно генерировал запрос...
                        "  AND ( " +
                        "    m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nName[:locale]) LIKE LOWER(:criteria)) " +
                        "    OR m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nDescription[:locale]) LIKE LOWER(:criteria)) " +
                        "    OR m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nActors[:locale]) LIKE LOWER(:criteria)) " +
                        "    OR m.id IN (SELECT mov.id FROM Movie mov WHERE LOWER(mov.i18nDirector[:locale]) LIKE LOWER(:criteria)) " +
                        "    OR actor.id IN (SELECT a.id FROM Actor a WHERE LOWER(a.i18nName[:locale]) LIKE LOWER(:criteria)) " +
                        "    OR m.movieDirector.id IN (SELECT a.id FROM Actor a WHERE LOWER(a.i18nName[:locale]) LIKE LOWER(:criteria)) " +
                        "  )")
        

})
public class Movie extends ru.cti.oss.iptv.resource.logical.MediaContent implements java.io.Serializable
{
    
    private static final long serialVersionUID = -3620386098840149390L;
    
    private boolean selected = false;
    
    // ----------- Attribute Definitions ------------
    
    private java.lang.String actors;
    private java.util.Map < java.util.Locale, java.lang.String > i18nActors;
    private java.lang.String director;
    private java.util.Map < java.util.Locale, java.lang.String > i18nDirector;
    private java.lang.String starRating;
    private java.util.Map < java.util.Locale, java.lang.String > i18nStarRating;
    private java.lang.String studio;
    private java.util.Map < java.util.Locale, java.lang.String > i18nStudio;
    private java.util.Date year;
    private java.lang.Long duration;
    private ru.cti.oss.iptv.Image poster = new ru.cti.oss.iptv.Image();
    private java.lang.String audiotype;
    private java.util.Map < java.util.Locale, java.lang.String > i18nCountry;
    private ru.cti.oss.iptv.resource.logical.ScreenFormat screenFormat;
    private java.util.Date licenseExpired;
    private java.util.List < Picture > pictures = new ArrayList < Picture >();
    private java.lang.Integer weightRating = 0;
    private java.lang.Integer pointSumRating = 0;  
    private java.lang.Integer voteCountRating = 0;
    private java.lang.String country;
    private java.util.Date beginDate;
    private java.lang.String tvCode;
    
    // --------- Relationship Definitions -----------
    
    private MovieActor movieDirector;
    private java.util.Set < MovieActor > movieActors = new java.util.HashSet < MovieActor >();
    private java.util.Set < ru.cti.oss.iptv.resource.logical.MediaAsset > previews = new java.util.TreeSet < ru.cti.oss.iptv.resource.logical.MediaAsset >();
    private List < AdvTrailer > advTrailers = new ArrayList < AdvTrailer >();
    private ru.cti.oss.iptv.resource.logical.MovieCategory movieCategory;
    private java.util.Set < Movie > recommendedMovies = new java.util.HashSet < Movie >();
    private java.util.Set < MovieAdditionalMaterial > additionalMaterials = new java.util.HashSet < MovieAdditionalMaterial >();
    private java.util.List < Episode > episodes = new ArrayList < Episode >();
    private java.util.Set < ru.cti.oss.iptv.resource.logical.MulticastChannel > promoTrailers = new java.util.HashSet < ru.cti.oss.iptv.resource.logical.MulticastChannel >();
    private String inAppId;
    
    // ---- Manageable Display Attributes (Transient) -----
    
    // --------------- Constructors -----------------
    
    /**
     * Default empty constructor
     */
    public Movie() {
    }
    
    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param actors Value for the actors property
     * @param director Value for the director property
     * @param starRating Value for the starRating property
     * @param studio Value for the studio property
     * @param year Value for the year property
     * @param duration Value for the duration property
     * @param poster Value for the poster property
     * @param country Value for the country property
     * @param screenFormat Value for the screenFormat property
     * @param licenseExpired Value for the licenseExpired property
     */
    public Movie(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.datatypes.LifeCycleState status,
            java.lang.String actors, java.lang.String director, java.lang.String starRating, java.lang.String studio, java.util.Date year,
            java.lang.Long duration, ru.cti.oss.iptv.Image poster, java.lang.String country,
            ru.cti.oss.iptv.resource.logical.ScreenFormat screenFormat, java.util.Date licenseExpired) {
        super(name, description, status);
        setName(name);
        setDescription(description);
        setStatus(status);
        setActors(actors);
        setDirector(director);
        setStarRating(starRating);
        setStudio(studio);
        setYear(year);
        setDuration(duration);
        setPoster(poster);
        setCountry(country);
        setScreenFormat(screenFormat);
        setLicenseExpired(licenseExpired);
    }
    
    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param name Value for the name property
     * @param description Value for the description property
     * @param status Value for the status property
     * @param actors Value for the actors property
     * @param director Value for the director property
     * @param starRating Value for the starRating property
     * @param studio Value for the studio property
     * @param year Value for the year property
     * @param duration Value for the duration property
     * @param poster Value for the poster property
     * @param country Value for the country property
     * @param screenFormat Value for the screenFormat property
     * @param licenseExpired Value for the licenseExpired property
     * @param previews Value for the previews relation
     * @param movieCategory Value for the movieCategory relation
     */
    public Movie(java.lang.String name, java.lang.String description, ru.cti.oss.cbe.datatypes.LifeCycleState status,
            java.lang.String actors, java.lang.String director, java.lang.String starRating, java.lang.String studio, java.util.Date year,
            java.lang.Long duration, ru.cti.oss.iptv.Image poster, java.lang.String country,
            ru.cti.oss.iptv.resource.logical.ScreenFormat screenFormat, java.util.Date licenseExpired,
            java.util.Set < ru.cti.oss.iptv.resource.logical.MediaAsset > previews,
            ru.cti.oss.iptv.resource.logical.MovieCategory movieCategory) {
        setName(name);
        setDescription(description);
        setStatus(status);
        setActors(actors);
        setDirector(director);
        setStarRating(starRating);
        setStudio(studio);
        setYear(year);
        setDuration(duration);
        setPoster(poster);
        setCountry(country);
        setScreenFormat(screenFormat);
        setLicenseExpired(licenseExpired);
        
        setPreviews(previews);
        setMovieCategory(movieCategory);
    }
    
    // -------- Attribute Accessors ----------
    
    /**
     * Get the actors property.
     * 
     * @return java.lang.String The value of actors
     */
    @javax.persistence.Column(name = "ACTORS", insertable = true, updatable = true)
    public java.lang.String getActors() {
        return actors;
    }
    
    /**
     * Set the actors property.
     * @param value the new value
     */
    public void setActors(java.lang.String value) {
        this.actors = value;
    }
    
    @org.hibernate.annotations.CollectionOfElements
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "movie_i18n_actors", joinColumns = { @javax.persistence.JoinColumn(name = "movie_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nActors() {
        return i18nActors;
    }
    
    public void setI18nActors(java.util.Map < java.util.Locale, java.lang.String > i18nActors) {
        this.i18nActors = i18nActors;
    }
    
    /**
     * Get the director property.
     * 
     * @return java.lang.String The value of director
     */
    @javax.persistence.Column(name = "DIRECTOR", insertable = true, updatable = true)
    public java.lang.String getDirector() {
        return director;
    }
    
    /**
     * Set the director property.
     * @param value the new value
     */
    public void setDirector(java.lang.String value) {
        this.director = value;
    }
    
    @org.hibernate.annotations.CollectionOfElements
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "movie_i18n_director", joinColumns = { @javax.persistence.JoinColumn(name = "movie_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nDirector() {
        return i18nDirector;
    }
    
    public void setI18nDirector(java.util.Map < java.util.Locale, java.lang.String > i18nDirector) {
        this.i18nDirector = i18nDirector;
    }
    
    /**
     * Get the starRating property.
     * 
     * @return java.lang.String The value of starRating
     */
    @javax.persistence.Column(name = "STAR_RATING", insertable = true, updatable = true)
    public java.lang.String getStarRating() {
        return starRating;
    }
    
    /**
     * Set the starRating property.
     * @param value the new value
     */
    public void setStarRating(java.lang.String value) {
        this.starRating = value;
    }
    
    @org.hibernate.annotations.CollectionOfElements
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "movie_i18n_star_rating", joinColumns = { @javax.persistence.JoinColumn(name = "movie_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nStarRating() {
        return i18nStarRating;
    }
    
    public void setI18nStarRating(java.util.Map < java.util.Locale, java.lang.String > i18nStarRating) {
        this.i18nStarRating = i18nStarRating;
    }
    
    /**
     * Get the studio property.
     * 
     * @return java.lang.String The value of studio
     */
    @javax.persistence.Column(name = "STUDIO", insertable = true, updatable = true)
    public java.lang.String getStudio() {
        return studio;
    }
    
    /**
     * Set the studio property.
     * @param value the new value
     */
    public void setStudio(java.lang.String value) {
        this.studio = value;
    }
    
    @org.hibernate.annotations.CollectionOfElements
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "movie_i18n_studio", joinColumns = { @javax.persistence.JoinColumn(name = "movie_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nStudio() {
        return i18nStudio;
    }
    
    public void setI18nStudio(java.util.Map < java.util.Locale, java.lang.String > i18nStudio) {
        this.i18nStudio = i18nStudio;
    }
    
    /**
     * Get the year property.
     * 
     * @return java.util.Date The value of year
     */
    @javax.persistence.Column(name = "YEAR", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public java.util.Date getYear() {
        return year;
    }
    
    /**
     * Set the year property.
     * @param value the new value
     */
    public void setYear(java.util.Date value) {
        this.year = value;
    }
        
    /**
	 * @return the beginDate
	 */
    @javax.persistence.Column(name = "BEGIN_DATE", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public java.util.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
     * Get the duration property.
     * 
     * @return java.lang.Long The value of duration
     */
    @javax.persistence.Column(name = "DURATION", nullable = false, insertable = true, updatable = true)
    public java.lang.Long getDuration() {
        return duration;
    }

	/**
     * Set the duration property.
     * @param value the new value
     */
    public void setDuration(java.lang.Long value) {
        this.duration = value;
    }
    
    /**
     * Get the weightRating property.
     * 
     * @return java.lang.Integer The value of weightRating
     */
    @javax.persistence.Column(name = "WEIGHT_RATING", nullable = true, insertable = true, updatable = true)
    public java.lang.Integer getWeightRating() {
        return weightRating;
    }
    
    /**
     * Set the weightRating property.
     * @param value the new value
     */
    public void setWeightRating(java.lang.Integer value) {
        this.weightRating = value;
    }
    
    /**
     * 
     * @return audiotype
     */
    @javax.persistence.Column(name = "AUDIOTYPE", insertable = true, updatable = true)
    public java.lang.String getAudiotype() {
		return audiotype;
	}
    
    /**
     * 
     * @param audiotype
     */
	public void setAudiotype(java.lang.String audiotype) {
		this.audiotype = audiotype;
	}

	/**
     * Get the poster property.
     * 
     * @return ru.cti.oss.iptv.Image The value of poster
     */
    @javax.persistence.Embedded
    @javax.persistence.AttributeOverrides({
            @javax.persistence.AttributeOverride(name = "width", column = @javax.persistence.Column(name = "POSTER_WIDTH", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "height", column = @javax.persistence.Column(name = "POSTER_HEIGHT", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "size", column = @javax.persistence.Column(name = "POSTER_SIZE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "contentType", column = @javax.persistence.Column(name = "POSTER_CONTENT_TYPE", insertable = true, updatable = true)),
            @javax.persistence.AttributeOverride(name = "data", column = @javax.persistence.Column(name = "POSTER_DATA", insertable = true, updatable = true)) })
    public ru.cti.oss.iptv.Image getPoster() {
        return poster;
    }
    
    /**
     * Get the pictures Collection
     *
     * @return java.util.List<Picture>
     */
    @javax.persistence.OneToMany(mappedBy = "movie", fetch = javax.persistence.FetchType.LAZY)
    public java.util.List < Picture > getPictures() {
        return this.pictures;
    }
    
    /**
     * Set the pictures
     *
     * @param categories
     */
    public void setPictures(List < Picture > pictures) {
        this.pictures = pictures;
    }
    
    /**
     * Get the adv trailers list
     *
     * @return java.util.List<AdvTrailer>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "MOVIES2ADVTRAILERS", joinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "ADVTRAILER_ID", referencedColumnName = "ID") })
    public List < AdvTrailer > getAdvTrailers() {
        return advTrailers;
    }
    
    /**
     * remove adv trailer from collection
     * @param trailer AdvTrailer
     */
    public void removeAdvTrailer(AdvTrailer trailer) {
        if (advTrailers != null) {
            advTrailers.remove(trailer);
        }
    }
    
    /**
     * add adv trailer into collection
     * @param trailer AdvTrailer
     */
    public void addAdvTrailer(AdvTrailer trailer) {
        if (advTrailers == null) {
            advTrailers = new ArrayList < AdvTrailer >();
        }
        advTrailers.add(trailer);
    }
    
    /**
     * Set the advTrailers list property.
     * @param advTrailers List <AdvTrailer>
     */
    public void setAdvTrailers(List < AdvTrailer > advTrailers) {
        this.advTrailers = advTrailers;
    }
    
    /**
     * Set the poster property.
     * @param value the new value
     */
    public void setPoster(ru.cti.oss.iptv.Image value) {
        this.poster = value;
    }
    
    /**
     * Get the country property.
     * 
     * @return java.lang.String The value of country
     */
    @javax.persistence.Column(name = "COUNTRY", insertable = true, updatable = true)
    public java.lang.String getCountry() {
        return country;
    }
    
    /**
     * Set the country property.
     * @param value the new value
     */
    public void setCountry(java.lang.String value) {
        this.country = value;
    }
        
    /**
	 * @return the tvCode
	 */
    @javax.persistence.Column(name = "TV_CODE", insertable = true, updatable = true)
	public java.lang.String getTvCode() {
		return tvCode;
	}

	/**
	 * @param tvCode the tvCode to set
	 */
	public void setTvCode(java.lang.String tvCode) {
		this.tvCode = tvCode;
	}
	
	/**
	 * @return the inAppId
	 */
	@javax.persistence.Column(name = "IN_APP_ID", insertable = true, updatable = true)
	public String getInAppId() {
		return inAppId;
	}

	/**
	 * @param inAppId the inAppId to set
	 */
	public void setInAppId(String inAppId) {
		this.inAppId = inAppId;
	}

	@org.hibernate.annotations.CollectionOfElements
    @org.hibernate.annotations.MapKeyManyToMany
    @javax.persistence.JoinTable(name = "movie_i18n_country", joinColumns = { @javax.persistence.JoinColumn(name = "movie_id", nullable = false) })
    public java.util.Map < java.util.Locale, java.lang.String > getI18nCountry() {
        return i18nCountry;
    }
    
    public void setI18nCountry(java.util.Map < java.util.Locale, java.lang.String > i18nCountry) {
        this.i18nCountry = i18nCountry;
    }
    
    /**
     * Get the screenFormat property.
     * 
     * @return ru.cti.oss.iptv.resource.logical.ScreenFormat The value of screenFormat
     */
    @javax.persistence.Column(name = "SCREEN_FORMAT", insertable = true, updatable = true)
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    public ru.cti.oss.iptv.resource.logical.ScreenFormat getScreenFormat() {
        return screenFormat;
    }
    
    /**
     * Set the screenFormat property.
     * @param value the new value
     */
    public void setScreenFormat(ru.cti.oss.iptv.resource.logical.ScreenFormat value) {
        this.screenFormat = value;
    }
    
    /**
     * Get the licenseExpired property.
     * 
     * @return java.util.Date The value of licenseExpired
     */
    @javax.persistence.Column(name = "LICENSE_EXPIRED", insertable = true, updatable = true)
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public java.util.Date getLicenseExpired() {
        return licenseExpired;
    }
    
    /**
     * Set the licenseExpired property.
     * @param value the new value
     */
    public void setLicenseExpired(java.util.Date value) {
        this.licenseExpired = value;
    }
    
    // ------------- Relations ------------------
    
    /**
     * Get the previews Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.MediaAsset>
     */
    @javax.persistence.OneToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "MOVIES2PREVIEWS", joinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_IDC", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "MEDIA_ASSET_IDC", referencedColumnName = "ID") })
    public java.util.Set < ru.cti.oss.iptv.resource.logical.MediaAsset > getPreviews() {
        return this.previews;
    }
    
    /**
     * Set the previews
     *
     * @param previews
     */
    public void setPreviews(java.util.Set < ru.cti.oss.iptv.resource.logical.MediaAsset > previews) {
        this.previews = previews;
    }
    
    /**
     * Add MediaAsset
     *
     * @param mediaAsset
     */
    
    public void addToPreviews(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset) {
        if (mediaAsset == null)
            return;
        this.getPreviews().add(mediaAsset);
    }
    
    /**
     * Remove MediaAsset
     *
     * @param mediaAsset
     */
    
    public void removeFromPreviews(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset) {
        if (mediaAsset == null)
            return;
        this.getPreviews().remove(mediaAsset);
    }
    
    /**
     * Get the promoTrailers Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.MediaAsset>
     */
    @javax.persistence.OneToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "MOVIES2PROMOTRAILERS37", joinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_IDC", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "MULTICAST_CHANNELS_IDC", referencedColumnName = "ID") })
    public java.util.Set < ru.cti.oss.iptv.resource.logical.MulticastChannel > getPromoTrailers() {
        return this.promoTrailers;
    }
    
    /**
     * Set the promoTrailers
     *
     * @param promoTrailers
     */
    public void setPromoTrailers(java.util.Set < ru.cti.oss.iptv.resource.logical.MulticastChannel > promoTrailers) {
        this.promoTrailers = promoTrailers;
    }
    
    /**
     * Add MediaAsset
     *
     * @param mediaAsset
     */
    
    public void addToPromoTrailers(ru.cti.oss.iptv.resource.logical.MulticastChannel mediaAsset) {
        if (mediaAsset == null)
            return;
        this.getPromoTrailers().add(mediaAsset);
    }
    
    /**
     * Remove MediaAsset
     *
     * @param mediaAsset
     */
    
    public void removeFromPromoTrailers(ru.cti.oss.iptv.resource.logical.MediaAsset mediaAsset) {
        if (mediaAsset == null)
            return;
        this.getPromoTrailers().remove(mediaAsset);
    }
    
    /**
     * Get the movieCategory
     *
     * @return ru.cti.oss.iptv.resource.logical.MovieCategory
     */
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinColumn(name = "MOVIECATEGORY")
    public ru.cti.oss.iptv.resource.logical.MovieCategory getMovieCategory() {
        return this.movieCategory;
    }
    
    /**
     * Set the movieCategory
     *
     * @param movieCategory
     */
    public void setMovieCategory(ru.cti.oss.iptv.resource.logical.MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }
    
    /**
     * Get the recommended movies
     *
     * @return java.util.Set<Movie>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "MOVIES2RECOMMENDED", joinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "RECOMMENDED_ID", referencedColumnName = "ID") })
    public java.util.Set < Movie > getRecommendedMovies() {
        return recommendedMovies;
    }
    
    public void setRecommendedMovies(java.util.Set < Movie > movies) {
        this.recommendedMovies = movies;
    }
    
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.LAZY, optional = true)
    @javax.persistence.JoinColumn(name = "MVDIRECTOR")
    public MovieActor getMovieDirector() {
        return movieDirector;
    }
    
    public void setMovieDirector(MovieActor movieDirector) {
        this.movieDirector = movieDirector;
    }
    
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "MOVIES2ACTORS", joinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID") })
    public java.util.Set < MovieActor > getMovieActors() {
        return movieActors;
    }
    
    public void setMovieActors(java.util.Set < MovieActor > movieActors) {
        this.movieActors = movieActors;
    }
    
    public void addToMovieActors(MovieActor movieActor) {
        if (movieActor == null)
            return;
        this.getMovieActors().add(movieActor);
    }
    
    /* (non-Javadoc)
     * @see ru.cti.oss.iptv.resource.logical.MediaContent#getMediaSource()
     */
    @Deprecated
    @javax.persistence.Transient
    public ru.cti.oss.iptv.resource.logical.MediaSource getMediaSource() {
        return MediaContentToDefaultMediaSourceHelper.getDefaultMediaAsset(this.getMediaSources());
    }
    
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @javax.persistence.JoinTable(name = "MOVIES2ADDITIONAL", joinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "ADDITIONAL_ID", referencedColumnName = "ID") })
    public java.util.Set < MovieAdditionalMaterial > getAdditionalMaterials() {
        return additionalMaterials;
    }
    
    public void setAdditionalMaterials(java.util.Set < MovieAdditionalMaterial > additionalMaterials) {
        this.additionalMaterials = additionalMaterials;
    }
    
    /**
     * Get the ratingPointSum property.
     * 
     * @return java.lang.Integer The value of ratingPointSum
     */
    @javax.persistence.Column(name = "POINT_SUM_RATING", nullable = true, insertable = true, updatable = true)
    public java.lang.Integer getPointSumRating() {
        return pointSumRating;
    }
    
    /**
     * Set the ratingPointSum property.
     * @param value the new value
     */
    public void setPointSumRating(java.lang.Integer value) {
        this.pointSumRating = value;
    }
    
    /**
     * Get the ratingPointSum property.
     * 
     * @return java.lang.Integer The value of ratingPointSum
     */
    @javax.persistence.Column(name = "VOTE_COUNT_RATING", nullable = true, insertable = true, updatable = true)
    public java.lang.Integer getVoteCountRating() {
        return voteCountRating;
    }
    
    /**
     * Set the ratingPointSum property.
     * @param value the new value
     */
    public void setVoteCountRating(java.lang.Integer value) {
        this.voteCountRating = value;
    }
    
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
        if (!(object instanceof Movie)) {
            return false;
        }
        final Movie that = (Movie) object;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Movie(=");
        sb.append(super.toString());
        sb.append("actors: ");
        sb.append(getActors());
        sb.append(", director: ");
        sb.append(getDirector());
        sb.append(", starRating: ");
        sb.append(getStarRating());
        sb.append(", studio: ");
        sb.append(getStudio());
        sb.append(", year: ");
        sb.append(getYear());
        sb.append(", duration: ");
        sb.append(getDuration());
        sb.append(", poster: ");
        if (getPoster() != null)
            sb.append(getPoster().toString());
        else
            sb.append("null");
        sb.append(", country: ");
        sb.append(getCountry());
        sb.append(", screenFormat: ");
        sb.append(getScreenFormat());
        sb.append(")");
        return sb.toString();
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    @Transient
    public boolean isSelected() {
        return selected;
    }
    
    public void setEpisodes(java.util.List < Episode > episodes) {
        this.episodes = episodes;
    }
    
    /**
     * Get the mediaAssets Collection
     *
     * @return java.util.Set<ru.cti.oss.iptv.resource.logical.MediaAsset>
     */
    @javax.persistence.ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @org.hibernate.annotations.BatchSize(size = 100)
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @javax.persistence.JoinTable(name = "movies2episodes", joinColumns = { @javax.persistence.JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "EPISODE_ID", referencedColumnName = "ID", unique = false) })
    public java.util.List < Episode > getEpisodes() {
        return episodes;
    }
    
    public void addToEpisodes(Episode episode) {
        if (episode == null)
            return;
        this.getEpisodes().add(episode);
    }
    
}