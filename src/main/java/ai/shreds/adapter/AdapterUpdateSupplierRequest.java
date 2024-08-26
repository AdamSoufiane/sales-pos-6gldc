package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Request object for updating a supplier.
 * Contains the necessary fields to update supplier information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterUpdateSupplierRequest {
    /**
     * Name of the supplier.
     */
    @NotBlank(message = "Name is mandatory")
    private String name;

    /**
     * Contact information of the supplier.
     */
    @NotBlank(message = "Contact information is mandatory")
    private String contact_info;

    /**
     * Address of the supplier.
     */
    @NotBlank(message = "Address is mandatory")
    private String address;

    /**
     * Company name of the supplier.
     */
    @NotBlank(message = "Company name is mandatory")
    private String company_name;

    /**
     * Due date for the supplier payment.
     */
    @NotBlank(message = "Due date is mandatory")
    private LocalDateTime due_date;
}