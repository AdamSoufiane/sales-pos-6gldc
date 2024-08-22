package ai.shreds.domain;

import java.util.UUID;
import java.lang.IllegalArgumentException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the core attributes of a category without metadata like id, created_at, and updated_at.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DomainCategoryValue {
    @Size(max = 255)
    private String name;

    @Size(max = 1000)
    private String description;

    @NotNull
    private UUID category_id;

    /**
     * Validates the category data.
     * Ensures the name is not null or empty,
     * the description does not exceed 1000 characters,
     * and the category_id is a valid UUID.
     */
    public void validate() {
        StringBuilder errorMessages = new StringBuilder();

        if (name == null || name.trim().isEmpty()) {
            errorMessages.append("Category name cannot be null or empty\n");
        }

        if (description != null && description.length() > 1000) {
            errorMessages.append("Description cannot exceed 1000 characters\n");
        }

        if (category_id == null) {
            errorMessages.append("Category ID cannot be null\n");
        }

        if (errorMessages.length() > 0) {
            throw new IllegalArgumentException(errorMessages.toString());
        }
    }
}