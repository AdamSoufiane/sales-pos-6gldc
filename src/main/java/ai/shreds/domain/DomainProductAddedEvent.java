package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Represents the event of a product being added to the inventory.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainProductAddedEvent {
    /**
     * Unique identifier for the newly added product.
     */
    @NotBlank
    private String productId;

    /**
     * Initial stock quantity of the newly added product.
     */
    @NotNull
    @Min(1)
    private Integer initialQuantity;

    /**
     * Timestamp when the product was added to the inventory.
     */
    @NotNull
    private LocalDateTime creationTime;

    /**
     * The quantity threshold at which an alert should be triggered.
     */
    @NotNull
    @Min(0)
    private Integer alertQuantity;

    /**
     * The default warehouse location where the product is stored.
     */
    @NotBlank
    private String warehouseLocation;
}