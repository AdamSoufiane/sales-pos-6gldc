package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Product Added Event.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedEntityDTO {
    /**
     * Unique identifier for the newly added product.
     */
    private String productId;

    /**
     * Initial stock quantity of the newly added product.
     */
    private Integer initialQuantity;

    /**
     * Timestamp when the product was added to the inventory.
     */
    private LocalDateTime creationTime;

    /**
     * The quantity threshold at which an alert should be triggered.
     */
    private Integer alertQuantity;

    /**
     * The default warehouse location where the product is stored.
     */
    private String warehouseLocation;
}