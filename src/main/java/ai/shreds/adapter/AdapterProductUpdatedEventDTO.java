package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) for the ProductUpdated event consumed from Kafka. 
  * This class represents the structure of the ProductUpdated event and is used 
  * to transfer data between different layers of the application, ensuring that 
  * the structure of the ProductUpdated event is maintained consistently throughout the system. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterProductUpdatedEventDTO { 
     private String eventType; 
     private UUID productId; 
     private String name; 
     private String description; 
     private BigDecimal price; 
     private UUID categoryId; 
     private Timestamp createdAt; 
     private Timestamp updatedAt; 
 }