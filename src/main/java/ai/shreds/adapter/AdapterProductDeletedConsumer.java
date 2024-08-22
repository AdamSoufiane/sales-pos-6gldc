package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedAdapterProductDeletedEventDTO; 
 import org.springframework.kafka.annotation.KafkaListener; 
 import org.springframework.retry.annotation.Backoff; 
 import org.springframework.retry.annotation.Retryable; 
 import org.springframework.stereotype.Component; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import com.fasterxml.jackson.databind.ObjectMapper; 
 import lombok.RequiredArgsConstructor; 
  
 @Component 
 @RequiredArgsConstructor 
 public class AdapterProductDeletedConsumer { 
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductDeletedConsumer.class); 
     private final AdapterKafkaConsumer kafkaConsumer; 
     private final ObjectMapper objectMapper; 
  
     @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000)) 
     @KafkaListener(topics = "product-deleted", groupId = "inventory-service", errorHandler = "kafkaListenerErrorHandler") 
     public void consume(String message) { 
         try { 
             SharedAdapterProductDeletedEventDTO event = objectMapper.readValue(message, SharedAdapterProductDeletedEventDTO.class); 
             kafkaConsumer.processProductDeletedEvent(event); 
             logger.info("Successfully processed product deleted event for productId: {}", event.getProductId()); 
         } catch (Exception e) { 
             logger.error("Error processing product deleted event", e); 
             handleException(e); 
         } 
     } 
  
     private void handleException(Exception e) { 
         // Custom exception handling logic 
         logger.error("Custom exception handling: ", e); 
         notifyStakeholders(e); 
     } 
  
     private void notifyStakeholders(Exception e) { 
         // Logic to notify stakeholders about the repeated failure 
         logger.error("Notifying stakeholders about the repeated failure: ", e); 
     } 
 } 
 