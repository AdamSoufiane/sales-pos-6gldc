package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCategoryUpdateRequest; 
 import ai.shreds.adapter.AdapterCategoryResponse; 
 import java.util.UUID; 
  
 /** 
  * Interface for updating an existing category. 
  */ 
 @FunctionalInterface 
 public interface ApplicationUpdateCategoryInputPort { 
     /** 
      * Updates an existing category with the provided details. 
      * 
      * @param id      the UUID of the category to be updated 
      * @param request the new details for the category 
      * @return the updated category details 
      */ 
     AdapterCategoryResponse updateCategory(UUID id, AdapterCategoryUpdateRequest request); 
 } 
 