package ai.shreds.domain; 
  
 import java.util.Optional; 
 import java.util.UUID; 
  
 /** 
  * Interface for handling database operations for product entities. 
  */ 
 public interface DomainProductRepositoryPort { 
     /** 
      * Persists the given product entity to the database. 
      * 
      * @param product the product entity to save 
      */ 
     void save(DomainProductEntity product); 
  
     /** 
      * Retrieves a product entity by its unique identifier from the database. 
      * 
      * @param id the unique identifier of the product 
      * @return an Optional containing the found product entity, or an empty Optional if not found 
      */ 
     Optional<DomainProductEntity> findById(UUID id); 
  
     /** 
      * Removes the product entity with the given identifier from the database. 
      * 
      * @param id the unique identifier of the product to delete 
      */ 
     void deleteById(UUID id); 
 }