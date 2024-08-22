package ai.shreds.domain; 
  
 import java.sql.Timestamp; 
 import java.util.List; 
  
 /** 
  * DomainCategoryRepositoryPort is an interface for interacting with Category entities in the database. 
  */ 
 public interface DomainCategoryRepositoryPort { 
     /** 
      * Retrieves all Category entities from the database. 
      * @return a list of Category entities 
      */ 
     List<DomainCategoryEntity> findAll() throws Exception; 
  
     /** 
      * Retrieves a Category entity by its unique identifier. 
      * @param id the unique identifier of the category 
      * @return the Category entity 
      */ 
     DomainCategoryEntity findById(Long id) throws Exception; 
  
     /** 
      * Searches for Category entities that contain the specified name (supports partial matches). 
      * @param name the name to search for 
      * @return a list of Category entities that match the search criteria 
      */ 
     List<DomainCategoryEntity> findByNameContaining(String name) throws Exception; 
  
     /** 
      * Searches for Category entities by their parent category ID. 
      * @param categoryId the parent category ID 
      * @return a list of Category entities that match the search criteria 
      */ 
     List<DomainCategoryEntity> findByCategoryId(Long categoryId) throws Exception; 
  
     /** 
      * Searches for Category entities created after a specific date. 
      * @param createdAfter the timestamp to search from 
      * @return a list of Category entities that match the search criteria 
      */ 
     List<DomainCategoryEntity> findByCreatedAtAfter(Timestamp createdAfter) throws Exception; 
  
     /** 
      * Searches for Category entities updated after a specific date. 
      * @param updatedAfter the timestamp to search from 
      * @return a list of Category entities that match the search criteria 
      */ 
     List<DomainCategoryEntity> findByUpdatedAtAfter(Timestamp updatedAfter) throws Exception; 
 } 
  
 // Implementation Note: Use Lombok annotations for getter and setter methods.