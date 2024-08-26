package ai.shreds.adapter;

import ai.shreds.application.ApplicationProductAddedInputPort;
import ai.shreds.application.ApplicationProductAddedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * Listener to handle Product Added events from Kafka.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AdapterProductAddedController {

    private final ApplicationProductAddedInputPort applicationProductAddedInputPort;

    /**
     * Handles incoming ProductAdded events.
     *
     * @param record the Kafka consumer record containing product details
     */
    @KafkaListener(topics = "ProductAdded")
    public void handleProductAddedEvent(ConsumerRecord<String, AdapterProductAddedRequestParams> record) {
        AdapterProductAddedRequestParams params = record.value();
        try {
            log.info("Received ProductAdded event: {}", params);
            // Process the event using the application service
            AdapterProductAddedResponseDTO response = applicationProductAddedInputPort.processProductAddedEvent(params);
            log.info("Processed ProductAdded event successfully: {}", response);
        } catch (ApplicationProductAddedException e) {
            log.error("Error processing ProductAdded event: {}", e.getMessage(), e);
        }
    }
}