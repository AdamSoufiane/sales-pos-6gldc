package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.util.UUID; 
  
 /** 
  * Data Transfer Object for alert information in the domain layer. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class DomainAlertDTO { 
     /** 
      * Unique identifier for the product. 
      */ 
     private UUID productId; 
     /** 
      * Alert message to be sent. 
      */ 
     private String alertMessage; 
     /** 
      * Threshold quantity at which the alert is triggered. 
      */ 
     private int threshold; 
     /** 
      * Current quantity of the product in stock. 
      */ 
     private int currentQuantity; 
 }