package ai.shreds.adapter;

import ai.shreds.domain.DomainSupplierEntity;
import ai.shreds.shared.SharedSupplierDTO;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AdapterSupplierMapper {

    public AdapterSupplierResponseDTO toDTO(DomainSupplierEntity entity) {
        if (entity == null) {
            return null;
        }
        AdapterSupplierResponseDTO dto = new AdapterSupplierResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContact_info(entity.getContact_info());
        dto.setAddress(entity.getAddress());
        dto.setCreated_at(entity.getCreated_at());
        dto.setUpdated_at(entity.getUpdated_at());
        return dto;
    }

    public DomainSupplierEntity toEntity(AdapterSupplierResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        DomainSupplierEntity entity = new DomainSupplierEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setContact_info(dto.getContact_info());
        entity.setAddress(dto.getAddress());
        entity.setCreated_at(dto.getCreated_at());
        entity.setUpdated_at(dto.getUpdated_at());
        return entity;
    }

    public SharedSupplierDTO toSharedSupplierDTO(DomainSupplierEntity entity) {
        if (entity == null) {
            return null;
        }
        SharedSupplierDTO dto = new SharedSupplierDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContact_info(entity.getContact_info());
        dto.setAddress(entity.getAddress());
        dto.setCreated_at(entity.getCreated_at());
        dto.setUpdated_at(entity.getUpdated_at());
        return dto;
    }

    public AdapterSupplierResponseDTO toAdapterSupplierResponseDTO(SharedSupplierDTO dto) {
        if (dto == null) {
            return null;
        }
        AdapterSupplierResponseDTO responseDTO = new AdapterSupplierResponseDTO();
        responseDTO.setId(dto.getId());
        responseDTO.setName(dto.getName());
        responseDTO.setContact_info(dto.getContact_info());
        responseDTO.setAddress(dto.getAddress());
        responseDTO.setCreated_at(dto.getCreated_at());
        responseDTO.setUpdated_at(dto.getUpdated_at());
        return responseDTO;
    }
}