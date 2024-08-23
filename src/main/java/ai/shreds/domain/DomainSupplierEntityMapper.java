package ai.shreds.domain;

import ai.shreds.shared.SharedSupplierDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainSupplierEntityMapper {

    private static final Logger logger = LoggerFactory.getLogger(DomainSupplierEntityMapper.class);

    /**
     * Converts a DomainSupplierEntity to a SharedSupplierDTO.
     * @param entity the DomainSupplierEntity to convert.
     * @return the converted SharedSupplierDTO.
     */
    public SharedSupplierDTO toDTO(DomainSupplierEntity entity) {
        if (entity == null) {
            logger.warn("Attempted to convert a null DomainSupplierEntity to SharedSupplierDTO");
            return null;
        }
        logger.info("Converting DomainSupplierEntity to SharedSupplierDTO: {}", entity);
        return SharedSupplierDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .contactInfo(entity.getContactInfo())
                .address(entity.getAddress())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    /**
     * Converts a SharedSupplierDTO to a DomainSupplierEntity.
     * @param dto the SharedSupplierDTO to convert.
     * @return the converted DomainSupplierEntity.
     */
    public DomainSupplierEntity toEntity(SharedSupplierDTO dto) {
        if (dto == null) {
            logger.warn("Attempted to convert a null SharedSupplierDTO to DomainSupplierEntity");
            return null;
        }
        logger.info("Converting SharedSupplierDTO to DomainSupplierEntity: {}", dto);
        return DomainSupplierEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .contactInfo(dto.getContactInfo())
                .address(dto.getAddress())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}