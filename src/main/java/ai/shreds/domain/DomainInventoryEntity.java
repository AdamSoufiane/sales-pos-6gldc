package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.ToString; 
 import java.time.LocalDateTime; 
  
 /** 
  * Represents an inventory record for a product. 
  */ 
 @Getter 
 @ToString 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Builder 
 public class DomainInventoryEntity { 
     private String productId; 
     private int initialQuantity; 
     private LocalDateTime creationTime; 
     private int alertQuantity; 
     private String warehouseLocation; 
  
     /** 
      * Validates the inventory entity fields. 
      * 
      * @throws IllegalArgumentException if any field is invalid 
      */ 
     public void validate() { 
         if (productId == null || productId.isEmpty()) { 
             throw new IllegalArgumentException("Product ID must not be null or empty"); 
         } 
         if (initialQuantity <= 0) { 
             throw new IllegalArgumentException("Initial quantity must be a positive integer"); 
         } 
         if (alertQuantity < 0 || alertQuantity > initialQuantity) { 
             throw new IllegalArgumentException("Alert quantity must be between 0 and initial quantity"); 
         } 
         if (warehouseLocation == null || warehouseLocation.isEmpty()) { 
             throw new IllegalArgumentException("Warehouse location must not be null or empty"); 
         } 
     } 
  
     /** 
      * Constructor to enforce validation during object creation. 
      */ 
     public DomainInventoryEntity(String productId, int initialQuantity, LocalDateTime creationTime, int alertQuantity, String warehouseLocation) { 
         this.productId = productId; 
         this.initialQuantity = initialQuantity; 
         this.creationTime = creationTime; 
         this.alertQuantity = alertQuantity; 
         this.warehouseLocation = warehouseLocation; 
         validate(); 
     } 
 } 
 