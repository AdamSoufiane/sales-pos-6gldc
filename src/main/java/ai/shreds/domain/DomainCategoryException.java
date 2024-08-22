package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * Custom exception class for handling category-related exceptions in the domain layer. 
  */ 
 public class DomainCategoryException extends RuntimeException { 
  
     /** 
      * Constructs a new DomainCategoryException with the specified detail message. 
      * 
      * @param message the detail message. 
      */ 
     public DomainCategoryException(String message) { 
         super(message); 
     } 
  
     /** 
      * Constructs a new DomainCategoryException with the specified detail message and cause. 
      * 
      * @param message the detail message. 
      * @param cause   the cause of the exception. 
      */ 
     public DomainCategoryException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     /** 
      * Creates a new DomainCategoryException indicating that a category with the specified ID was not found. 
      * 
      * @param id the UUID of the category. 
      * @return a new DomainCategoryException. 
      */ 
     public static DomainCategoryException categoryNotFound(UUID id) { 
         return new DomainCategoryException("Category with ID " + id + " not found."); 
     } 
  
     /** 
      * Creates a new DomainCategoryException indicating that the category name is not unique within its parent category. 
      * 
      * @param name the name of the category. 
      * @return a new DomainCategoryException. 
      */ 
     public static DomainCategoryException categoryNameNotUnique(String name) { 
         return new DomainCategoryException("Category name '" + name + "' is not unique within its parent category."); 
     } 
  
     /** 
      * Creates a new DomainCategoryException indicating that the category cannot be deleted because it has subcategories. 
      * 
      * @param id the UUID of the category. 
      * @return a new DomainCategoryException. 
      */ 
     public static DomainCategoryException categoryHasSubcategories(UUID id) { 
         return new DomainCategoryException("Category with ID " + id + " cannot be deleted because it has subcategories."); 
     } 
  
     /** 
      * Creates a new DomainCategoryException indicating that the parent category with the specified ID was not found. 
      * 
      * @param id the UUID of the parent category. 
      * @return a new DomainCategoryException. 
      */ 
     public static DomainCategoryException parentCategoryNotFound(UUID id) { 
         return new DomainCategoryException("Parent category with ID " + id + " not found."); 
     } 
  
     /** 
      * Creates a new DomainCategoryException indicating invalid category data. 
      * 
      * @param message the detail message. 
      * @return a new DomainCategoryException. 
      */ 
     public static DomainCategoryException invalidCategoryData(String message) { 
         return new DomainCategoryException("Invalid category data: " + message); 
     } 
  
     /** 
      * Creates a new DomainCategoryException indicating an invalid UUID format. 
      * 
      * @param uuid the invalid UUID. 
      * @return a new DomainCategoryException. 
      */ 
     public static DomainCategoryException invalidUUIDFormat(String uuid) { 
         return new DomainCategoryException("Invalid UUID format: " + uuid); 
     } 
 }