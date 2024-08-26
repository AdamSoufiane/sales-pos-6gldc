package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Represents an inventory record for a product.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainInventoryEntity {

    /**
     * Unique identifier for the newly added product.
     */
    private String productId;

    /**
     * Initial stock quantity of the newly added product.
     */
    private int initialQuantity;

    /**
     * Timestamp when the product was added to the inventory.
     */
    private LocalDateTime creationTime;

    /**
     * The quantity threshold at which an alert should be triggered.
     */
    private int alertQuantity;

    /**
     * The default warehouse location where the product is stored.
     */
    private String warehouseLocation;
}