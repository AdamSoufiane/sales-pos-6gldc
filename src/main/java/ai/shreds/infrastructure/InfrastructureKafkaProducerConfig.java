package ai.shreds.infrastructure; 
  
 import org.apache.kafka.clients.producer.ProducerConfig; 
 import org.apache.kafka.clients.producer.ProducerRecord; 
 import org.apache.kafka.common.serialization.StringSerializer; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.kafka.core.DefaultKafkaProducerFactory; 
 import org.springframework.kafka.core.KafkaTemplate; 
 import org.springframework.kafka.core.ProducerFactory; 
 import org.springframework.kafka.support.serializer.JsonSerializer; 
 import org.springframework.kafka.annotation.EnableKafka; 
 import org.springframework.beans.factory.annotation.Value; 
 import lombok.extern.slf4j.Slf4j; 
 import javax.annotation.PostConstruct; 
  
 import java.util.HashMap; 
 import java.util.Map; 
  
 @Configuration 
 @EnableKafka 
 @Slf4j 
 public class InfrastructureKafkaProducerConfig { 
  
     @Value("${kafka.bootstrap-servers}") 
     private String bootstrapServers; 
  
     @PostConstruct 
     public void validateProperties() { 
         if (bootstrapServers == null || bootstrapServers.isEmpty()) { 
             throw new IllegalArgumentException("Kafka bootstrap servers must be configured"); 
         } 
     } 
  
     @Bean 
     public ProducerFactory<String, String> producerFactory() { 
         Map<String, Object> configProps = new HashMap<>(); 
         configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); 
         configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); 
         configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); 
         return new DefaultKafkaProducerFactory<>(configProps); 
     } 
  
     @Bean 
     public KafkaTemplate<String, String> kafkaTemplate() { 
         return new KafkaTemplate<>(producerFactory()); 
     } 
  
     public void produceEvent(String event, Object data) { 
         KafkaTemplate<String, String> kafkaTemplate = kafkaTemplate(); 
         try { 
             ProducerRecord<String, String> record = new ProducerRecord<>(event, data.toString()); 
             kafkaTemplate.send(record); 
             log.info("Produced event to topic {}: {}", event, data); 
         } catch (Exception e) { 
             log.error("Failed to produce event to topic {}: {}", event, data, e); 
         } 
     } 
 } 
 