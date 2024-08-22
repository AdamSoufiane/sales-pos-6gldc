package ai.shreds.application; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Component; 
  
 @Component 
 public class ApplicationCategoryServiceException { 
     private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryServiceException.class); 
  
     public void handleException(Exception e) { 
         // Log the exception details with more context 
         logger.error("An error occurred in the ApplicationCategoryService: {}", e.getMessage(), e); 
  
         // Optionally, rethrow the exception or wrap it in a custom exception 
         throw new CustomApplicationException("An error occurred in the category service", e); 
     } 
 } 
  
 class CustomApplicationException extends RuntimeException { 
     public CustomApplicationException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }