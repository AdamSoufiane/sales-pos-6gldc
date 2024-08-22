package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.NotNull; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) for Category. 
  * Represents category data when interacting with external services or other layers of the application. 
  */ 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterCategoryDTO { 
     @NotNull 
     private UUID id; 
     @NotNull 
     private String name; 
     private String description; 
     private UUID category_id; 
     private Timestamp created_at; 
     private Timestamp updated_at; 
 }