package ai.shreds.adapter;

import ai.shreds.adapter.AdapterPurchaseDataDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AdapterSharedSuccessResponse is a Data Transfer Object (DTO) used to encapsulate the success response
 * for the purchase transaction process. It contains the status code, data, and error fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdapterSharedSuccessResponse {
    private int status_code;
    private AdapterPurchaseDataDTO data;
    private String error = null;
}