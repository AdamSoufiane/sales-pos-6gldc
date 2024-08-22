package ai.shreds.domain;

import ai.shreds.shared.AdapterCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a category entity in the domain layer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainCategoryEntity {

    /**
     * Unique identifier for the category.
     */
    private UUID id;

    /**
     * Name of the category.
     */
    private String name;

    /**
     * Optional description of the category.
     */
    private Optional<String> description;

    /**
     * Identifier of the parent category (can be null or reference the id of another category).
     */
    private Optional<UUID> categoryId;

    /**
     * Timestamp when the category was created (auto-generated).
     */
    private Timestamp createdAt;

    /**
     * Timestamp when the category was last updated (auto-generated).
     */
    private Timestamp updatedAt;

    /**
     * Converts this entity to an AdapterCategoryDTO.
     *
     * @return AdapterCategoryDTO object
     */
    public AdapterCategoryDTO toAdapterCategoryDTO() {
        return AdapterCategoryDTO.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description.orElse(null))
                .categoryId(this.categoryId.orElse(null))
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}