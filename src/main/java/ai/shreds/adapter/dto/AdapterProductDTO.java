package ai.shreds.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing product data in the adapter layer.
 * This class is used to transfer product data between different layers of the application,
 * particularly from the adapter layer to the application layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterProductDTO {
    private String productId;
    private double purchasePrice;
    private int quantity;
}