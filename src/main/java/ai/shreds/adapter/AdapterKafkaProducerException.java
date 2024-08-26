package ai.shreds.adapter;

import ai.shreds.shared.dto.AdapterSharedErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.KafkaException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class AdapterKafkaProducerException {
    private static final Logger logger = LoggerFactory.getLogger(AdapterKafkaProducerException.class);

    /**
     * Handles exceptions that occur during Kafka message production.
     * Logs the exception details and performs necessary error handling.
     * @param e the exception to handle
     * @return an error response object
     */
    @Retryable(value = KafkaException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public AdapterSharedErrorResponse handleKafkaException(Exception e) {
        if (e instanceof KafkaException) {
            logger.error("KafkaException occurred: {}", e.getMessage(), e);
            // Additional handling for Kafka-specific exceptions can be added here
        } else {
            logger.error("An unexpected exception occurred: {}", e.getMessage(), e);
        }
        // Potentially add more error handling logic here, such as sending alerts or retrying the operation
        return new AdapterSharedErrorResponse(500, null, e.getMessage());
    }
}