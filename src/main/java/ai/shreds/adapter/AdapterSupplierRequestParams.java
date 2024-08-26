package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterSupplierRequestParams {
    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Contact information is required")
    private String contact_info;

    @NotEmpty(message = "Address is required")
    private String address;
}