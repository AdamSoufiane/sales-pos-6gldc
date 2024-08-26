package ai.shreds.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents an inventory record for a product.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainInventoryEntity {
    @NotBlank(message = "Product ID must not be null or empty")
    private String productId;
    @Min(value = 1, message = "Initial quantity must be a positive integer")
    private int initialQuantity;
    @Future(message = "Creation time must be in the future")
    private LocalDateTime creationTime;
    @Min(value = 0, message = "Alert quantity must be non-negative")
    private int alertQuantity;
    @NotBlank(message = "Warehouse location must not be null or empty")
    private String warehouseLocation;
}