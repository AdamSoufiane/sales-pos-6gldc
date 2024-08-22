package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 @Getter 
 @AllArgsConstructor 
 @ToString 
 public class SharedAdapterProductAddedEventDTO { 
     private final String eventType; 
     private final UUID productId; 
     private final String name; 
     private final String description; 
     private final BigDecimal price; 
     private final UUID categoryId; 
     private final Timestamp createdAt; 
     private final Timestamp updatedAt; 
 } 
 // Lombok annotations are used for getters and constructors. 
 // The class should be immutable.