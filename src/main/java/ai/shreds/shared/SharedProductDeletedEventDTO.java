package ai.shreds.shared; 
  
 import lombok.Data; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) representing a ProductDeleted event. 
  */ 
 @Data 
 public class SharedProductDeletedEventDTO { 
     /** 
      * Type of the event, should be 'ProductDeleted'. 
      */ 
     private String eventType; 
  
     /** 
      * Unique identifier of the product being deleted. 
      */ 
     private UUID productId; 
 }