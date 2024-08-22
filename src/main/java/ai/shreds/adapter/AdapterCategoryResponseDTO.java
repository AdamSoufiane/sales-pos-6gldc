package ai.shreds.adapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) for transferring category data between layers.
 * This DTO is used within the adapter layer to encapsulate category data
 * and provide it in a structured format for API responses.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdapterCategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp updatedAt;
}