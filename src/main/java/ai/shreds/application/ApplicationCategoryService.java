package ai.shreds.application; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryServicePort; 
 import ai.shreds.shared.AdapterCategoryDTO; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.stereotype.Service; 
  
 import java.util.Optional; 
 import java.util.UUID; 
  
 /** 
  * Service implementation for handling category-related operations. 
  */ 
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationCategoryService implements ApplicationCategoryServicePort { 
  
     private final DomainCategoryServicePort domainCategoryService; 
  
     /** 
      * Fetches category details by ID. 
      * 
      * @param id the UUID of the category 
      * @return the category details as AdapterCategoryDTO 
      */ 
     @Override 
     public ResponseEntity<AdapterCategoryDTO> getCategoryById(UUID id) { 
         Optional<DomainCategoryEntity> categoryEntityOptional = domainCategoryService.findById(id); 
         if (categoryEntityOptional.isPresent()) { 
             DomainCategoryEntity categoryEntity = categoryEntityOptional.get(); 
             return new ResponseEntity<>(categoryEntity.toAdapterCategoryDTO(), HttpStatus.OK); 
         } else { 
             // Handle the case where the category is not found 
             return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
         } 
     } 
 } 
  
 class CategoryNotFoundException extends RuntimeException { 
     public CategoryNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.http.ResponseEntity; 
  
 @ControllerAdvice 
 public class GlobalExceptionHandler { 
  
     @ExceptionHandler(CategoryNotFoundException.class) 
     public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) { 
         return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); 
     } 
  
     // Other exception handlers can be added here 
 }