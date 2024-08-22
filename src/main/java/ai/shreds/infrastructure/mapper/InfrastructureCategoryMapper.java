package ai.shreds.infrastructure.mapper;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.adapter.AdapterCategoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between DomainCategoryEntity and AdapterCategoryResponseDTO.
 * Utilizes MapStruct for automatic mapping implementation.
 */
@Mapper(componentModel = "spring")
public interface InfrastructureCategoryMapper {

    InfrastructureCategoryMapper INSTANCE = Mappers.getMapper(InfrastructureCategoryMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    DomainCategoryEntity toEntity(AdapterCategoryResponseDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    AdapterCategoryResponseDTO toDto(DomainCategoryEntity entity);
}