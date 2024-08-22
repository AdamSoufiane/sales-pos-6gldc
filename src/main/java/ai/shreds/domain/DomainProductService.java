package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import java.util.Optional; 
 import java.util.UUID; 
  
 @Slf4j 
 @RequiredArgsConstructor 
 public class DomainProductService implements DomainProductServicePort { 
  
     private final DomainProductRepositoryPort productRepository; 
     private final DomainCategoryRepositoryPort categoryRepository; 
  
     /** 
      * Saves a product entity after validation. 
      * @param product the product entity to save 
      */ 
     @Override 
     public void save(DomainProductEntity product) { 
         validateProductData(product); 
         checkCategoryExists(product.getCategoryId()); 
         productRepository.save(product); 
     } 
  
     /** 
      * Finds a product entity by its ID. 
      * @param id the UUID of the product 
      * @return an Optional containing the found product entity 
      */ 
     @Override 
     public Optional<DomainProductEntity> findById(UUID id) { 
         return productRepository.findById(id); 
     } 
  
     /** 
      * Deletes a product entity by its ID. 
      * @param id the UUID of the product 
      */ 
     @Override 
     public void deleteById(UUID id) { 
         Optional<DomainProductEntity> product = productRepository.findById(id); 
         if (product.isPresent()) { 
             productRepository.deleteById(id); 
         } else { 
             throw new ProductNotFoundException("Product not found"); 
         } 
     } 
  
     private void validateProductData(DomainProductEntity product) { 
         if (product.getName() == null || product.getName().isEmpty()) { 
             throw new InvalidProductDataException("Product name must not be empty"); 
         } 
         if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new InvalidProductDataException("Product price must be a positive value"); 
         } 
     } 
  
     private void checkCategoryExists(UUID categoryId) { 
         if (!categoryRepository.findById(categoryId).isPresent()) { 
             throw new CategoryNotFoundException("Category does not exist"); 
         } 
     } 
 } 
  
 package ai.shreds.domain.exception; 
  
 public class InvalidProductDataException extends RuntimeException { 
     public InvalidProductDataException(String message) { 
         super(message); 
     } 
 } 
  
 package ai.shreds.domain.exception; 
  
 public class ProductNotFoundException extends RuntimeException { 
     public ProductNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 package ai.shreds.domain.exception; 
  
 public class CategoryNotFoundException extends RuntimeException { 
     public CategoryNotFoundException(String message) { 
         super(message); 
     } 
 } 
 