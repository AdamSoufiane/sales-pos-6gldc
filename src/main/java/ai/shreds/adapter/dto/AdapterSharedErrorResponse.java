package ai.shreds.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for encapsulating error responses in the adapter layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterSharedErrorResponse {
    /**
     * HTTP status code of the response.
     */
    private int status_code;
    /**
     * Data field, always null for error responses.
     */
    private final Object data = null;
    /**
     * Error message detailing what went wrong.
     */
    private String error;
}