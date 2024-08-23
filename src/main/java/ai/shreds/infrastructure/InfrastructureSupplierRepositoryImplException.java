package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Component; 
  
 /** 
  * Handles exceptions that occur within the InfrastructureSupplierRepositoryImpl class. 
  */ 
 @Component 
 public class InfrastructureSupplierRepositoryImplException { 
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureSupplierRepositoryImplException.class); 
  
     /** 
      * Handles the given exception by logging it and optionally rethrowing it as a custom exception. 
      * 
      * @param e the exception to handle 
      */ 
     public void handleException(Exception e) { 
         // Log the exception details 
         logger.error("An error occurred in InfrastructureSupplierRepositoryImpl: {}", e.getMessage(), e); 
         // Optionally, rethrow the exception as a custom exception 
         throw new CustomRepositoryException("Error in repository layer", e); 
     } 
 } 
  
 /** 
  * Custom exception to wrap repository layer exceptions. 
  */ 
 class CustomRepositoryException extends RuntimeException { 
     /** 
      * Constructs a new CustomRepositoryException with the specified detail message and cause. 
      * 
      * @param message the detail message 
      * @param cause the cause of the exception 
      */ 
     public CustomRepositoryException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 