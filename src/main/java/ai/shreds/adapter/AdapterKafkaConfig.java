package ai.shreds.adapter; 
  
 import org.apache.kafka.clients.consumer.ConsumerConfig; 
 import org.apache.kafka.common.serialization.StringDeserializer; 
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory; 
 import org.springframework.kafka.core.ConsumerFactory; 
 import org.springframework.kafka.core.DefaultKafkaConsumerFactory; 
 import org.springframework.kafka.listener.ConcurrentMessageListenerContainer; 
 import org.springframework.kafka.listener.config.ContainerProperties; 
 import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer; 
 import org.springframework.kafka.support.serializer.JsonDeserializer; 
 import java.util.HashMap; 
 import java.util.Map; 
  
 @Configuration 
 public class AdapterKafkaConfig { 
  
     @Value("${kafka.concurrency:3}") 
     private int concurrencyLevel; 
  
     @Value("${kafka.ack-mode:MANUAL_IMMEDIATE}") 
     private ContainerProperties.AckMode ackMode; 
  
     @Bean 
     public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() { 
         ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>(); 
         factory.setConsumerFactory(consumerFactory()); 
         factory.setConcurrency(concurrencyLevel); // Set concurrency level 
         factory.getContainerProperties().setAckMode(ackMode); 
         return factory; 
     } 
  
     @Bean 
     public ConsumerFactory<String, String> consumerFactory() { 
         Map<String, Object> config = new HashMap<>(); 
         config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "${kafka.bootstrap-servers}"); 
         config.put(ConsumerConfig.GROUP_ID_CONFIG, "${kafka.group-id}"); 
         config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class); 
         config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class); 
         config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName()); 
         config.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); 
         return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>()); 
     } 
  
     // Add a note to use Lombok annotations for boilerplate code reduction. 
 } 
 