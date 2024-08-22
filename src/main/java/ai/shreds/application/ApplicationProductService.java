package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterProductCreateRequest; 
 import ai.shreds.adapter.AdapterProductCreateResponse; 
 import ai.shreds.adapter.AdapterProductUpdateRequest; 
 import ai.shreds.adapter.AdapterProductUpdateResponse; 
 import ai.shreds.adapter.AdapterProductDeleteResponse; 
 import ai.shreds.adapter.AdapterCategoryClient; 
 import ai.shreds.adapter.AdapterKafkaProducer; 
 import ai.shreds.shared.AdapterCategoryDTO; 
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductServicePort; 
 import ai.shreds.domain.DomainCategoryServicePort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import java.util.Optional; 
 import java.util.UUID; 
 import java.sql.Timestamp; 
 import java.math.BigDecimal; 
 import lombok.Getter; 
 import lombok.Setter; 
  
 /** 
  * Service implementation for managing products. 
  */ 
 @Service 
 @Getter 
 @Setter 
 public class ApplicationProductService implements ApplicationProductServicePort { 
  
     @Autowired 
     private ApplicationCategoryServicePort applicationCategoryService; 
  
     @Autowired 
     private DomainProductServicePort domainProductService; 
  
     @Autowired 
     private AdapterKafkaProducer kafkaProducer; 
  
     /** 
      * Creates a new product. 
      * 
      * @param params the product creation request parameters 
      * @return the created product response 
      */ 
     @Override 
     public AdapterProductCreateResponse createProduct(AdapterProductCreateRequest params) { 
         // Validate product data 
         ProductLogic.validateProductData(params); 
  
         // Fetch category details 
         AdapterCategoryDTO category = applicationCategoryService.getCategoryById(params.getCategoryId()); 
         if (category == null) { 
             throw new RuntimeException("Category not found"); 
         } 
  
         // Create DomainProductEntity 
         DomainProductEntity product = new DomainProductEntity(UUID.randomUUID(), params.getName(), params.getDescription(), params.getPrice(), params.getCategoryId(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())); 
  
         // Save product entity 
         domainProductService.save(product); 
  
         // Publish ProductAdded event 
         kafkaProducer.produceEvent("ProductAdded", product); 
  
         // Return response 
         return product.toAdapterProductCreateResponse(); 
     } 
  
     /** 
      * Updates an existing product. 
      * 
      * @param id the product ID 
      * @param params the product update request parameters 
      * @return the updated product response 
      */ 
     @Override 
     public AdapterProductUpdateResponse updateProduct(UUID id, AdapterProductUpdateRequest params) { 
         // Validate product data 
         ProductLogic.validateProductData(params); 
  
         // Fetch category details 
         AdapterCategoryDTO category = applicationCategoryService.getCategoryById(params.getCategoryId()); 
         if (category == null) { 
             throw new RuntimeException("Category not found"); 
         } 
  
         // Find existing product 
         Optional<DomainProductEntity> existingProductOpt = domainProductService.findById(id); 
         if (!existingProductOpt.isPresent()) { 
             throw new RuntimeException("Product not found"); 
         } 
         DomainProductEntity existingProduct = existingProductOpt.get(); 
  
         // Update product entity 
         existingProduct.setName(params.getName()); 
         existingProduct.setDescription(params.getDescription()); 
         existingProduct.setPrice(params.getPrice()); 
         existingProduct.setCategoryId(params.getCategoryId()); 
         existingProduct.setUpdatedAt(new Timestamp(System.currentTimeMillis())); 
  
         // Save updated product entity 
         domainProductService.save(existingProduct); 
  
         // Publish ProductUpdated event 
         kafkaProducer.produceEvent("ProductUpdated", existingProduct); 
  
         // Return response 
         return existingProduct.toAdapterProductUpdateResponse(); 
     } 
  
     /** 
      * Deletes a product. 
      * 
      * @param id the product ID 
      * @return the delete product response 
      */ 
     @Override 
     public AdapterProductDeleteResponse deleteProduct(UUID id) { 
         // Find existing product 
         Optional<DomainProductEntity> existingProductOpt = domainProductService.findById(id); 
         if (!existingProductOpt.isPresent()) { 
             throw new RuntimeException("Product not found"); 
         } 
  
         // Delete product entity 
         domainProductService.deleteById(id); 
  
         // Publish ProductDeleted event 
         kafkaProducer.produceEvent("ProductDeleted", id); 
  
         // Return response 
         return new AdapterProductDeleteResponse(); 
     } 
 } 
  
 /** 
  * Utility class for product-related logic. 
  */ 
 class ProductLogic { 
     public static void validateProductData(AdapterProductCreateRequest params) { 
         if (params.getName() == null || params.getName().isEmpty()) { 
             throw new RuntimeException("Product name must not be empty"); 
         } 
         if (params.getPrice() == null || params.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new RuntimeException("Product price must be a positive value"); 
         } 
         if (params.getCategoryId() == null) { 
             throw new RuntimeException("Category ID must not be null"); 
         } 
     } 
  
     public static void validateProductData(AdapterProductUpdateRequest params) { 
         if (params.getName() == null || params.getName().isEmpty()) { 
             throw new RuntimeException("Product name must not be empty"); 
         } 
         if (params.getPrice() == null || params.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new RuntimeException("Product price must be a positive value"); 
         } 
         if (params.getCategoryId() == null) { 
             throw new RuntimeException("Category ID must not be null"); 
         } 
     } 
 }