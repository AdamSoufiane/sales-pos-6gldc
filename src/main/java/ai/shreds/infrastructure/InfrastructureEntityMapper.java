package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainPurchaseTransactionEntity; 
 import org.springframework.stereotype.Component; 
 import java.util.stream.Collectors; 
 import lombok.Getter; 
 import lombok.Setter; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Component 
 @Getter 
 @Setter 
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
         logger.info("Mapping DomainPurchaseTransactionEntity to InfrastructurePurchaseTransactionRepositoryImpl"); 
         InfrastructurePurchaseTransactionRepositoryImpl infraEntity = new InfrastructurePurchaseTransactionRepositoryImpl(); 
         infraEntity.setPurchaseNumber(domainEntity.getPurchaseNumber()); 
         infraEntity.setPurchaseDate(domainEntity.getPurchaseDate()); 
         infraEntity.setSupplierId(domainEntity.getSupplierId()); 
         infraEntity.setProducts(domainEntity.getProducts().stream() 
                 .map(this::mapProductToInfrastructure) 
                 .collect(Collectors.toList())); 
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
         logger.info("Mapping DomainProductEntity to InfrastructureProduct"); 
         InfrastructureProduct infraProduct = new InfrastructureProduct(); 
         infraProduct.setProductId(domainProduct.getId()); 
         infraProduct.setPurchasePrice(domainProduct.getPurchasePrice()); 
         infraProduct.setQuantity(domainProduct.getQuantity()); 
         return infraProduct; 
     } 
 } 
 