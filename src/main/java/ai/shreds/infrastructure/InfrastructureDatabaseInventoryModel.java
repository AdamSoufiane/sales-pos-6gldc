package ai.shreds.infrastructure; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import org.springframework.data.annotation.Id; 
 import org.springframework.data.mongodb.core.mapping.Document; 
  
 import java.time.LocalDateTime; 
  
 /** 
  * Represents the database model for the Inventory entity. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 @Document(collection = "Inventory") 
 public class InfrastructureDatabaseInventoryModel { 
     /** 
      * Unique identifier for the newly added product. 
      */ 
     @Id 
     private String productId; 
  
     /** 
      * Initial stock quantity of the newly added product. 
      */ 
     private Integer initialQuantity; 
  
     /** 
      * Timestamp when the product was added to the inventory. 
      */ 
     private LocalDateTime creationTime; 
  
     /** 
      * The quantity threshold at which an alert should be triggered. 
      */ 
     private Integer alertQuantity; 
  
     /** 
      * The default warehouse location where the product is stored. 
      */ 
     private String warehouseLocation; 
 } 
 