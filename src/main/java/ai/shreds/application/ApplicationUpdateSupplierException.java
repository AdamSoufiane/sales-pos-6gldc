package ai.shreds.application; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
  
 /** 
  * Exception handler for update supplier operations in the application layer. 
  */ 
 @RestControllerAdvice 
 public class ApplicationUpdateSupplierException { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationUpdateSupplierException.class); 
  
     /** 
      * Handles generic exceptions. 
      * 
      * @param e the exception 
      * @return a response entity with an error message 
      */ 
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         logger.error("Exception occurred while updating supplier: ", e); 
         return new ResponseEntity<>("An error occurred while updating the supplier.", HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     /** 
      * Handles database access exceptions. 
      * 
      * @param e the data access exception 
      * @return a response entity with an error message 
      */ 
     @ExceptionHandler(DataAccessException.class) 
     public ResponseEntity<String> handleDataAccessException(DataAccessException e) { 
         logger.error("Database exception occurred while updating supplier: ", e); 
         return new ResponseEntity<>("A database error occurred while updating the supplier.", HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     /** 
      * Handles illegal argument exceptions. 
      * 
      * @param e the illegal argument exception 
      * @return a response entity with an error message 
      */ 
     @ExceptionHandler(IllegalArgumentException.class) 
     public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) { 
         logger.error("Invalid argument exception occurred while updating supplier: ", e); 
         return new ResponseEntity<>("Invalid input provided while updating the supplier.", HttpStatus.BAD_REQUEST); 
     } 
 } 
 