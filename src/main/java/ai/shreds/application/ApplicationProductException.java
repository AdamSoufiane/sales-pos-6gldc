package ai.shreds.application; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @RestControllerAdvice 
 public class ApplicationProductException { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationProductException.class); 
  
     @ExceptionHandler(ProductNotFoundException.class) 
     public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) { 
         logger.error("ProductNotFoundException occurred: ", e); 
         return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND); 
     } 
  
     @ExceptionHandler(ValidationException.class) 
     public ResponseEntity<String> handleValidationException(ValidationException e) { 
         logger.error("ValidationException occurred: ", e); 
         return new ResponseEntity<>("Invalid product data", HttpStatus.BAD_REQUEST); 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         logger.error("Exception occurred: ", e); 
         return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
  
 class ProductNotFoundException extends RuntimeException { 
     public ProductNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 class ValidationException extends RuntimeException { 
     public ValidationException(String message) { 
         super(message); 
     } 
 } 
 