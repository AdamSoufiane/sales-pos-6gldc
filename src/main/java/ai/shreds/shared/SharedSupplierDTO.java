package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Supplier.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedSupplierDTO {
    /**
     * Unique identifier for the supplier.
     */
    private Long id;

    /**
     * Name of the supplier.
     */
    private String name;

    /**
     * Contact information including phone number and email address.
     */
    private String contact_info;

    /**
     * Physical address of the supplier.
     */
    private String address;

    /**
     * Timestamp when the supplier record was created.
     */
    private LocalDateTime created_at;

    /**
     * Timestamp when the supplier record was last updated.
     */
    private LocalDateTime updated_at;
}