package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object for capturing query parameters for filtering suppliers. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterSupplierRequestParams { 
     private String name; 
     private String contact_info; 
     private String address; 
 }