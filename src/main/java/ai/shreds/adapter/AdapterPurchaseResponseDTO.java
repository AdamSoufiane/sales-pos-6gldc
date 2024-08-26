package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterPurchaseDataDTO; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object for the response of a purchase transaction. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterPurchaseResponseDTO { 
     /** 
      * Status code of the response. 
      */ 
     private int status_code; 
  
     /** 
      * Data of the purchase transaction. 
      */ 
     private AdapterPurchaseDataDTO data; 
  
     /** 
      * Error message if any. 
      */ 
     private String error; 
 }