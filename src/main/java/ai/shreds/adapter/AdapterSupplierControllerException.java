package ai.shreds.adapter; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @ControllerAdvice 
 public class AdapterSupplierControllerException { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterSupplierControllerException.class); 
  
     @ExceptionHandler(SupplierNotFoundException.class) 
     public ResponseEntity<ErrorResponse> handleSupplierNotFoundException(SupplierNotFoundException e) { 
         logger.error("Supplier not found exception: ", e); 
         ErrorResponse errorResponse = new ErrorResponse("404", "Supplier not found."); 
         return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<ErrorResponse> handleException(Exception e) { 
         logger.error("Internal server error: ", e); 
         ErrorResponse errorResponse = new ErrorResponse("500", "Internal server error."); 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     public static class ErrorResponse { 
         private String errorCode; 
         private String errorMessage; 
  
         public ErrorResponse(String errorCode, String errorMessage) { 
             this.errorCode = errorCode; 
             this.errorMessage = errorMessage; 
         } 
  
         public String getErrorCode() { 
             return errorCode; 
         } 
  
         public void setErrorCode(String errorCode) { 
             this.errorCode = errorCode; 
         } 
  
         public String getErrorMessage() { 
             return errorMessage; 
         } 
  
         public void setErrorMessage(String errorMessage) { 
             this.errorMessage = errorMessage; 
         } 
     } 
 } 
  
 class SupplierNotFoundException extends RuntimeException { 
     public SupplierNotFoundException(String message) { 
         super(message); 
     } 
 }