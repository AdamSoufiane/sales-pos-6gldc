package ai.shreds.domain; 
  
 import ai.shreds.adapter.AdapterCategoryCreateRequest; 
 import ai.shreds.application.ApplicationCategoryException; 
 import ai.shreds.shared.SharedUUID; 
 import lombok.Getter; 
 import lombok.Setter; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
  
 @Service 
 @Getter 
 @Setter 
 public class DomainCategoryService implements DomainCategoryServicePort { 
  
     private final DomainCategoryRepositoryPort categoryRepository; 
  
     @Autowired 
     public DomainCategoryService(DomainCategoryRepositoryPort categoryRepository) { 
         this.categoryRepository = categoryRepository; 
     } 
  
     @Override 
     public void validateCategoryData(AdapterCategoryCreateRequest request) { 
         if (request.getName() == null || request.getName().isEmpty()) { 
             throw new ApplicationCategoryException("Category name is required."); 
         } 
  
         if (request.getCategory_id() != null) { 
             SharedUUID parentId = new SharedUUID(request.getCategory_id().toString()); 
             checkParentCategoryExists(parentId); 
         } 
  
         if (!isCategoryNameUniqueWithinParent(request.getName(), request.getCategory_id())) { 
             throw new ApplicationCategoryException("Category name must be unique within its parent category."); 
         } 
     } 
  
     @Override 
     public void checkParentCategoryExists(SharedUUID id) { 
         DomainCategoryEntity parentCategory = categoryRepository.findById(id); 
         if (parentCategory == null) { 
             throw new ApplicationCategoryException("Parent category does not exist."); 
         } 
     } 
  
     private boolean isCategoryNameUniqueWithinParent(String name, SharedUUID parentId) { 
         // Implement logic to check if the category name is unique within its parent category 
         return categoryRepository.findByNameAndParentId(name, parentId) == null; 
     } 
  
     private void validateCategoryDeletion(SharedUUID id) { 
         // Implement logic to ensure that a category can only be deleted if it does not have any subcategories 
         if (!categoryRepository.findSubcategoriesByParentId(id).isEmpty()) { 
             throw new ApplicationCategoryException("Category cannot be deleted as it has subcategories."); 
         } 
     } 
 } 
 