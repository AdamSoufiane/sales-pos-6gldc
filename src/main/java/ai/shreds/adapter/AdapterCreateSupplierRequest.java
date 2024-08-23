package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.validation.constraints.NotEmpty; 
 import javax.validation.constraints.NotNull; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterCreateSupplierRequest { 
     @NotNull 
     @NotEmpty 
     private String name; 
  
     @NotNull 
     @NotEmpty 
     private String contact_info; 
  
     @NotNull 
     @NotEmpty 
     private String address; 
 }