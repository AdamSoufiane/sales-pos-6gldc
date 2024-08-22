package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCategoryDTO; 
 import ai.shreds.adapter.AdapterKafkaProducer; 
 import ai.shreds.adapter.AdapterProductCreateRequest; 
 import ai.shreds.adapter.AdapterProductCreateResponse; 
 import ai.shreds.adapter.AdapterProductDeleteResponse; 
 import ai.shreds.adapter.AdapterProductUpdateRequest; 
 import ai.shreds.adapter.AdapterProductUpdateResponse; 
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductServicePort; 
 import ai.shreds.domain.DomainCategoryServicePort; 
 import ai.shreds.domain.ProductLogic; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import java.time.LocalDateTime; 
 import java.util.Optional; 
 import java.util.UUID; 
 import java.math.BigDecimal; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 public class ApplicationProductServiceImpl implements ApplicationProductServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationProductServiceImpl.class); 
  
     @Autowired 
     private DomainProductServicePort domainProductService; 
  
     @Autowired 
     private ApplicationCategoryServicePort applicationCategoryService; 
  
     @Autowired 
     private AdapterKafkaProducer kafkaProducer; 
  
     @Autowired 
     private ProductLogic productLogic; 
  
     @Override 
     public AdapterProductCreateResponse createProduct(AdapterProductCreateRequest params) { 
         logger.info("Creating product with name: {}", params.getName()); 
  
         // Validate product data 
         productLogic.validateProductData(params); 
  
         // Fetch category details 
         AdapterCategoryDTO category; 
         try { 
             category = applicationCategoryService.getCategoryById(params.getCategoryId()); 
         } catch (Exception e) { 
             logger.error("Failed to fetch category details", e); 
             throw new RuntimeException("Failed to fetch category details", e); 
         } 
  
         // Create a new product entity 
         DomainProductEntity product = new DomainProductEntity(UUID.randomUUID(), params.getName(), params.getDescription(), params.getPrice(), params.getCategoryId(), LocalDateTime.now(), LocalDateTime.now()); 
  
         // Save product entity to the database 
         domainProductService.save(product); 
  
         // Produce 'ProductAdded' event to Kafka 
         kafkaProducer.produceEvent("ProductAdded", product); 
  
         // Return response with created product details 
         return product.toAdapterProductCreateResponse(); 
     } 
  
     @Override 
     public AdapterProductUpdateResponse updateProduct(UUID id, AdapterProductUpdateRequest params) { 
         logger.info("Updating product with ID: {}", id); 
  
         // Validate product data 
         productLogic.validateProductData(params); 
  
         // Fetch existing product by ID 
         Optional<DomainProductEntity> existingProductOpt = domainProductService.findById(id); 
         if (!existingProductOpt.isPresent()) { 
             logger.error("Product not found with ID: {}", id); 
             throw new RuntimeException("Product not found"); 
         } 
         DomainProductEntity existingProduct = existingProductOpt.get(); 
  
         // Fetch updated category details 
         AdapterCategoryDTO category; 
         try { 
             category = applicationCategoryService.getCategoryById(params.getCategoryId()); 
         } catch (Exception e) { 
             logger.error("Failed to fetch category details", e); 
             throw new RuntimeException("Failed to fetch category details", e); 
         } 
  
         // Update existing product entity with new data 
         existingProduct.setName(params.getName()); 
         existingProduct.setDescription(params.getDescription()); 
         existingProduct.setPrice(params.getPrice()); 
         existingProduct.setCategoryId(params.getCategoryId()); 
         existingProduct.setUpdatedAt(LocalDateTime.now()); 
  
         // Save updated product entity to the database 
         domainProductService.save(existingProduct); 
  
         // Produce 'ProductUpdated' event to Kafka 
         kafkaProducer.produceEvent("ProductUpdated", existingProduct); 
  
         // Return response with updated product details 
         return existingProduct.toAdapterProductUpdateResponse(); 
     } 
  
     @Override 
     public AdapterProductDeleteResponse deleteProduct(UUID id) { 
         logger.info("Deleting product with ID: {}", id); 
  
         // Fetch existing product by ID 
         Optional<DomainProductEntity> existingProductOpt = domainProductService.findById(id); 
         if (!existingProductOpt.isPresent()) { 
             logger.error("Product not found with ID: {}", id); 
             throw new RuntimeException("Product not found"); 
         } 
         DomainProductEntity existingProduct = existingProductOpt.get(); 
  
         // Delete product entity from the database 
         domainProductService.deleteById(id); 
  
         // Produce 'ProductDeleted' event to Kafka 
         kafkaProducer.produceEvent("ProductDeleted", existingProduct); 
  
         // Return response indicating successful deletion 
         return new AdapterProductDeleteResponse(); 
     } 
 }