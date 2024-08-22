package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) representing the event of a product being deleted. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterProductDeletedEventDTO { 
     /** 
      * The type of the event, in this case, it will always be 'ProductDeleted'. 
      */ 
     private String eventType; 
  
     /** 
      * The unique identifier of the product that has been deleted. 
      */ 
     private UUID productId; 
 }