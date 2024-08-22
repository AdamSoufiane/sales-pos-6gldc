package ai.shreds.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdapterCategoryControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryControllerException.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // Log the exception
        logger.error("Illegal argument: ", e);
        // Return a ResponseEntity with a specific error message and HTTP status 400
        return new ResponseEntity<>("Illegal argument: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        // Log the exception
        logger.error("Null pointer exception: ", e);
        // Return a ResponseEntity with a specific error message and HTTP status 500
        return new ResponseEntity<>("Null pointer exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        // Log the exception
        logger.error("Data integrity violation: ", e);
        // Return a ResponseEntity with a specific error message and HTTP status 409
        return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // Log the exception
        logger.error("Validation error: ", e);
        // Return a ResponseEntity with a specific error message and HTTP status 400
        return new ResponseEntity<>("Validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception
        logger.error("An error occurred: ", e);
        // Return a ResponseEntity with a generic error message and HTTP status 500
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}