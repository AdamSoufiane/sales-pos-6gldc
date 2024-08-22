package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationProductServicePort; 
 import ai.shreds.application.ApplicationCategoryServicePort; 
 import ai.shreds.shared.AdapterProductCreateRequest; 
 import ai.shreds.shared.AdapterProductCreateResponse; 
 import ai.shreds.shared.AdapterProductUpdateRequest; 
 import ai.shreds.shared.AdapterProductUpdateResponse; 
 import ai.shreds.shared.AdapterProductDeleteResponse; 
 import ai.shreds.shared.AdapterCategoryDTO; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.dao.DataIntegrityViolationException; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.*; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 @RestController 
 @RequestMapping("/products") 
 public class AdapterProductController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class); 
     private final ApplicationProductServicePort productService; 
     private final ApplicationCategoryServicePort categoryService; 
  
     @Autowired 
     public AdapterProductController(ApplicationProductServicePort productService, ApplicationCategoryServicePort categoryService) { 
         this.productService = productService; 
         this.categoryService = categoryService; 
     } 
  
     @PostMapping 
     public ResponseEntity<AdapterProductCreateResponse> createProduct(@RequestBody AdapterProductCreateRequest request) { 
         validateProductRequest(request); 
         AdapterCategoryDTO category = categoryService.getCategoryById(request.getCategoryId()); 
         if (category == null) { 
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
         } 
         logger.info("Creating product with name: {}", request.getName()); 
         AdapterProductCreateResponse response = productService.createProduct(request); 
         // Produce Kafka event here 
         // kafkaProducer.produceEvent("ProductAdded", response); 
         return new ResponseEntity<>(response, HttpStatus.CREATED); 
     } 
  
     @PutMapping("/{id}") 
     public ResponseEntity<AdapterProductUpdateResponse> updateProduct(@PathVariable UUID id, @RequestBody AdapterProductUpdateRequest request) { 
         validateProductRequest(request); 
         AdapterCategoryDTO category = categoryService.getCategoryById(request.getCategoryId()); 
         if (category == null) { 
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
         } 
         logger.info("Updating product with ID: {}", id); 
         AdapterProductUpdateResponse response = productService.updateProduct(id, request); 
         // Produce Kafka event here 
         // kafkaProducer.produceEvent("ProductUpdated", response); 
         return new ResponseEntity<>(response, HttpStatus.OK); 
     } 
  
     @DeleteMapping("/{id}") 
     public ResponseEntity<AdapterProductDeleteResponse> deleteProduct(@PathVariable UUID id) { 
         logger.info("Deleting product with ID: {}", id); 
         AdapterProductDeleteResponse response = productService.deleteProduct(id); 
         // Produce Kafka event here 
         // kafkaProducer.produceEvent("ProductDeleted", response); 
         return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); 
     } 
  
     @ExceptionHandler(DataIntegrityViolationException.class) 
     public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) { 
         logger.error("Data integrity violation: {}", e.getMessage()); 
         return ResponseEntity.status(400).body("Invalid product data"); 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         logger.error("An error occurred: {}", e.getMessage()); 
         return ResponseEntity.status(500).body(e.getMessage()); 
     } 
  
     private void validateProductRequest(Object request) { 
         // Implement validation logic here 
         if (request instanceof AdapterProductCreateRequest) { 
             AdapterProductCreateRequest createRequest = (AdapterProductCreateRequest) request; 
             if (createRequest.getName() == null || createRequest.getName().isEmpty()) { 
                 throw new IllegalArgumentException("Product name must not be empty"); 
             } 
             if (createRequest.getPrice() == null || createRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
                 throw new IllegalArgumentException("Product price must be a positive value"); 
             } 
             if (createRequest.getCategoryId() == null) { 
                 throw new IllegalArgumentException("Category ID must not be null"); 
             } 
         } else if (request instanceof AdapterProductUpdateRequest) { 
             AdapterProductUpdateRequest updateRequest = (AdapterProductUpdateRequest) request; 
             if (updateRequest.getName() == null || updateRequest.getName().isEmpty()) { 
                 throw new IllegalArgumentException("Product name must not be empty"); 
             } 
             if (updateRequest.getPrice() == null || updateRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
                 throw new IllegalArgumentException("Product price must be a positive value"); 
             } 
             if (updateRequest.getCategoryId() == null) { 
                 throw new IllegalArgumentException("Category ID must not be null"); 
             } 
         } 
     } 
 } 
 