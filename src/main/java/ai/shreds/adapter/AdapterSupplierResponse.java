package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedSupplierDTO; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * AdapterSupplierResponse encapsulates the response data for supplier-related operations. 
  * It includes status code, supplier data, and error message if any. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterSupplierResponse { 
     /** 
      * The HTTP status code of the response. 
      */ 
     private int status_code; 
     /** 
      * The data of the supplier in the response. 
      */ 
     private SharedSupplierDTO data; 
     /** 
      * The error message if there is any error. 
      */ 
     private String error; 
 }