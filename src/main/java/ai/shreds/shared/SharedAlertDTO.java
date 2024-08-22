package ai.shreds.shared.dto; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
  
 /** 
  * Represents an alert data transfer object used to notify stakeholders when inventory levels fall below a specified threshold. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedAlertDTO { 
     private UUID productId; 
     private String alertMessage; 
     private Integer threshold; 
     private Integer currentQuantity; 
 } 
 