package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for Category.
 * Represents category data when interacting with external services or other layers of the application.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdapterCategoryDTO {
    @NotNull
    private UUID id;
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @Size(max = 255)
    private String description;
    private UUID categoryId;
    private Instant createdAt;
    private Instant updatedAt;
}