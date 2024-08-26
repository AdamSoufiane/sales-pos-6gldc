package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainPurchaseTransactionEntity;
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
    public InfrastructurePurchaseTransactionRepositoryImpl mapDomainToInfrastructure(DomainPurchaseTransactionEntity domainEntity) {
        if (domainEntity == null) {
            throw new IllegalArgumentException("Domain entity cannot be null");
        }
        logger.debug("Mapping DomainPurchaseTransactionEntity to InfrastructurePurchaseTransactionRepositoryImpl");
        InfrastructurePurchaseTransactionRepositoryImpl infraEntity = new InfrastructurePurchaseTransactionRepositoryImpl();
        infraEntity.setPurchaseNumber(domainEntity.getPurchaseNumber());
        infraEntity.setPurchaseDate(domainEntity.getPurchaseDate());
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
}

class InfrastructurePurchaseTransactionRepositoryImpl {
    private String purchaseNumber;
    private String purchaseDate;
    private String supplierId;
    private List<InfrastructureProduct> products;

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public void setProducts(List<InfrastructureProduct> products) {
        this.products = products;
    }
}

class InfrastructureProduct {
    private String productId;
    private double purchasePrice;
    private int quantity;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}