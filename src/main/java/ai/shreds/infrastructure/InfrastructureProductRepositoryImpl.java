package ai.shreds.infrastructure; 
  
 import ai.shreds.shared.SharedProductDomainEntity; 
 import ai.shreds.infrastructure.InfrastructureProductRepositoryPort; 
 import org.springframework.stereotype.Repository; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
 import java.util.UUID; 
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.NoArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Repository 
 @NoArgsConstructor 
 @Getter 
 @Setter 
 public class InfrastructureProductRepositoryImpl implements InfrastructureProductRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureProductRepositoryImpl.class); 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     /** 
      * Finds a product by its unique identifier. 
      * @param id the unique identifier of the product 
      * @return the product entity if found, otherwise null 
      */ 
     @Override 
     public SharedProductDomainEntity findById(UUID id) { 
         try { 
             logger.info("Finding product with ID: {}", id); 
             return entityManager.find(SharedProductDomainEntity.class, id); 
         } catch (Exception e) { 
             logger.error("Error finding product with ID: {}", id, e); 
             return null; 
         } 
     } 
  
     /** 
      * Saves a new or updated product to the database. 
      * @param product the product entity to be saved 
      */ 
     @Override 
     @Transactional 
     public void save(SharedProductDomainEntity product) { 
         try { 
             if (entityManager.find(SharedProductDomainEntity.class, product.getProductId()) == null) { 
                 logger.info("Persisting new product with ID: {}", product.getProductId()); 
                 entityManager.persist(product); 
             } else { 
                 logger.info("Merging existing product with ID: {}", product.getProductId()); 
                 entityManager.merge(product); 
             } 
         } catch (Exception e) { 
             logger.error("Error saving product with ID: {}", product.getProductId(), e); 
         } 
     } 
  
     /** 
      * Deletes a product by its unique identifier. 
      * @param id the unique identifier of the product 
      */ 
     @Override 
     @Transactional 
     public void deleteById(UUID id) { 
         try { 
             SharedProductDomainEntity product = entityManager.find(SharedProductDomainEntity.class, id); 
             if (product != null) { 
                 logger.info("Removing product with ID: {}", id); 
                 entityManager.remove(product); 
             } 
         } catch (Exception e) { 
             logger.error("Error deleting product with ID: {}", id, e); 
         } 
     } 
 } 
 