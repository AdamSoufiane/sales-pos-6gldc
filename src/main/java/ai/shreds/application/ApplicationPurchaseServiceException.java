package ai.shreds.application; 
  
 import ai.shreds.shared.AdapterPurchaseResponseDTO; 
 import ai.shreds.shared.AdapterSharedErrorResponse; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Component; 
  
 /** 
  * This class handles exceptions that occur within the ApplicationPurchaseService. 
  */ 
 @Slf4j 
 @Component 
 public class ApplicationPurchaseServiceException { 
  
     /** 
      * Handles exceptions and returns an appropriate AdapterPurchaseResponseDTO object. 
      *  
      * @param e the exception that occurred 
      * @return AdapterPurchaseResponseDTO containing error details 
      */ 
     public AdapterPurchaseResponseDTO handleException(Exception e) { 
         log.error("An error occurred while processing the purchase transaction", e); 
         AdapterSharedErrorResponse errorResponse = new AdapterSharedErrorResponse(); 
         errorResponse.setStatus_code(500); 
         errorResponse.setData(null); 
         errorResponse.setError("Internal server error: " + e.getMessage()); 
         AdapterPurchaseResponseDTO response = new AdapterPurchaseResponseDTO(); 
         response.setStatus_code(500); 
         response.setData(null); 
         response.setError(errorResponse.getError()); 
         return response; 
     } 
 }