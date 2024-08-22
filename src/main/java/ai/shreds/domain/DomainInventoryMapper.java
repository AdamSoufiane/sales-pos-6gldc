package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedAlertDTO; 
 import ai.shreds.shared.SharedInventoryDomainEntity; 
 import ai.shreds.shared.SharedProductDomainEntity; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.stereotype.Component; 
 import java.time.Instant; 
  
 /** 
  * Mapper class to convert between domain entities and DTOs. 
  * Note: Use Lombok annotations for getters and setters to reduce boilerplate code. 
  */ 
 @Component 
 public class DomainInventoryMapper { 
  
     @Value("${inventory.defaultQuantity:0}") 
     private int defaultQuantity; // Default initial quantity 
  
     @Value("${inventory.defaultQteAlert:10}") 
     private int defaultQteAlert; // Default alert threshold 
  
     /** 
      * Converts SharedProductDomainEntity to SharedInventoryDomainEntity. 
      * @param product the product domain entity 
      * @return the inventory domain entity 
      */ 
     public SharedInventoryDomainEntity toInventoryDomainEntity(SharedProductDomainEntity product) { 
         if (product == null) { 
             throw new IllegalArgumentException("Product cannot be null"); 
         } 
         SharedInventoryDomainEntity inventory = new SharedInventoryDomainEntity(); 
         inventory.setProductId(product.getProductId()); 
         inventory.setQuantity(defaultQuantity); 
         inventory.setQteAlert(defaultQteAlert); 
         inventory.setLastUpdated(Instant.now()); 
         return inventory; 
     } 
  
     /** 
      * Converts SharedProductDomainEntity to SharedAlertDTO. 
      * @param product the product domain entity 
      * @return the alert DTO 
      */ 
     public SharedAlertDTO toAlertDTO(SharedProductDomainEntity product) { 
         if (product == null) { 
             throw new IllegalArgumentException("Product cannot be null"); 
         } 
         SharedAlertDTO alert = new SharedAlertDTO(); 
         alert.setProductId(product.getProductId()); 
         alert.setAlertMessage("Inventory level is below the threshold for product: " + product.getName()); 
         alert.setThreshold(defaultQteAlert); 
         alert.setCurrentQuantity(defaultQuantity); 
         return alert; 
     } 
 } 
 