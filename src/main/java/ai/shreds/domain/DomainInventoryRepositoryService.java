package ai.shreds.domain; 
  
 import ai.shreds.infrastructure.InfrastructureInventoryRepositoryImpl; 
 import ai.shreds.infrastructure.InfrastructureException; 
 import ai.shreds.infrastructure.InfrastructureDatabaseInventoryModel; 
 import ai.shreds.infrastructure.InfrastructureInventoryRepositoryImplMapper; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
  
 @Slf4j 
 @Service 
 @RequiredArgsConstructor 
 public class DomainInventoryRepositoryService implements DomainInventoryRepositoryPort { 
  
     private final InfrastructureInventoryRepositoryImpl inventoryRepository; 
     private final InfrastructureInventoryRepositoryImplMapper inventoryMapper; 
  
     /** 
      * Save a new inventory entity or update an existing one. 
      * @param entity the inventory entity to be saved or updated 
      */ 
     @Override 
     public void save(DomainInventoryEntity entity) { 
         try { 
             InfrastructureDatabaseInventoryModel dbModel = inventoryMapper.mapToDatabaseModel(entity); 
             inventoryRepository.save(dbModel); 
             log.info("Successfully saved inventory entity with productId: {}", entity.getProductId()); 
         } catch (InfrastructureException e) { 
             log.error("Failed to save inventory entity with productId: {}", entity.getProductId(), e); 
             throw new DomainProductAddedEventException("Failed to save inventory entity", e); 
         } 
     } 
  
     /** 
      * Find an inventory entity by its product ID. 
      * @param productId the product ID of the inventory entity to be found 
      * @return the found inventory entity 
      */ 
     @Override 
     public DomainInventoryEntity findByProductId(String productId) { 
         try { 
             InfrastructureDatabaseInventoryModel dbModel = inventoryRepository.findByProductId(productId); 
             log.info("Successfully found inventory entity with productId: {}", productId); 
             return inventoryMapper.mapToDomainEntity(dbModel); 
         } catch (InfrastructureException e) { 
             log.error("Failed to find inventory entity by productId: {}", productId, e); 
             throw new DomainProductAddedEventException("Failed to find inventory entity by productId", e); 
         } 
     } 
 } 
 