package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedAdapterProductAddedEventDTO; 
 import ai.shreds.shared.SharedAdapterProductUpdatedEventDTO; 
 import ai.shreds.shared.SharedAdapterProductDeletedEventDTO; 
 import ai.shreds.application.ApplicationInventoryServiceInputPort; 
 import ai.shreds.shared.SharedAdapterInventoryMapper; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.kafka.annotation.KafkaListener; 
 import org.springframework.stereotype.Component; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Component 
 @RequiredArgsConstructor 
 @Slf4j 
 public class AdapterKafkaConsumer { 
  
     private final ApplicationInventoryServiceInputPort inventoryService; 
     private final SharedAdapterInventoryMapper inventoryMapper; 
  
     @KafkaListener(topics = "product-added", groupId = "inventory-group") 
     public void processProductAddedEvent(SharedAdapterProductAddedEventDTO event) { 
         try { 
             validateProductAddedEvent(event); 
             log.info("Validated ProductAddedEvent for productId: {}", event.getProductId()); 
             inventoryService.processProductAddedEvent(inventoryMapper.toDomain(event)); 
             log.info("Successfully processed ProductAddedEvent for productId: {}", event.getProductId()); 
         } catch (Exception e) { 
             log.error("Error processing ProductAddedEvent: {}", e.getMessage(), e); 
             // Implement retry mechanism or other handling logic here 
         } 
     } 
  
     @KafkaListener(topics = "product-updated", groupId = "inventory-group") 
     public void processProductUpdatedEvent(SharedAdapterProductUpdatedEventDTO event) { 
         try { 
             validateProductUpdatedEvent(event); 
             log.info("Validated ProductUpdatedEvent for productId: {}", event.getProductId()); 
             inventoryService.processProductUpdatedEvent(inventoryMapper.toDomain(event)); 
             log.info("Successfully processed ProductUpdatedEvent for productId: {}", event.getProductId()); 
         } catch (Exception e) { 
             log.error("Error processing ProductUpdatedEvent: {}", e.getMessage(), e); 
             // Implement retry mechanism or other handling logic here 
         } 
     } 
  
     @KafkaListener(topics = "product-deleted", groupId = "inventory-group") 
     public void processProductDeletedEvent(SharedAdapterProductDeletedEventDTO event) { 
         try { 
             validateProductDeletedEvent(event); 
             log.info("Validated ProductDeletedEvent for productId: {}", event.getProductId()); 
             inventoryService.processProductDeletedEvent(inventoryMapper.toDomain(event)); 
             log.info("Successfully processed ProductDeletedEvent for productId: {}", event.getProductId()); 
         } catch (Exception e) { 
             log.error("Error processing ProductDeletedEvent: {}", e.getMessage(), e); 
             // Implement retry mechanism or other handling logic here 
         } 
     } 
  
     private void validateProductAddedEvent(SharedAdapterProductAddedEventDTO event) { 
         // Add validation logic for ProductAddedEvent 
         if (event.getProductId() == null || event.getName() == null || event.getPrice() == null) { 
             throw new IllegalArgumentException("Invalid ProductAddedEvent: Missing required fields"); 
         } 
     } 
  
     private void validateProductUpdatedEvent(SharedAdapterProductUpdatedEventDTO event) { 
         // Add validation logic for ProductUpdatedEvent 
         if (event.getProductId() == null || event.getName() == null || event.getPrice() == null) { 
             throw new IllegalArgumentException("Invalid ProductUpdatedEvent: Missing required fields"); 
         } 
     } 
  
     private void validateProductDeletedEvent(SharedAdapterProductDeletedEventDTO event) { 
         // Add validation logic for ProductDeletedEvent 
         if (event.getProductId() == null) { 
             throw new IllegalArgumentException("Invalid ProductDeletedEvent: Missing productId"); 
         } 
     } 
 } 
 