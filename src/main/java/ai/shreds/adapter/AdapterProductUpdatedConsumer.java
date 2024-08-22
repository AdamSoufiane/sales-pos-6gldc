package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedAdapterProductUpdatedEventDTO; 
 import com.fasterxml.jackson.databind.ObjectMapper; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.kafka.annotation.KafkaListener; 
 import org.springframework.stereotype.Component; 
  
 @Component 
 @RequiredArgsConstructor 
 public class AdapterProductUpdatedConsumer { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductUpdatedConsumer.class); 
     private final AdapterKafkaConsumer adapterKafkaConsumer; 
     private final ObjectMapper objectMapper; 
  
     @Value("${kafka.topic.product-updated}") 
     private String productUpdatedTopic; 
  
     @KafkaListener(topics = "#{'${kafka.topic.product-updated}'}", groupId = "inventory-service") 
     public void consume(String message) { 
         try { 
             SharedAdapterProductUpdatedEventDTO event = parseMessage(message); 
             adapterKafkaConsumer.processProductUpdatedEvent(event); 
         } catch (Exception e) { 
             logger.error("Error processing ProductUpdated event: {}", e.getMessage(), e); 
         } 
     } 
  
     private SharedAdapterProductUpdatedEventDTO parseMessage(String message) throws Exception { 
         try { 
             return objectMapper.readValue(message, SharedAdapterProductUpdatedEventDTO.class); 
         } catch (Exception e) { 
             throw new Exception("Failed to parse message", e); 
         } 
     } 
 } 
 