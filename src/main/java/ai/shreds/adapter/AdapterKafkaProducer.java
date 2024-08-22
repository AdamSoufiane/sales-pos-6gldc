package ai.shreds.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdapterKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void produceEvent(String event, Object data) {
        try {
            String message = objectMapper.writeValueAsString(data);
            kafkaTemplate.send(event, message);
            log.info("Produced event to Kafka: {} with data: {}", event, message);
        } catch (JsonProcessingException e) {
            log.error("Failed to produce event to Kafka: {} with data: {}", event, data, e);
        }
    }
}