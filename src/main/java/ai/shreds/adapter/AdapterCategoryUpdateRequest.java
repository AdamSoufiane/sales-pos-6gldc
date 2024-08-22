package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.Size; 
 import java.util.UUID; 
  
 /** 
  * AdapterCategoryUpdateRequest is a request parameter object used for updating an existing category. 
  * It contains fields for the category name, description, and parent category ID. 
  * The class includes validation annotations to ensure that the input data meets the business rules. 
  * Specifically, the 'name' field is required. The 'category_id' field should be a valid UUID if provided. 
  *  
  * Note: Use Lombok annotations for getters, setters, and constructors. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterCategoryUpdateRequest { 
     @NotBlank(message = "Category name is required.") 
     @Size(max = 255, message = "Category name must be less than 255 characters.") 
     private String name; 
  
     @Size(max = 5000, message = "Category description must be less than 5000 characters.") 
     private String description; 
  
     private UUID category_id; 
 }