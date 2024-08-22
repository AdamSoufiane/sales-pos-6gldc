package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
@RestControllerAdvice
public class AdapterExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdapterExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        logger.error("Exception caught: ", exception);
        return new ResponseEntity<>("An error occurred: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AdapterKafkaConsumerException.class)
    public ResponseEntity<String> handleKafkaConsumerException(AdapterKafkaConsumerException exception) {
        logger.error("Kafka Consumer Exception caught: Topic - {}, Partition - {}", exception.getTopic(), exception.getPartition(), exception);
        return new ResponseEntity<>("Kafka Consumer Error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}