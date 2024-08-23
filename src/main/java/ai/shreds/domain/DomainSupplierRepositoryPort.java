package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainSupplierEntity; 
  
 /** 
  * Interface for Supplier Repository Port. 
  * Provides methods for interacting with the Supplier entity in the database. 
  */ 
 public interface DomainSupplierRepositoryPort { 
     /** 
      * Saves a supplier entity to the database. 
      * @param supplier the supplier entity to save 
      */ 
     void save(DomainSupplierEntity supplier); 
  
     /** 
      * Finds a supplier entity by its ID. 
      * @param id the ID of the supplier entity 
      * @return the found supplier entity 
      */ 
     DomainSupplierEntity findById(Long id); 
  
     /** 
      * Deletes a supplier entity by its ID. 
      * @param id the ID of the supplier entity 
      */ 
     void deleteById(Long id); 
  
     /** 
      * Checks if a supplier entity exists by its ID. 
      * @param id the ID of the supplier entity 
      * @return true if the supplier entity exists, false otherwise 
      */ 
     boolean existsById(Long id); 
 }