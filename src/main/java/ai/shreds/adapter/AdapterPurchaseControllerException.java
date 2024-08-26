package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterPurchaseResponseDTO; 
 import ai.shreds.shared.AdapterSharedErrorResponse; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
  
 @Slf4j 
 @RestControllerAdvice 
 public class AdapterPurchaseControllerException { 
  
     @ExceptionHandler(Exception.class) 
     public AdapterPurchaseResponseDTO handleException(Exception e) { 
         log.error("Exception occurred: ", e); 
         AdapterSharedErrorResponse errorResponse = new AdapterSharedErrorResponse(); 
         errorResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
         errorResponse.setData(null); 
         errorResponse.setError(e.getMessage()); 
          
         AdapterPurchaseResponseDTO responseDTO = new AdapterPurchaseResponseDTO(); 
         responseDTO.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
         responseDTO.setData(null); 
         responseDTO.setError(e.getMessage()); 
         return responseDTO; 
     } 
 } 
 