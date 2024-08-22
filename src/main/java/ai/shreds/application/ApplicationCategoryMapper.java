package ai.shreds.application.mapper;

import ai.shreds.adapter.dto.AdapterCategoryResponseDTO;
import ai.shreds.domain.entity.DomainCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCategoryMapper {

    public DomainCategoryEntity toDomain(AdapterCategoryResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        DomainCategoryEntity entity = new DomainCategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategoryId(dto.getCategoryId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    public AdapterCategoryResponseDTO toDTO(DomainCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        AdapterCategoryResponseDTO dto = new AdapterCategoryResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategoryId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
}