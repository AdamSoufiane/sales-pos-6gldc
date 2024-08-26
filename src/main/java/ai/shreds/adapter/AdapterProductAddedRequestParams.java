package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ai.shreds.shared.SharedRequestParams;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterProductAddedRequestParams extends SharedRequestParams {

    @NotBlank(message = "Product ID cannot be blank")
    private String productId;

    @NotNull(message = "Initial quantity cannot be null")
    @Min(value = 1, message = "Initial quantity must be at least 1")
    private Integer initialQuantity;

    @NotNull(message = "Creation time cannot be null")
    private LocalDateTime creationTime;

    @NotNull(message = "Alert quantity cannot be null")
    @Min(value = 0, message = "Alert quantity must be at least 0")
    private Integer alertQuantity;

    @NotBlank(message = "Warehouse location cannot be blank")
    private String warehouseLocation;
}