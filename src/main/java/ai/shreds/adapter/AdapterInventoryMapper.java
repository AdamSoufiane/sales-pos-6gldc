package ai.shreds.adapter;

import ai.shreds.shared.SharedProductDomainEntity;
import ai.shreds.shared.SharedInventoryDomainEntity;
import ai.shreds.shared.SharedAlertDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between domain entities and DTOs related to inventory.
 */
@Mapper
public interface AdapterInventoryMapper {

    AdapterInventoryMapper INSTANCE = Mappers.getMapper(AdapterInventoryMapper.class);

    /**
     * Converts a SharedProductDomainEntity to a SharedInventoryDomainEntity.
     * @param event the product domain entity
     * @return the corresponding inventory domain entity
     */
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "qteAlert", target = "qteAlert")
    @Mapping(source = "lastUpdated", target = "lastUpdated")
    SharedInventoryDomainEntity toInventoryDomainEntity(SharedProductDomainEntity event);

    /**
     * Converts a SharedProductDomainEntity to a SharedAlertDTO.
     * @param event the product domain entity
     * @return the corresponding alert DTO
     */
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "name", target = "alertMessage")
    @Mapping(source = "qteAlert", target = "threshold")
    @Mapping(source = "quantity", target = "currentQuantity")
    SharedAlertDTO toAlertDTO(SharedProductDomainEntity event);
}