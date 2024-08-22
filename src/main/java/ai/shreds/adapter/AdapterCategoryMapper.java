package ai.shreds.adapter;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.adapter.AdapterCategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AdapterCategoryMapper {

    /**
     * Converts a DomainCategoryEntity to an AdapterCategoryResponseDTO.
     *
     * @param entity the domain entity to convert
     * @return the corresponding AdapterCategoryResponseDTO
     */
    public AdapterCategoryResponseDTO toResponseDTO(DomainCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return AdapterCategoryResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .categoryId(entity.getCategoryId() != null ? entity.getCategoryId().getId() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    /**
     * Converts an AdapterCategoryResponseDTO to a DomainCategoryEntity.
     *
     * @param dto the DTO to convert
     * @return the corresponding DomainCategoryEntity
     */
    public DomainCategoryEntity toEntity(AdapterCategoryResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        DomainCategoryEntity categoryEntity = new DomainCategoryEntity();
        categoryEntity.setId(dto.getId());
        categoryEntity.setName(dto.getName());
        categoryEntity.setDescription(dto.getDescription());
        // Assuming you have a method to fetch DomainCategoryEntity by ID for categoryId
        if (dto.getCategoryId() != null) {
            DomainCategoryEntity parentCategory = new DomainCategoryEntity();
            parentCategory.setId(dto.getCategoryId());
            categoryEntity.setCategoryId(parentCategory);
        }
        categoryEntity.setCreatedAt(dto.getCreatedAt());
        categoryEntity.setUpdatedAt(dto.getUpdatedAt());
        return categoryEntity;
    }
}