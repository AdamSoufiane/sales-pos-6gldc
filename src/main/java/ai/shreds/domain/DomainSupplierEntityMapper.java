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
        SharedSupplierDTO dto = new SharedSupplierDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContact_info(entity.getContact_info());
        dto.setAddress(entity.getAddress());
        dto.setCompany_name(entity.getCompany_name());
        dto.setDue_date(entity.getDue_date());
        dto.setCreated_at(entity.getCreated_at());
        dto.setUpdated_at(entity.getUpdated_at());
        return dto;
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
        DomainSupplierEntity entity = new DomainSupplierEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setContact_info(dto.getContact_info());
        entity.setAddress(dto.getAddress());
        entity.setCompany_name(dto.getCompany_name());
        entity.setDue_date(dto.getDue_date());
        entity.setCreated_at(dto.getCreated_at());
        entity.setUpdated_at(dto.getUpdated_at());
        return entity;
    }
}