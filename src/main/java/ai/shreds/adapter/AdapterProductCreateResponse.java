package ai.shreds.adapter; 
  
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 /** 
  * Data Transfer Object for the response of creating a product. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterProductCreateResponse { 
  
     /** 
      * Unique identifier for the product. 
      */ 
     private UUID productId; 
  
     /** 
      * Name of the product. 
      */ 
     private String name; 
  
     /** 
      * Description of the product. 
      */ 
     private String description; 
  
     /** 
      * Price of the product. 
      */ 
     private BigDecimal price; 
  
     /** 
      * Identifier for the category to which the product belongs. 
      */ 
     private UUID categoryId; 
  
     /** 
      * Name of the category to which the product belongs. 
      */ 
     private String categoryName; 
  
     /** 
      * Timestamp when the product was created. 
      */ 
     private Timestamp createdAt; 
  
     /** 
      * Timestamp when the product was last updated. 
      */ 
     private Timestamp updatedAt; 
 }