package ai.shreds.infrastructure; 
  
 import ai.shreds.shared.SharedInventoryDomainEntity; 
 import java.util.UUID; 
  
 /** 
  * Interface for Inventory Repository operations. 
  */ 
 public interface InfrastructureInventoryRepositoryPort { 
  
     /** 
      * Finds inventory information by the product's unique identifier. 
      * 
      * @param productId the unique identifier of the product 
      * @return the inventory information of the product 
      * @throws InventoryNotFoundException if the inventory information is not found 
      */ 
     SharedInventoryDomainEntity findByProductId(UUID productId) throws InventoryNotFoundException; 
  
     /** 
      * Saves new or updated inventory information to the database. 
      * 
      * @param inventory the inventory information to be saved 
      */ 
     void save(SharedInventoryDomainEntity inventory); 
  
     /** 
      * Deletes inventory information by the product's unique identifier. 
      * 
      * @param productId the unique identifier of the product 
      */ 
     void deleteByProductId(UUID productId); 
 } 
  
 /** 
  * Custom exception to be thrown when inventory information is not found. 
  */ 
 class InventoryNotFoundException extends Exception { 
     public InventoryNotFoundException(String message) { 
         super(message); 
     } 
 } 
 