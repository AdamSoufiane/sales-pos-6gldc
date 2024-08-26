package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object (DTO) for filtering supplier queries. 
  * This class encapsulates the optional filter criteria for querying suppliers such as name, contact information, and address. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedRequestParams { 
     private String name; 
     private String contact_info; 
     private String address; 
 } 
  
 // Implementation Note: Use Lombok annotations for getters and setters.