package ai.shreds.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ai.shreds.adapter.dto.AdapterSharedErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdapterKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public AdapterKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info("Message sent to topic {}: {}", topic, message);
        } catch (Exception e) {
            AdapterSharedErrorResponse response = handleKafkaException(e);
            // Further handling of the response if necessary
            throw new RuntimeException(response.getError(), e);
        }
    }

    private AdapterSharedErrorResponse handleKafkaException(Exception e) {
        log.error("Error sending message to Kafka: ", e);
        return new AdapterSharedErrorResponse(500, null, "Failed to send message to Kafka: " + e.getMessage());
    }
}