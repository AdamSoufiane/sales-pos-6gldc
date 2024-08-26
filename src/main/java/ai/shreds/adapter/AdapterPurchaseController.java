package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterPurchaseRequestDTO; 
 import ai.shreds.shared.AdapterPurchaseResponseDTO; 
 import ai.shreds.shared.AdapterSharedErrorResponse; 
 import ai.shreds.shared.AdapterSharedSuccessResponse; 
 import ai.shreds.application.ApplicationPurchaseServiceInputPort; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
 import javax.validation.Valid; 
  
 @Slf4j 
 @RestController 
 @RequestMapping("/purchase") 
 @RequiredArgsConstructor 
 public class AdapterPurchaseController { 
  
     private final ApplicationPurchaseServiceInputPort purchaseService; 
  
     @PostMapping 
     public ResponseEntity<AdapterPurchaseResponseDTO> processPurchaseTransaction(@Valid @RequestBody AdapterPurchaseRequestDTO request) { 
         log.info("Processing purchase transaction for purchaseNumber: {}", request.getPurchaseNumber()); 
         try { 
             AdapterPurchaseResponseDTO response = purchaseService.processPurchaseTransaction(request); 
             log.info("Successfully processed purchase transaction for purchaseNumber: {}", request.getPurchaseNumber()); 
             return new ResponseEntity<>(response, HttpStatus.OK); 
         } catch (IllegalArgumentException e) { 
             log.error("Bad request for purchaseNumber: {}: {}", request.getPurchaseNumber(), e.getMessage()); 
             return new ResponseEntity<>(handleBadRequestException(e), HttpStatus.BAD_REQUEST); 
         } catch (Exception e) { 
             log.error("Internal server error for purchaseNumber: {}: {}", request.getPurchaseNumber(), e.getMessage()); 
             return new ResponseEntity<>(handleInternalServerErrorException(e), HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
  
     @ExceptionHandler(IllegalArgumentException.class) 
     public AdapterPurchaseResponseDTO handleBadRequestException(IllegalArgumentException e) { 
         log.error("Bad request: {}", e.getMessage()); 
         AdapterSharedErrorResponse errorResponse = new AdapterSharedErrorResponse(); 
         errorResponse.setStatus_code(400); 
         errorResponse.setData(null); 
         errorResponse.setError(e.getMessage()); 
         return errorResponse; 
     } 
  
     @ExceptionHandler(Exception.class) 
     public AdapterPurchaseResponseDTO handleInternalServerErrorException(Exception e) { 
         log.error("Internal server error: {}", e.getMessage()); 
         AdapterSharedErrorResponse errorResponse = new AdapterSharedErrorResponse(); 
         errorResponse.setStatus_code(500); 
         errorResponse.setData(null); 
         errorResponse.setError(e.getMessage()); 
         return errorResponse; 
     } 
 } 
 