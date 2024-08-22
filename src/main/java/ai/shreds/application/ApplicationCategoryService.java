package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCategoryCreateRequest; 
 import ai.shreds.adapter.AdapterCategoryUpdateRequest; 
 import ai.shreds.adapter.AdapterCategoryResponse; 
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import ai.shreds.domain.DomainCategoryServicePort; 
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
 import lombok.Getter; 
 import lombok.Setter; 
  
 import java.time.LocalDateTime; 
 import java.util.UUID; 
  
 @Slf4j 
 @Service 
 @Getter 
 @Setter 
 public class ApplicationCategoryService implements ApplicationCreateCategoryInputPort, ApplicationUpdateCategoryInputPort, ApplicationDeleteCategoryInputPort { 
  
     private final DomainCategoryRepositoryPort categoryRepository; 
     private final DomainCategoryServicePort categoryService; 
  
     @Autowired 
     public ApplicationCategoryService(DomainCategoryRepositoryPort categoryRepository, DomainCategoryServicePort categoryService) { 
         this.categoryRepository = categoryRepository; 
         this.categoryService = categoryService; 
     } 
  
     @Override 
     @Transactional 
     public AdapterCategoryResponse createCategory(AdapterCategoryCreateRequest request) { 
         try { 
             categoryService.validateCategoryData(request); 
             if (request.getCategory_id() != null) { 
                 categoryService.checkParentCategoryExists(request.getCategory_id()); 
             } 
             if (categoryRepository.existsByNameAndParentId(request.getName(), request.getCategory_id())) { 
                 throw new IllegalArgumentException("Category name must be unique within its parent category"); 
             } 
             DomainCategoryEntity category = new DomainCategoryEntity(); 
             category.setId(UUID.randomUUID()); 
             category.setName(request.getName()); 
             category.setDescription(request.getDescription()); 
             category.setCategory_id(request.getCategory_id()); 
             category.setCreated_at(LocalDateTime.now()); 
             category.setUpdated_at(LocalDateTime.now()); 
             categoryRepository.save(category); 
             log.info("Category created successfully: {}", category.getId()); 
             return new AdapterCategoryResponse( 
                     category.getId(), 
                     category.getName(), 
                     category.getDescription(), 
                     category.getCategory_id(), 
                     category.getCreated_at(), 
                     category.getUpdated_at() 
             ); 
         } catch (IllegalArgumentException e) { 
             log.error("Validation error: {}", e.getMessage()); 
             throw new RuntimeException(e.getMessage()); 
         } catch (Exception e) { 
             log.error("Error creating category: {}", e.getMessage()); 
             throw new RuntimeException("Failed to create category. Please check your input data."); 
         } 
     } 
  
     @Override 
     @Transactional 
     public AdapterCategoryResponse updateCategory(UUID id, AdapterCategoryUpdateRequest request) { 
         try { 
             DomainCategoryEntity existingCategory = categoryRepository.findById(id); 
             if (existingCategory == null) { 
                 throw new IllegalArgumentException("Category not found"); 
             } 
             categoryService.validateCategoryData(request); 
             if (request.getCategory_id() != null) { 
                 categoryService.checkParentCategoryExists(request.getCategory_id()); 
             } 
             if (categoryRepository.existsByNameAndParentId(request.getName(), request.getCategory_id())) { 
                 throw new IllegalArgumentException("Category name must be unique within its parent category"); 
             } 
             existingCategory.setName(request.getName()); 
             existingCategory.setDescription(request.getDescription()); 
             existingCategory.setCategory_id(request.getCategory_id()); 
             existingCategory.setUpdated_at(LocalDateTime.now()); 
             categoryRepository.save(existingCategory); 
             log.info("Category updated successfully: {}", existingCategory.getId()); 
             return new AdapterCategoryResponse( 
                     existingCategory.getId(), 
                     existingCategory.getName(), 
                     existingCategory.getDescription(), 
                     existingCategory.getCategory_id(), 
                     existingCategory.getCreated_at(), 
                     existingCategory.getUpdated_at() 
             ); 
         } catch (IllegalArgumentException e) { 
             log.error("Validation error: {}", e.getMessage()); 
             throw new RuntimeException(e.getMessage()); 
         } catch (Exception e) { 
             log.error("Error updating category: {}", e.getMessage()); 
             throw new RuntimeException("Failed to update category. Please check your input data."); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void deleteCategory(UUID id) { 
         try { 
             DomainCategoryEntity existingCategory = categoryRepository.findById(id); 
             if (existingCategory == null) { 
                 throw new IllegalArgumentException("Category not found"); 
             } 
             // Check if the category has subcategories 
             if (categoryRepository.hasSubcategories(id)) { 
                 throw new IllegalArgumentException("Category cannot be deleted as it has subcategories"); 
             } 
             categoryRepository.deleteById(id); 
             log.info("Category deleted successfully: {}", id); 
         } catch (IllegalArgumentException e) { 
             log.error("Validation error: {}", e.getMessage()); 
             throw new RuntimeException(e.getMessage()); 
         } catch (Exception e) { 
             log.error("Error deleting category: {}", e.getMessage()); 
             throw new RuntimeException("Failed to delete category. Please ensure the category exists and has no subcategories."); 
         } 
     } 
 }