package ai.shreds.application; 
  
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import ai.shreds.shared.SharedAlertDTO; 
 import ai.shreds.shared.SharedProductAddedEventDTO; 
 import ai.shreds.shared.SharedProductDeletedEventDTO; 
 import ai.shreds.shared.SharedProductUpdatedEventDTO; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
 import java.util.UUID; 
  
 @Service 
 @RequiredArgsConstructor 
 @Slf4j 
 public class ApplicationInventoryServiceImpl implements ApplicationInventoryServiceInputPort { 
  
     private final DomainInventoryRepositoryPort domainInventoryRepositoryPort; 
     private final ApplicationAlertServiceOutputPort applicationAlertServiceOutputPort; 
  
     @Override 
     @Transactional 
     public void processProductAddedEvent(SharedProductAddedEventDTO event) { 
         try { 
             log.info("Processing ProductAdded event: {}", event); 
             // Convert DTO to domain entity 
             var productDomainEntity = domainInventoryRepositoryPort.toDomainEntity(event); 
              
             // Initialize or update inventory 
             domainInventoryRepositoryPort.initializeOrUpdateInventory(productDomainEntity); 
              
             // Check and trigger alert if needed 
             checkAndTriggerAlert(productDomainEntity.getProductId()); 
         } catch (Exception e) { 
             log.error("Error processing ProductAdded event: {}", event, e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void processProductUpdatedEvent(SharedProductUpdatedEventDTO event) { 
         try { 
             log.info("Processing ProductUpdated event: {}", event); 
             // Convert DTO to domain entity 
             var productDomainEntity = domainInventoryRepositoryPort.toDomainEntity(event); 
              
             // Adjust inventory for updated product 
             domainInventoryRepositoryPort.adjustInventoryForUpdatedProduct(productDomainEntity); 
              
             // Check and trigger alert if needed 
             checkAndTriggerAlert(productDomainEntity.getProductId()); 
         } catch (Exception e) { 
             log.error("Error processing ProductUpdated event: {}", event, e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void processProductDeletedEvent(SharedProductDeletedEventDTO event) { 
         try { 
             log.info("Processing ProductDeleted event: {}", event); 
             // Remove inventory for deleted product 
             domainInventoryRepositoryPort.removeInventoryForDeletedProduct(event.getProductId()); 
         } catch (Exception e) { 
             log.error("Error processing ProductDeleted event: {}", event, e); 
         } 
     } 
  
     private void checkAndTriggerAlert(UUID productId) { 
         try { 
             var inventory = domainInventoryRepositoryPort.findByProductId(productId); 
             if (inventory.getQuantity() < inventory.getQteAlert()) { 
                 var alert = domainInventoryRepositoryPort.toAlertDTO(inventory); 
                 applicationAlertServiceOutputPort.sendAlert(alert); 
             } 
         } catch (Exception e) { 
             log.error("Error checking and triggering alert for productId: {}", productId, e); 
         } 
     } 
 } 
 