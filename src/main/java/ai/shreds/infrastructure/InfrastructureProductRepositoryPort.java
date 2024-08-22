package ai.shreds.infrastructure; 
  
 import ai.shreds.shared.SharedProductDomainEntity; 
 import java.util.UUID; 
  
 /** 
  * InfrastructureProductRepositoryPort interface defines the methods required to interact with the Product entity in the database. 
  */ 
 public interface InfrastructureProductRepositoryPort { 
  
     /** 
      * Finds a product by its unique identifier. 
      * 
      * @param id the unique identifier of the product 
      * @return the product domain entity 
      */ 
     SharedProductDomainEntity findById(UUID id); 
  
     /** 
      * Saves a new or updated product to the database. 
      * 
      * @param product the product domain entity to save 
      */ 
     void save(SharedProductDomainEntity product); 
  
     /** 
      * Deletes a product by its unique identifier. 
      * 
      * @param id the unique identifier of the product 
      */ 
     void deleteById(UUID id); 
 } 
  
 // Note: Use Lombok annotations for getters and setters in SharedProductDomainEntity