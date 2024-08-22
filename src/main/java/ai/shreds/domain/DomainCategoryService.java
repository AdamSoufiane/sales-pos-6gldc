package ai.shreds.domain; 
  
 import ai.shreds.shared.AdapterCategoryRequestParams; 
 import ai.shreds.shared.DomainCategoryEntity; 
 import org.springframework.data.domain.PageRequest; 
 import org.springframework.data.domain.Pageable; 
 import org.springframework.stereotype.Service; 
 import lombok.RequiredArgsConstructor; 
  
 import java.util.List; 
 import java.util.Optional; 
  
 @Service 
 @RequiredArgsConstructor 
 public class DomainCategoryService { 
  
     private final DomainCategoryRepositoryPort categoryRepository; 
  
     /** 
      * Fetches all categories from the repository. 
      * @return List of DomainCategoryEntity objects. 
      */ 
     public List<DomainCategoryEntity> getAllCategories() { 
         try { 
             return categoryRepository.findAll(); 
         } catch (Exception e) { 
             throw new DomainCategoryServiceException("Error retrieving all categories", e); 
         } 
     } 
  
     /** 
      * Retrieves a specific category by its unique identifier. 
      * @param id Unique identifier of the category. 
      * @return DomainCategoryEntity object. 
      */ 
     public DomainCategoryEntity getCategoryById(Long id) { 
         try { 
             Optional<DomainCategoryEntity> category = categoryRepository.findById(id); 
             if (category.isPresent()) { 
                 return category.get(); 
             } else { 
                 throw new CategoryNotFoundException("Category not found with id: " + id); 
             } 
         } catch (Exception e) { 
             throw new DomainCategoryServiceException("Error retrieving category by ID", e); 
         } 
     } 
  
     /** 
      * Searches for categories based on provided search criteria. 
      * @param params AdapterCategoryRequestParams object containing search parameters. 
      * @return List of matching DomainCategoryEntity objects. 
      */ 
     public List<DomainCategoryEntity> searchCategories(AdapterCategoryRequestParams params) { 
         try { 
             Pageable pageable = PageRequest.of(params.getPage(), params.getSize()); 
             List<DomainCategoryEntity> results = categoryRepository.findAll(pageable).getContent(); 
             if (params.getName() != null) { 
                 results.retainAll(categoryRepository.findByNameContaining(params.getName())); 
             } 
             if (params.getCategoryId() != null) { 
                 results.retainAll(categoryRepository.findByCategoryId(params.getCategoryId())); 
             } 
             if (params.getCreatedAfter() != null) { 
                 results.retainAll(categoryRepository.findByCreatedAtAfter(params.getCreatedAfter())); 
             } 
             if (params.getUpdatedAfter() != null) { 
                 results.retainAll(categoryRepository.findByUpdatedAtAfter(params.getUpdatedAfter())); 
             } 
             return results; 
         } catch (Exception e) { 
             throw new DomainCategoryServiceException("Error searching categories", e); 
         } 
     } 
 } 
  
 class DomainCategoryServiceException extends RuntimeException { 
     public DomainCategoryServiceException(String message) { 
         super(message); 
     } 
  
     public DomainCategoryServiceException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class CategoryNotFoundException extends RuntimeException { 
     public CategoryNotFoundException(String message) { 
         super(message); 
     } 
 }