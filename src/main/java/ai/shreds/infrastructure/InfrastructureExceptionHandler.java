package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.context.request.WebRequest; 
  
 @ControllerAdvice 
 public class InfrastructureExceptionHandler { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureExceptionHandler.class); 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception exception, WebRequest request) { 
         // Log the exception 
         logger.error("An unexpected error occurred: ", exception); 
         String errorMessage = "An unexpected error occurred: " + exception.getClass().getName() + " - " + exception.getMessage(); 
         return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
 