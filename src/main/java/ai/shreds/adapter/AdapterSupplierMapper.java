package ai.shreds.adapter;

import ai.shreds.domain.DomainSupplierEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AdapterSupplierMapper {

    public AdapterSupplierResponseDTO toDTO(DomainSupplierEntity entity) {
        if (entity == null) {
            return null;
        }
        return AdapterSupplierResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .contact_info(entity.getContact_info())
                .address(entity.getAddress())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .due_date(entity.getDue_date())
                .company_name(entity.getCompany_name())
                .build();
    }

    public DomainSupplierEntity toEntity(AdapterSupplierResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        return DomainSupplierEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .contact_info(dto.getContact_info())
                .address(dto.getAddress())
                .created_at(dto.getCreated_at())
                .updated_at(dto.getUpdated_at())
                .due_date(dto.getDue_date())
                .company_name(dto.getCompany_name())
                .build();
    }
}