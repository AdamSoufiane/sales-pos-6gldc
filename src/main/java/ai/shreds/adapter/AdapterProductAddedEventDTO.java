package ai.shreds.adapter; 
  
 import com.fasterxml.jackson.annotation.JsonFormat; 
 import com.fasterxml.jackson.annotation.JsonProperty; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.math.BigDecimal; 
 import java.time.LocalDateTime; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) for the ProductAdded event consumed from Kafka. 
  * This class encapsulates all necessary fields that are part of the ProductAdded event. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterProductAddedEventDTO { 
  
     @JsonProperty("eventType") 
     private String eventType; 
  
     @JsonProperty("productId") 
     private UUID productId; 
  
     @JsonProperty("name") 
     private String name; 
  
     @JsonProperty("description") 
     private String description; 
  
     @JsonProperty("price") 
     private BigDecimal price; 
  
     @JsonProperty("categoryId") 
     private UUID categoryId; 
  
     @JsonProperty("createdAt") 
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") 
     private LocalDateTime createdAt; 
  
     @JsonProperty("updatedAt") 
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") 
     private LocalDateTime updatedAt; 
 }