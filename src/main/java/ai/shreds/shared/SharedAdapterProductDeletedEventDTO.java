package ai.shreds.shared.dto; 
  
 import com.fasterxml.jackson.annotation.JsonProperty; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) for representing a 'ProductDeleted' event consumed from Kafka. 
  * This DTO contains the event type and the unique identifier of the product that has been deleted. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedAdapterProductDeletedEventDTO { 
  
     /** 
      * The type of the event, in this case 'ProductDeleted'. 
      */ 
     @JsonProperty("eventType") 
     private String eventType; 
  
     /** 
      * The unique identifier of the product that has been deleted. 
      */ 
     @JsonProperty("productId") 
     private UUID productId; 
 }