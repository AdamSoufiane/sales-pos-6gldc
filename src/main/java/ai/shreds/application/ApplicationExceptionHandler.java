package ai.shreds.application; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.ResponseStatus; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @ControllerAdvice 
 public class ApplicationExceptionHandler { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class); 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception exception) { 
         // Log the exception 
         logger.error("Exception occurred: ", exception); 
  
         // Determine the type of exception and respond accordingly 
         if (exception instanceof ProductNotFoundException) { 
             return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND); 
         } else if (exception instanceof InventoryUpdateException) { 
             return new ResponseEntity<>("Failed to update inventory", HttpStatus.BAD_REQUEST); 
         } else if (exception instanceof DatabaseConnectionException) { 
             return new ResponseEntity<>("Database connection error", HttpStatus.SERVICE_UNAVAILABLE); 
         } else { 
             return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
 } 
  
 // Custom exception classes 
 @ResponseStatus(HttpStatus.NOT_FOUND) 
 class ProductNotFoundException extends RuntimeException { 
     public ProductNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 @ResponseStatus(HttpStatus.BAD_REQUEST) 
 class InventoryUpdateException extends RuntimeException { 
     public InventoryUpdateException(String message) { 
         super(message); 
     } 
 } 
  
 @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE) 
 class DatabaseConnectionException extends RuntimeException { 
     public DatabaseConnectionException(String message) { 
         super(message); 
     } 
 }