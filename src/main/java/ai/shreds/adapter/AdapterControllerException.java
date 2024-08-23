package ai.shreds.adapter;

import ai.shreds.shared.SharedSupplierDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class AdapterControllerException extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdapterControllerException.class);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Exception occurred: ", ex);
        AdapterSupplierResponse errorResponse = new AdapterSupplierResponse();
        errorResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(ex.getMessage());
        errorResponse.setData(null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}