package ai.shreds.application; 
  
 import java.util.UUID; 
  
 /** 
  * ApplicationDeleteCategoryInputPort is an interface for deleting a category by its ID. 
  * 
  * Business Rules: 
  * 1. A category can only be deleted if it does not have any subcategories. 
  * 2. If the category does not exist, an appropriate exception should be thrown. 
  */ 
 public interface ApplicationDeleteCategoryInputPort { 
     /** 
      * Deletes a category by its ID. 
      * 
      * @param id the UUID of the category to be deleted 
      * @throws CategoryNotFoundException if the category does not exist 
      * @throws CategoryHasSubcategoriesException if the category has subcategories 
      */ 
     void deleteCategory(UUID id) throws CategoryNotFoundException, CategoryHasSubcategoriesException; 
 } 
  
 // Implementation Note: Consider using Lombok annotations for getter and setter methods if needed.