package ai.shreds.application.mapper;

import ai.shreds.adapter.AdapterCategoryResponseDTO;
import ai.shreds.domain.DomainCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCategoryMapper {

    public DomainCategoryEntity toEntity(AdapterCategoryResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        DomainCategoryEntity entity = new DomainCategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        // Assuming you have a method to fetch DomainCategoryEntity by ID for categoryId
        if (dto.getCategoryId() != null) {
            DomainCategoryEntity parentCategory = new DomainCategoryEntity();
            parentCategory.setId(dto.getCategoryId());
            entity.setCategoryId(parentCategory);
        }
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    public AdapterCategoryResponseDTO toResponseDTO(DomainCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        AdapterCategoryResponseDTO dto = new AdapterCategoryResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategoryId() != null ? entity.getCategoryId().getId() : null);
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}