package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AdapterDeleteSupplierResponse encapsulates the response structure for the DELETE supplier API endpoint.
 *
 * Note: Use Lombok annotations for getters and setters.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterDeleteSupplierResponse {
    private int status_code;
    private Object data = null;
    private String error;
}