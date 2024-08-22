package ai.shreds.shared.dto; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object for inventory alert responses. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedAdapterInventoryAlertResponseDTO { 
     /** 
      * Unique identifier for the product. 
      */ 
     private UUID productId; 
  
     /** 
      * Alert message indicating the inventory issue. 
      */ 
     private String alertMessage; 
  
     /** 
      * Threshold quantity at which the alert is triggered. 
      */ 
     private Integer threshold; 
  
     /** 
      * Current quantity of the product in stock. 
      */ 
     private Integer currentQuantity; 
 } 
 