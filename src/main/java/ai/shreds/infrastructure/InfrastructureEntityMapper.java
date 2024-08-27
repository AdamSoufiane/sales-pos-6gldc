package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainPurchaseTransactionEntity;
import ai.shreds.adapter.AdapterPurchaseRequestDTO;
import ai.shreds.adapter.AdapterProductDTO;
import ai.shreds.adapter.AdapterPurchaseDataDTO;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InfrastructureEntityMapper {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureEntityMapper.class);

    /**
     * Maps a DomainPurchaseTransactionEntity to an InfrastructurePurchaseTransactionRepositoryImpl.
     * @param domainEntity the domain entity to be mapped
     * @return the mapped infrastructure entity
     */
    public InfrastructurePurchaseTransactionEntity mapDomainToInfrastructure(DomainPurchaseTransactionEntity domainEntity) {
        if (domainEntity == null) {
            throw new IllegalArgumentException("Domain entity cannot be null");
        }
        logger.debug("Mapping DomainPurchaseTransactionEntity to InfrastructurePurchaseTransactionEntity");
        InfrastructurePurchaseTransactionEntity infraEntity = new InfrastructurePurchaseTransactionEntity();
        infraEntity.setPurchaseNumber(domainEntity.getPurchaseNumber());
        infraEntity.setPurchaseDate(domainEntity.getPurchaseDate().toString()); // Assuming conversion to String
        infraEntity.setSupplierId(domainEntity.getSupplierId());
        if (domainEntity.getProducts() != null) {
            infraEntity.setProducts(domainEntity.getProducts().stream()
                    .map(this::mapProductToInfrastructure)
                    .collect(Collectors.toList()));
        }
        return infraEntity;
    }

    /**
     * Maps a DomainProductEntity to an InfrastructureProduct.
     * @param domainProduct the domain product to be mapped
     * @return the mapped infrastructure product
     */
    private InfrastructureProduct mapProductToInfrastructure(DomainProductEntity domainProduct) {
        if (domainProduct == null) {
            throw new IllegalArgumentException("Domain product cannot be null");
        }
        logger.debug("Mapping DomainProductEntity to InfrastructureProduct");
        InfrastructureProduct infraProduct = new InfrastructureProduct();
        infraProduct.setProductId(domainProduct.getId());
        infraProduct.setPurchasePrice(domainProduct.getPurchasePrice());
        infraProduct.setQuantity(domainProduct.getQuantity());
        return infraProduct;
    }

    /**
     * Maps an AdapterPurchaseRequestDTO to a DomainPurchaseTransactionEntity.
     * @param request the adapter request DTO to be mapped
     * @return the mapped domain entity
     */
    public DomainPurchaseTransactionEntity mapAdapterToDomain(AdapterPurchaseRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request DTO cannot be null");
        }
        DomainPurchaseTransactionEntity domainEntity = new DomainPurchaseTransactionEntity();
        domainEntity.setPurchaseNumber(request.getPurchaseNumber());
        domainEntity.setPurchaseDate(request.getPurchaseDate());
        domainEntity.setSupplierId(request.getSupplierId());
        domainEntity.setProducts(request.getProducts().stream().map(this::mapAdapterProductToDomain).collect(Collectors.toList()));
        return domainEntity;
    }

    /**
     * Maps an AdapterProductDTO to a DomainProductEntity.
     * @param productDTO the adapter product DTO to be mapped
     * @return the mapped domain product entity
     */
    private DomainProductEntity mapAdapterProductToDomain(AdapterProductDTO productDTO) {
        if (productDTO == null) {
            throw new IllegalArgumentException("Product DTO cannot be null");
        }
        DomainProductEntity domainProduct = new DomainProductEntity();
        domainProduct.setId(productDTO.getProductId());
        domainProduct.setPurchasePrice(productDTO.getPurchasePrice());
        domainProduct.setQuantity(productDTO.getQuantity());
        return domainProduct;
    }

    /**
     * Maps a DomainPurchaseTransactionEntity to an AdapterPurchaseDataDTO.
     * @param domainEntity the domain entity to be mapped
     * @return the mapped adapter data DTO
     */
    public AdapterPurchaseDataDTO mapDomainToAdapter(DomainPurchaseTransactionEntity domainEntity) {
        if (domainEntity == null) {
            throw new IllegalArgumentException("Domain entity cannot be null");
        }
        AdapterPurchaseDataDTO adapterData = new AdapterPurchaseDataDTO();
        adapterData.setPurchaseId(domainEntity.getPurchaseNumber());
        adapterData.setPurchaseNumber(domainEntity.getPurchaseNumber());
        adapterData.setPurchaseDate(domainEntity.getPurchaseDate().toString());
        adapterData.setSupplierId(domainEntity.getSupplierId());
        adapterData.setProducts(domainEntity.getProducts().stream().map(this::mapDomainProductToAdapter).collect(Collectors.toList()));
        return adapterData;
    }

    /**
     * Maps a DomainProductEntity to an AdapterProductDTO.
     * @param domainProduct the domain product to be mapped
     * @return the mapped adapter product DTO
     */
    private AdapterProductDTO mapDomainProductToAdapter(DomainProductEntity domainProduct) {
        if (domainProduct == null) {
            throw new IllegalArgumentException("Domain product cannot be null");
        }
        AdapterProductDTO adapterProduct = new AdapterProductDTO();
        adapterProduct.setProductId(domainProduct.getId());
        adapterProduct.setPurchasePrice(domainProduct.getPurchasePrice());
        adapterProduct.setQuantity(domainProduct.getQuantity());
        return adapterProduct;
    }
}