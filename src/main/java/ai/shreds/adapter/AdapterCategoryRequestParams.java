package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.Min; 
 import javax.validation.constraints.Size; 
 import java.sql.Timestamp; 
  
 /** 
  * Request parameters for searching categories. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterCategoryRequestParams { 
     /** 
      * Name of the category (supports partial matches). 
      */ 
     @Size(max = 255) 
     private String name; 
      
     /** 
      * ID of the parent category. 
      */ 
     @Min(1) 
     private Long categoryId; 
      
     /** 
      * Timestamp to search for categories created after this date. 
      */ 
     private Timestamp createdAfter; 
      
     /** 
      * Timestamp to search for categories updated after this date. 
      */ 
     private Timestamp updatedAfter; 
 }