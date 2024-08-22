package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 import java.util.Optional; 
 import java.util.UUID; 
  
 /** 
  * Implementation of DomainProductRepositoryPort using Spring Data JPA. 
  */ 
 @Repository 
 public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort { 
  
     private final ProductJpaRepository productJpaRepository; 
  
     @Autowired 
     public InfrastructureProductRepositoryImpl(ProductJpaRepository productJpaRepository) { 
         this.productJpaRepository = productJpaRepository; 
     } 
  
     /** 
      * Saves a product entity to the database. 
      *  
      * @param product the product entity to save 
      */ 
     @Override 
     @Transactional 
     public void save(DomainProductEntity product) { 
         productJpaRepository.save(product); 
     } 
  
     /** 
      * Finds a product entity by its ID. 
      *  
      * @param id the UUID of the product entity 
      * @return an Optional containing the product entity if found, or empty if not found 
      */ 
     @Override 
     @Transactional(readOnly = true) 
     public Optional<DomainProductEntity> findById(UUID id) { 
         return productJpaRepository.findById(id); 
     } 
  
     /** 
      * Deletes a product entity by its ID. 
      *  
      * @param id the UUID of the product entity to delete 
      */ 
     @Override 
     @Transactional 
     public void deleteById(UUID id) { 
         productJpaRepository.deleteById(id); 
     } 
 } 
  
 interface ProductJpaRepository extends JpaRepository<DomainProductEntity, UUID> {} 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class DomainProductEntity { 
     private UUID id; 
     private String name; 
     private String description; 
     private BigDecimal price; 
     private UUID categoryId; 
     private Timestamp createdAt; 
     private Timestamp updatedAt; 
 }