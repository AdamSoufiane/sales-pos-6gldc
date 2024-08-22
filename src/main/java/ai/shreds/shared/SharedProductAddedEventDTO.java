package ai.shreds.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for ProductAdded event.
 * This class is used to transfer data when a product is added to the inventory.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductAddedEventDTO {
    /**
     * Type of the event, should be 'ProductAdded'.
     */
    @JsonProperty("eventType")
    private String eventType = "ProductAdded";

    /**
     * Unique identifier for the product.
     */
    @JsonProperty("productId")
    private UUID productId;

    /**
     * Name of the product.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Description of the product.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Price of the product.
     */
    @JsonProperty("price")
    private BigDecimal price;

    /**
     * Identifier for the category to which the product belongs.
     */
    @JsonProperty("categoryId")
    private UUID categoryId;

    /**
     * Timestamp when the product was created.
     */
    @JsonProperty("createdAt")
    private Timestamp createdAt;

    /**
     * Timestamp when the product was last updated.
     */
    @JsonProperty("updatedAt")
    private Timestamp updatedAt;
}