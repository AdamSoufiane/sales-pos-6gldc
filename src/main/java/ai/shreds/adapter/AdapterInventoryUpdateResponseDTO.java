package ai.shreds.adapter.dto; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
 import java.sql.Timestamp; 
  
 /** 
  * Data Transfer Object for Inventory Update Response. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterInventoryUpdateResponseDTO { 
     /** 
      * Unique identifier for the product. 
      */ 
     private UUID productId; 
  
     /** 
      * Quantity of the product in stock. 
      */ 
     private Integer quantity; 
  
     /** 
      * The threshold quantity at which an alert should be triggered. 
      */ 
     private Integer qteAlert; 
  
     /** 
      * Timestamp when the inventory was last updated. 
      */ 
     private Timestamp lastUpdated; 
 }