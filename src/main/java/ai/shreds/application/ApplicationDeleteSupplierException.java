package ai.shreds.application; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
 import lombok.extern.slf4j.Slf4j; 
  
 /** 
  * Exception handler for supplier deletion related exceptions. 
  */ 
 @Slf4j 
 @RestControllerAdvice 
 public class ApplicationDeleteSupplierException { 
  
     /** 
      * Handles SupplierDeletionException and returns an appropriate response. 
      * 
      * @param e the SupplierDeletionException 
      * @return ResponseEntity with error message and HTTP status 
      */ 
     @ExceptionHandler(SupplierDeletionException.class) 
     public ResponseEntity<String> handleSupplierDeletionException(SupplierDeletionException e) { 
         // Log the exception details 
         log.error("Exception occurred while deleting supplier: ", e); 
         // Return an error response with appropriate status code 
         return new ResponseEntity<>("Error occurred while deleting supplier: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     /** 
      * Handles generic exceptions and returns an appropriate response. 
      * 
      * @param e the Exception 
      * @return ResponseEntity with error message and HTTP status 
      */ 
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         // Log the exception details 
         log.error("Exception occurred: ", e); 
         // Return an error response with appropriate status code 
         return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
  
 /** 
  * Custom exception for supplier deletion errors. 
  */ 
 class SupplierDeletionException extends RuntimeException { 
     public SupplierDeletionException(String message) { 
         super(message); 
     } 
  
     public SupplierDeletionException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 