package ai.shreds.adapter; 
  
 import org.springframework.http.ResponseEntity; 
 import org.springframework.kafka.listener.ListenerExecutionFailedException; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @RestControllerAdvice 
 public class AdapterKafkaConsumerException { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterKafkaConsumerException.class); 
  
     @ExceptionHandler(ListenerExecutionFailedException.class) 
     public ResponseEntity<String> handleException(ListenerExecutionFailedException exception) { 
         // Log the exception 
         logger.error("Error occurred during Kafka message processing: ", exception); 
         String errorMessage = "Error occurred during Kafka message processing: " + exception.getMessage(); 
         return ResponseEntity.status(500).body(errorMessage); 
     } 
 } 
 