package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryEntity; 
 import ai.shreds.infrastructure.model.InfrastructureDatabaseInventoryModel; 
 import org.springframework.stereotype.Component; 
 import lombok.Data; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Mapper class to convert between DomainInventoryEntity and InfrastructureDatabaseInventoryModel. 
  */ 
 @Data 
 @Component 
 public class InfrastructureInventoryRepositoryImplMapper { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImplMapper.class); 
  
     /** 
      * Maps a DomainInventoryEntity to an InfrastructureDatabaseInventoryModel. 
      *  
      * @param domainEntity the domain entity to be mapped 
      * @return the corresponding InfrastructureDatabaseInventoryModel 
      */ 
     public InfrastructureDatabaseInventoryModel mapToDatabaseModel(DomainInventoryEntity domainEntity) { 
         try { 
             if (domainEntity == null) { 
                 logger.warn("Attempted to map a null DomainInventoryEntity to InfrastructureDatabaseInventoryModel."); 
                 return null; 
             } 
             InfrastructureDatabaseInventoryModel databaseModel = new InfrastructureDatabaseInventoryModel(); 
             databaseModel.setProductId(domainEntity.getProductId()); 
             databaseModel.setInitialQuantity(domainEntity.getInitialQuantity()); 
             databaseModel.setCreationTime(domainEntity.getCreationTime()); 
             databaseModel.setAlertQuantity(domainEntity.getAlertQuantity()); 
             databaseModel.setWarehouseLocation(domainEntity.getWarehouseLocation()); 
             logger.info("Mapped DomainInventoryEntity to InfrastructureDatabaseInventoryModel: {}", databaseModel); 
             return databaseModel; 
         } catch (Exception e) { 
             logger.error("Error mapping DomainInventoryEntity to InfrastructureDatabaseInventoryModel", e); 
             throw e; 
         } 
     } 
  
     /** 
      * Maps an InfrastructureDatabaseInventoryModel to a DomainInventoryEntity. 
      *  
      * @param databaseModel the database model to be mapped 
      * @return the corresponding DomainInventoryEntity 
      */ 
     public DomainInventoryEntity mapToDomainEntity(InfrastructureDatabaseInventoryModel databaseModel) { 
         try { 
             if (databaseModel == null) { 
                 logger.warn("Attempted to map a null InfrastructureDatabaseInventoryModel to DomainInventoryEntity."); 
                 return null; 
             } 
             DomainInventoryEntity domainEntity = new DomainInventoryEntity(); 
             domainEntity.setProductId(databaseModel.getProductId()); 
             domainEntity.setInitialQuantity(databaseModel.getInitialQuantity()); 
             domainEntity.setCreationTime(databaseModel.getCreationTime()); 
             domainEntity.setAlertQuantity(databaseModel.getAlertQuantity()); 
             domainEntity.setWarehouseLocation(databaseModel.getWarehouseLocation()); 
             logger.info("Mapped InfrastructureDatabaseInventoryModel to DomainInventoryEntity: {}", domainEntity); 
             return domainEntity; 
         } catch (Exception e) { 
             logger.error("Error mapping InfrastructureDatabaseInventoryModel to DomainInventoryEntity", e); 
             throw e; 
         } 
     } 
 } 
 