package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.DecimalMin; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Size; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 /** 
  * Implementation Note: 
  * Ensure Lombok annotations (@Data, @NoArgsConstructor, @AllArgsConstructor) are used 
  * for generating getters, setters, constructors, and other utility methods. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterProductCreateRequest { 
     @NotBlank(message = "Product name must not be empty") 
     @Size(max = 255, message = "Product name must be less than 255 characters") 
     private String name; 
  
     @Size(max = 1000, message = "Product description must be less than 1000 characters") 
     private String description; 
  
     @NotNull(message = "Product price must not be null") 
     @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be a positive decimal value") 
     private BigDecimal price; 
  
     @NotNull(message = "Category ID must not be null") 
     private UUID categoryId; 
 }