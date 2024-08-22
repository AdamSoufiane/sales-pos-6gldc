package ai.shreds.shared;

import ai.shreds.adapter.AdapterProductAddedEventDTO;
import ai.shreds.adapter.AdapterProductUpdatedEventDTO;
import ai.shreds.adapter.AdapterProductDeletedEventDTO;
import ai.shreds.adapter.AdapterInventoryUpdateResponseDTO;
import ai.shreds.adapter.AdapterInventoryAlertResponseDTO;
import ai.shreds.domain.DomainInventoryEntity;
import ai.shreds.domain.DomainAlertDTO;
import ai.shreds.domain.DomainProductEntity;
import java.util.UUID;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SharedAdapterInventoryMapper {
    SharedAdapterInventoryMapper INSTANCE = Mappers.getMapper(SharedAdapterInventoryMapper.class);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    DomainProductEntity toDomainProductEntity(AdapterProductAddedEventDTO event);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    DomainProductEntity toDomainProductEntity(AdapterProductUpdatedEventDTO event);

    @Mapping(source = "productId", target = "productId")
    DomainProductEntity toDomainProductEntity(AdapterProductDeletedEventDTO event);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "qteAlert", target = "qteAlert")
    @Mapping(source = "lastUpdated", target = "lastUpdated")
    AdapterInventoryUpdateResponseDTO toAdapterInventoryUpdateResponseDTO(DomainInventoryEntity inventory);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "alertMessage", target = "alertMessage")
    @Mapping(source = "threshold", target = "threshold")
    @Mapping(source = "currentQuantity", target = "currentQuantity")
    AdapterInventoryAlertResponseDTO toAdapterInventoryAlertResponseDTO(DomainAlertDTO alert);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    SharedAdapterProductAddedEventDTO toSharedAdapterProductAddedEventDTO(DomainProductEntity product);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    SharedAdapterProductUpdatedEventDTO toSharedAdapterProductUpdatedEventDTO(DomainProductEntity product);

    @Mapping(source = "productId", target = "productId")
    SharedAdapterProductDeletedEventDTO toSharedAdapterProductDeletedEventDTO(DomainProductEntity product);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "qteAlert", target = "qteAlert")
    @Mapping(source = "lastUpdated", target = "lastUpdated")
    SharedAlertDTO toSharedAlertDTO(DomainInventoryEntity inventory);
}