package ai.shreds.shared; 
  
 import ai.shreds.domain.SharedProductDomainEntity; 
 import ai.shreds.domain.SharedInventoryDomainEntity; 
 import ai.shreds.domain.SharedAlertDTO; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import java.util.UUID; 
  
 @Data 
 @NoArgsConstructor 
 public class SharedDomainInventoryMapper { 
  
     /** 
      * Converts a SharedProductDomainEntity to a SharedInventoryDomainEntity. 
      * @param productDomainEntity the product domain entity 
      * @return the inventory domain entity 
      */ 
     public SharedInventoryDomainEntity toInventoryDomainEntity(SharedProductDomainEntity productDomainEntity) { 
         if (productDomainEntity == null) { 
             return null; 
         } 
         SharedInventoryDomainEntity inventoryDomainEntity = new SharedInventoryDomainEntity(); 
         inventoryDomainEntity.setProductId(productDomainEntity.getProductId()); 
         inventoryDomainEntity.setQuantity(0); // Default quantity 
         inventoryDomainEntity.setQteAlert(10); // Default alert threshold 
         inventoryDomainEntity.setLastUpdated(productDomainEntity.getUpdatedAt()); 
         return inventoryDomainEntity; 
     } 
  
     /** 
      * Converts a SharedProductDomainEntity to a SharedAlertDTO. 
      * @param productDomainEntity the product domain entity 
      * @return the alert DTO 
      */ 
     public SharedAlertDTO toAlertDTO(SharedProductDomainEntity productDomainEntity) { 
         if (productDomainEntity == null) { 
             return null; 
         } 
         SharedAlertDTO alertDTO = new SharedAlertDTO(); 
         alertDTO.setProductId(productDomainEntity.getProductId()); 
         alertDTO.setAlertMessage("Inventory level is below threshold!"); // Example alert message 
         alertDTO.setThreshold(10); // Example threshold value 
         alertDTO.setCurrentQuantity(0); // This should be set based on actual inventory data 
         return alertDTO; 
     } 
 } 
 