package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
 import lombok.ToString; 
  
 import java.util.UUID; 
  
 /** 
  * Represents the core attributes of a category without metadata like id, created_at, and updated_at. 
  */ 
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @ToString 
 public class DomainCategoryValue { 
     private String name; 
     private String description; 
     private UUID category_id; 
  
     /** 
      * Validates the category data. 
      * Ensures the name is not null or empty, the name is unique within its parent category, 
      * the description does not exceed 1000 characters, and the category_id is a valid UUID. 
      */ 
     public void validate() { 
         if (name == null || name.trim().isEmpty()) { 
             throw new IllegalArgumentException("Category name cannot be null or empty"); 
         } 
         // Assuming there's a method to check uniqueness within the parent category 
         if (!isNameUniqueWithinParentCategory(name, category_id)) { 
             throw new IllegalArgumentException("Category name must be unique within its parent category"); 
         } 
         if (description != null && description.length() > 1000) { 
             throw new IllegalArgumentException("Description cannot exceed 1000 characters"); 
         } 
         if (category_id != null) { 
             try { 
                 UUID.fromString(category_id.toString()); 
             } catch (IllegalArgumentException e) { 
                 throw new IllegalArgumentException("Invalid UUID for category_id"); 
             } 
         } 
     } 
 }