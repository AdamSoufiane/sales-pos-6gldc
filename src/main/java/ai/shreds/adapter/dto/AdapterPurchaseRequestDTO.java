package ai.shreds.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import ai.shreds.adapter.dto.AdapterProductDTO; // Ensure the AdapterProductDTO class is correctly imported and accessible.

/**
 * Data Transfer Object for Purchase Request.
 * Contains fields for purchaseNumber, purchaseDate, supplierId, and a list of products.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterPurchaseRequestDTO {
    private String purchaseNumber;
    private String purchaseDate;
    private String supplierId;
    private List<AdapterProductDTO> products;
}