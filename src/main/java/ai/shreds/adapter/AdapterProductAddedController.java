package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationProductAddedInputPort; 
 import ai.shreds.application.ApplicationProductAddedException; 
 import ai.shreds.domain.DomainProductAddedEventResponse; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
  
 import javax.validation.Valid; 
  
 /** 
  * Controller to handle Product Added events from Kafka. 
  */ 
 @RestController 
 @RequestMapping("/api/v1/product-added") 
 @RequiredArgsConstructor 
 @Slf4j 
 public class AdapterProductAddedController { 
  
     private final ApplicationProductAddedInputPort applicationProductAddedInputPort; 
  
     /** 
      * Handles incoming ProductAdded events. 
      * 
      * @param params the request parameters containing product details 
      * @return response indicating success or failure 
      */ 
     @PostMapping 
     public AdapterProductAddedResponseDTO handleProductAddedEvent(@Valid @RequestBody AdapterProductAddedRequestParams params) { 
         try { 
             log.info("Received ProductAdded event: {}", params); 
             // Process the event using the application service 
             AdapterProductAddedResponseDTO response = applicationProductAddedInputPort.processProductAddedEvent(params); 
             log.info("Processed ProductAdded event successfully: {}", response); 
             return response; 
         } catch (ApplicationProductAddedException e) { 
             log.error("Error processing ProductAdded event: {}", e.getMessage(), e); 
             return new AdapterProductAddedResponseDTO("Error: " + e.getMessage()); 
         } 
     } 
  
     /** 
      * Handles exceptions thrown by the controller methods. 
      * 
      * @param e the exception 
      * @return response indicating the error 
      */ 
     @ExceptionHandler(Exception.class) 
     public AdapterProductAddedResponseDTO handleException(Exception e) { 
         log.error("Unhandled exception: {}", e.getMessage(), e); 
         return new AdapterProductAddedResponseDTO("Error: " + e.getMessage()); 
     } 
 } 
 