package ai.shreds.adapter;

import ai.shreds.shared.SharedAdapterProductAddedEventDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@Component
public class AdapterProductAddedConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductAddedConsumer.class);
    private final AdapterKafkaConsumer kafkaConsumer;
    private final ObjectMapper objectMapper;

    @Autowired
    public AdapterProductAddedConsumer(AdapterKafkaConsumer kafkaConsumer, ObjectMapper objectMapper) {
        this.kafkaConsumer = kafkaConsumer;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${kafka.topic.product-added}", groupId = "inventory-service")
    public void consume(String message) {
        try {
            if (message != null) {
                // Deserialize message to SharedAdapterProductAddedEventDTO
                SharedAdapterProductAddedEventDTO event = objectMapper.readValue(message, SharedAdapterProductAddedEventDTO.class);
                // Process the event
                kafkaConsumer.processProductAddedEvent(event);
            }
        } catch (JsonProcessingException e) {
            logger.error("Error deserializing ProductAdded event", e);
        } catch (Exception e) {
            logger.error("Error processing ProductAdded event", e);
        }
    }
}