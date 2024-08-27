package ai.shreds.adapter;

import ai.shreds.adapter.AdapterPurchaseResponseDTO;
import ai.shreds.adapter.dto.AdapterSharedErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdapterPurchaseControllerException {

    @ExceptionHandler(Exception.class)
    public AdapterPurchaseResponseDTO handleException(Exception e) {
        log.error("Exception occurred: ", e);
        AdapterSharedErrorResponse errorResponse = new AdapterSharedErrorResponse();
        errorResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setData(null);
        errorResponse.setError(e.getMessage());
        AdapterPurchaseResponseDTO response = new AdapterPurchaseResponseDTO();
        response.setStatus_code(errorResponse.getStatus_code());
        response.setData(null);
        response.setError(errorResponse.getError());
        return response;
    }
}