package ai.shreds.shared.dto; 
  
 import com.fasterxml.jackson.annotation.JsonProperty; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object (DTO) for transferring inventory update response data. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedAdapterInventoryUpdateResponseDTO { 
  
     /** 
      * Unique identifier for the product. 
      */ 
     @JsonProperty("productId") 
     private UUID productId; 
  
     /** 
      * Quantity of the product in stock. 
      */ 
     @JsonProperty("quantity") 
     private Integer quantity; 
  
     /** 
      * The threshold quantity at which an alert should be triggered. 
      */ 
     @JsonProperty("qteAlert") 
     private Integer qteAlert; 
  
     /** 
      * Timestamp when the inventory was last updated. 
      */ 
     @JsonProperty("lastUpdated") 
     private Timestamp lastUpdated; 
 }