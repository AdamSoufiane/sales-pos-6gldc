package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.NotNull; 
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object for ProductUpdated event. 
  * This DTO is used to transfer product update information between different layers of the application. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedAdapterProductUpdatedEventDTO { 
     /** 
      * The type of the event, must not be null. 
      */ 
     @NotNull 
     private String eventType; 
  
     /** 
      * The unique identifier of the product, must not be null. 
      */ 
     @NotNull 
     private UUID productId; 
  
     /** 
      * The name of the product, must not be null. 
      */ 
     @NotNull 
     private String name; 
  
     /** 
      * The description of the product. 
      */ 
     private String description; 
  
     /** 
      * The price of the product. 
      */ 
     private BigDecimal price; 
  
     /** 
      * The category ID to which the product belongs. 
      */ 
     private UUID categoryId; 
  
     /** 
      * The timestamp when the product was created. 
      */ 
     private Timestamp createdAt; 
  
     /** 
      * The timestamp when the product was last updated. 
      */ 
     private Timestamp updatedAt; 
 } 
 