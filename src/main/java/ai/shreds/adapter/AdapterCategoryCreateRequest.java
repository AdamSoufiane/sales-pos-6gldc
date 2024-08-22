package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdapterCategoryCreateRequest {
    @NotEmpty(message = "Category name is required")
    private String name;
    private String description;
    @NotNull(message = "Category ID is required")
    private UUID category_id;
}