package ai.shreds.application; 
  
 import ai.shreds.shared.DomainCategoryEntity; 
 import java.util.List; 
 import java.util.Map; 
  
 /** 
  * Service port for category-related operations. 
  */ 
 public interface ApplicationCategoryServicePort { 
  
     /** 
      * Fetches all categories from the system. 
      * 
      * @return a list of DomainCategoryEntity representing all categories. 
      */ 
     List<DomainCategoryEntity> getAllCategories(); 
  
     /** 
      * Fetches a specific category by its unique identifier. 
      * 
      * @param id the unique identifier of the category. 
      * @return a DomainCategoryEntity representing the category with the specified id. 
      */ 
     DomainCategoryEntity getCategoryById(Long id); 
  
     /** 
      * Searches for categories based on provided search criteria. 
      * 
      * @param query a map containing search criteria such as name, parent category ID, creation date, and update date. 
      * @return a list of DomainCategoryEntity that match the search criteria. 
      */ 
     List<DomainCategoryEntity> searchCategories(Map<String, String> query); 
 } 
 