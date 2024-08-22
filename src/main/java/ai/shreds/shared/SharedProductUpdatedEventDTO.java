package ai.shreds.shared;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing a product update event.
 * This DTO is used to transfer data between different layers of the application
 * when a product is updated.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductUpdatedEventDTO {
    private String eventType;
    private UUID productId;
    private String name;
    private String description;
    private BigDecimal price;
    private UUID categoryId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}