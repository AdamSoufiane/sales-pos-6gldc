package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents the inventory levels of products within the domain layer.
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
public class DomainInventoryEntity {
    /**
     * Unique identifier for the product.
     */
    private final UUID productId;

    /**
     * Quantity of the product in stock.
     */
    private final Integer quantity;

    /**
     * The threshold quantity at which an alert should be triggered.
     */
    private final Integer qteAlert;

    /**
     * Timestamp when the inventory was last updated.
     */
    private final LocalDateTime lastUpdated;
}