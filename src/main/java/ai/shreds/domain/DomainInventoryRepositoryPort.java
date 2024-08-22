package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedInventoryDomainEntity; 
 import java.util.UUID; 
  
 /** 
  * Interface for Inventory Repository Port in the Domain Layer. 
  * Provides methods for finding, saving, and deleting inventory records based on product's unique identifier. 
  */ 
 public interface DomainInventoryRepositoryPort { 
  
     /** 
      * Finds an inventory record by the product's unique identifier. 
      * 
      * @param productId the unique identifier of the product 
      * @return the inventory details of the specified product 
      */ 
     SharedInventoryDomainEntity findByProductId(UUID productId); 
  
     /** 
      * Saves a new or updated inventory record to the database. 
      * 
      * @param inventory the inventory details to be saved 
      */ 
     void save(SharedInventoryDomainEntity inventory); 
  
     /** 
      * Deletes an inventory record by the product's unique identifier. 
      * 
      * @param productId the unique identifier of the product 
      */ 
     void deleteByProductId(UUID productId); 
 } 
 