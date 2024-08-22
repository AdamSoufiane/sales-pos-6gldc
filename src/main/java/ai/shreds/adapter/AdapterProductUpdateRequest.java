package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.validation.constraints.NotEmpty; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Positive; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterProductUpdateRequest { 
     @NotEmpty(message = "Name must not be empty") 
     private String name; 
  
     private String description; 
  
     @Positive(message = "Price must be positive") 
     private BigDecimal price; 
  
     @NotNull(message = "Category ID must not be null") 
     private UUID categoryId; 
  
     // Getters and Setters 
     public String getName() { 
         return name; 
     } 
  
     public void setName(String name) { 
         this.name = name; 
     } 
  
     public String getDescription() { 
         return description; 
     } 
  
     public void setDescription(String description) { 
         this.description = description; 
     } 
  
     public BigDecimal getPrice() { 
         return price; 
     } 
  
     public void setPrice(BigDecimal price) { 
         this.price = price; 
     } 
  
     public UUID getCategoryId() { 
         return categoryId; 
     } 
  
     public void setCategoryId(UUID categoryId) { 
         this.categoryId = categoryId; 
     } 
 } 
 // Note: Use Lombok annotations for getters and setters instead of manually writing them.