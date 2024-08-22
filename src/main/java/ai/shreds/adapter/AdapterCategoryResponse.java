package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Data Transfer Object (DTO) for transferring category data between layers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdapterCategoryResponse {
    /**
     * Unique identifier for the category.
     */
    @NotNull
    private UUID id;

    /**
     * Name of the category.
     */
    @NotNull
    @Size(max = 255)
    private String name;

    /**
     * Description of the category.
     */
    private String description;

    /**
     * Identifier of the parent category (if applicable).
     */
    private UUID category_id;

    /**
     * Timestamp when the category was created.
     */
    @NotNull
    private LocalDateTime created_at;

    /**
     * Timestamp when the category was last updated.
     */
    @NotNull
    private LocalDateTime updated_at;

    /**
     * Error message for the response.
     */
    private String message;

    /**
     * Formats the created_at timestamp to a standard string representation.
     * @return Formatted created_at timestamp.
     */
    public String getFormattedCreatedAt() {
        return formatTimestamp(created_at);
    }

    /**
     * Formats the updated_at timestamp to a standard string representation.
     * @return Formatted updated_at timestamp.
     */
    public String getFormattedUpdatedAt() {
        return formatTimestamp(updated_at);
    }

    /**
     * Formats a given timestamp to a standard string representation.
     * @param timestamp The timestamp to be formatted.
     * @return Formatted timestamp.
     */
    private String formatTimestamp(LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return timestamp.format(formatter);
    }

    /**
     * Sets the error message for the response.
     * @param message The error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}