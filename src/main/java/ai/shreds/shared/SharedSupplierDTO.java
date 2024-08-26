package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Supplier.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharedSupplierDTO {
    /**
     * Unique identifier for the supplier (auto-generated).
     */
    private Integer id;

    /**
     * Name of the supplier.
     */
    private String name;

    /**
     * Contact information for the supplier, including phone number and email address.
     */
    private String contact_info;

    /**
     * Physical address of the supplier.
     */
    private String address;

    /**
     * Timestamp when the supplier record was created (auto-generated).
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created_at;

    /**
     * Timestamp when the supplier record was last updated (auto-generated).
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updated_at;

    /**
     * Due date for the supplier payment.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime due_date;

    /**
     * Company name of the supplier.
     */
    private String company_name;
}