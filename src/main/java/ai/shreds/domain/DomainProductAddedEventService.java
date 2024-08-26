package ai.shreds.domain; 
  
 import ai.shreds.application.ApplicationProductAddedException; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
  
 @Slf4j 
 @Service 
 @RequiredArgsConstructor 
 public class DomainProductAddedEventService implements DomainProductAddedEventPort { 
     private final DomainInventoryRepositoryPort repositoryPort; 
  
     @Override 
     @Transactional 
     public DomainProductAddedEventResponse processEvent(DomainProductAddedEvent event) { 
         try { 
             log.info("Processing ProductAdded event: {}", event); 
             // Validate the event data 
             validateEvent(event); 
  
             // Update the inventory 
             updateInventory(event); 
  
             // Return success response 
             return new DomainProductAddedEventResponse("ProductAdded event consumed and processed successfully"); 
         } catch (ValidationException e) { 
             log.error("Validation error: {}", e.getMessage()); 
             throw new ApplicationProductAddedException("Error processing ProductAdded event", e); 
         } catch (Exception e) { 
             log.error("Unexpected error: {}", e.getMessage()); 
             throw new ApplicationProductAddedException("Error processing ProductAdded event", e); 
         } 
     } 
  
     private void validateEvent(DomainProductAddedEvent event) throws ValidationException { 
         if (event.getProductId() == null || event.getProductId().isEmpty()) { 
             throw new ValidationException("Product ID is required"); 
         } 
         if (event.getInitialQuantity() <= 0) { 
             throw new ValidationException("Initial quantity must be a positive integer"); 
         } 
         if (event.getAlertQuantity() > event.getInitialQuantity()) { 
             throw new ValidationException("Alert quantity must be less than or equal to initial quantity"); 
         } 
         if (event.getWarehouseLocation() == null || event.getWarehouseLocation().isEmpty()) { 
             throw new ValidationException("Warehouse location is required"); 
         } 
     } 
  
     private void updateInventory(DomainProductAddedEvent event) { 
         DomainInventoryEntity inventoryEntity = new DomainInventoryEntity( 
                 event.getProductId(), 
                 event.getInitialQuantity(), 
                 event.getCreationTime(), 
                 event.getAlertQuantity(), 
                 event.getWarehouseLocation() 
         ); 
         repositoryPort.save(inventoryEntity); 
     } 
 } 
  
 class ValidationException extends RuntimeException { 
     public ValidationException(String message) { 
         super(message); 
     } 
 }