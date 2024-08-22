package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
  
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class AdapterInventoryAlertResponseDTO { 
     private UUID productId; 
     private String alertMessage; 
     private Integer threshold; 
     private Integer currentQuantity; 
 }