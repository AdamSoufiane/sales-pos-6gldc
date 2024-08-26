package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.SharedSupplierDTO; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 import java.time.LocalDateTime; 
 import java.util.List; 
 import java.util.Optional; 
  
 @Service 
 public class DomainSupplierService implements DomainSupplierRepositoryPort { 
  
     private final DomainSupplierRepositoryPort repository; 
  
     @Autowired 
     public DomainSupplierService(DomainSupplierRepositoryPort repository) { 
         this.repository = repository; 
     } 
  
     /** 
      * Retrieves all suppliers based on the provided filters. 
      * 
      * @param params The filtering criteria. 
      * @return A list of DomainSupplierEntity. 
      */ 
     @Override 
     public List<DomainSupplierEntity> findAll(SharedRequestParams params) { 
         if (params == null) { 
             throw new IllegalArgumentException("Params cannot be null"); 
         } 
         try { 
             return repository.findAll(params); 
         } catch (IllegalArgumentException e) { 
             throw new DomainSupplierServiceException("Invalid parameters: " + e.getMessage(), e); 
         } catch (Exception e) { 
             throw new DomainSupplierServiceException("Error retrieving suppliers: " + e.getMessage(), e); 
         } 
     } 
  
     /** 
      * Retrieves a supplier by its unique ID. 
      * 
      * @param id The unique ID of the supplier. 
      * @return The DomainSupplierEntity. 
      */ 
     @Override 
     public DomainSupplierEntity findById(Long id) { 
         try { 
             Optional<DomainSupplierEntity> supplier = Optional.ofNullable(repository.findById(id)); 
             return supplier.orElseThrow(() -> new SupplierNotFoundException("Supplier not found with ID: " + id)); 
         } catch (SupplierNotFoundException e) { 
             throw e; 
         } catch (Exception e) { 
             throw new DomainSupplierServiceException("Error retrieving supplier: " + e.getMessage(), e); 
         } 
     } 
 } 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class DomainSupplierEntity { 
     private Integer id; 
     private String name; 
     private String contact_info; 
     private String address; 
     private LocalDateTime created_at; 
     private LocalDateTime updated_at; 
 } 
  
 class DomainSupplierServiceException extends RuntimeException { 
     public DomainSupplierServiceException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class SupplierNotFoundException extends RuntimeException { 
     public SupplierNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 @ControllerAdvice 
 class GlobalExceptionHandler { 
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 }