package ai.shreds.application; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Component; 
  
 /** 
  * Exception handler for supplier creation process in the application layer. 
  */ 
 @Component 
 public class ApplicationCreateSupplierException { 
     private static final Logger logger = LoggerFactory.getLogger(ApplicationCreateSupplierException.class); 
  
     /** 
      * Handles exceptions that occur during the supplier creation process. 
      * 
      * @param e the exception to handle 
      */ 
     public void handleException(Exception e) { 
         // Log the exception details 
         logger.error("An error occurred while creating a supplier: {}", e.getMessage(), e); 
          
         // Handle specific exception types if needed 
         if (e instanceof IllegalArgumentException) { 
             throw new SupplierCreationException("Invalid input data", e); 
         } else if (e instanceof DatabaseException) { // Assuming DatabaseException is a custom exception for database errors 
             throw new SupplierCreationException("Database error occurred", e); 
         } else { 
             throw new SupplierCreationException("Failed to create supplier", e); 
         } 
     } 
 } 
  
 // Custom exception class moved to a separate file for better organization 
 package ai.shreds.application; 
  
 /** 
  * Custom exception for supplier creation errors. 
  */ 
 public class SupplierCreationException extends RuntimeException { 
     public SupplierCreationException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 