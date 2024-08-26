package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainPurchaseTransactionEntity; 
 import ai.shreds.domain.DomainPurchaseTransactionPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import javax.persistence.Entity; 
 import javax.persistence.GeneratedValue; 
 import javax.persistence.GenerationType; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 @Repository 
 public class InfrastructurePurchaseTransactionRepositoryImpl implements DomainPurchaseTransactionPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructurePurchaseTransactionRepositoryImpl.class); 
  
     private final PurchaseTransactionJpaRepository purchaseTransactionJpaRepository; 
     private final InfrastructureEntityMapper entityMapper; 
  
     @Autowired 
     public InfrastructurePurchaseTransactionRepositoryImpl(PurchaseTransactionJpaRepository purchaseTransactionJpaRepository, InfrastructureEntityMapper entityMapper) { 
         this.purchaseTransactionJpaRepository = purchaseTransactionJpaRepository; 
         this.entityMapper = entityMapper; 
     } 
  
     @Override 
     public void savePurchaseTransaction(DomainPurchaseTransactionEntity transaction) { 
         try { 
             PurchaseTransactionEntity jpaEntity = entityMapper.mapDomainToInfrastructure(transaction); 
             purchaseTransactionJpaRepository.save(jpaEntity); 
             logger.info("Purchase transaction saved successfully: {}", transaction.getPurchaseNumber()); 
         } catch (Exception e) { 
             logger.error("Error saving purchase transaction: {}", transaction.getPurchaseNumber(), e); 
             throw new RuntimeException("Failed to save purchase transaction", e); 
         } 
     } 
 } 
  
 @Entity 
 @Table(name = "purchase_transaction") 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class PurchaseTransactionEntity { 
  
     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     private Long id; 
     private String purchaseNumber; 
     private String purchaseDate; 
     private String supplierId; 
 }