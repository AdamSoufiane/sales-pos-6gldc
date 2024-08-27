package ai.shreds.adapter;

import ai.shreds.adapter.dto.AdapterProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Data Transfer Object (DTO) that encapsulates the details of a purchase transaction.
 * This includes the purchase ID, purchase number, purchase date, supplier ID, and a list of products involved in the purchase.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterPurchaseDataDTO {
    /**
     * Unique identifier for the purchase transaction.
     */
    @NotNull
    private String purchaseId;

    /**
     * Unique number for the purchase transaction.
     */
    @NotNull
    private String purchaseNumber;

    /**
     * Date when the purchase was made.
     */
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$", message = "Date must be in ISO 8601 format")
    private String purchaseDate;

    /**
     * Unique identifier of the supplier.
     */
    @NotNull
    private String supplierId;

    /**
     * List of products involved in the purchase.
     */
    @NotNull
    private List<AdapterProductDTO> products;
}