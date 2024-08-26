package ai.shreds.adapter; 
  
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.kafka.core.KafkaTemplate; 
 import org.springframework.stereotype.Service; 
 import ai.shreds.shared.AdapterSharedErrorResponse; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 public class AdapterKafkaProducer { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterKafkaProducer.class); 
  
     private final KafkaTemplate<String, String> kafkaTemplate; 
  
     @Autowired 
     public AdapterKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) { 
         this.kafkaTemplate = kafkaTemplate; 
     } 
  
     public void sendMessage(String topic, String message) { 
         try { 
             kafkaTemplate.send(topic, message); 
             logger.info("Message sent to topic {}: {}", topic, message); 
         } catch (Exception e) { 
             handleKafkaException(e); 
         } 
     } 
  
     private void handleKafkaException(Exception e) { 
         logger.error("Error sending message to Kafka: ", e); 
         // Handle the exception, e.g., send an alert, retry logic, etc. 
         AdapterSharedErrorResponse errorResponse = new AdapterSharedErrorResponse(); 
         errorResponse.setStatus_code(500); 
         errorResponse.setData(null); 
         errorResponse.setError("Failed to send message to Kafka: " + e.getMessage()); 
         // Additional error handling logic can be added here 
     } 
 } 
  
 // Implementation Note: Use Lombok annotations for getters and setters. 
 // Implementation Note: Use @Value annotation for the class if immutability is required. 
 // Implementation Note: Ensure proper exception handling by adding specific exception classes for Kafka exceptions. 
 