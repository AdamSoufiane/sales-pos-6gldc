package ai.shreds.adapter;

import ai.shreds.shared.SharedSupplierDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@ControllerAdvice
public class AdapterControllerException extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdapterControllerException.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<AdapterSupplierResponse> handleException(Exception ex, WebRequest request) {
        logger.error("Exception occurred: ", ex);
        AdapterSupplierResponse errorResponse = new AdapterSupplierResponse();
        errorResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(ex.getMessage());
        errorResponse.setData(null);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode("INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}