package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.SharedSupplierDTO; 
 import java.util.List; 
  
 /** 
  * DomainSupplierRepositoryPort is an interface in the domain layer that defines the contract for data access operations related to suppliers. 
  */ 
 public interface DomainSupplierRepositoryPort { 
     /** 
      * Retrieves all supplier records from the database, with optional filtering criteria. 
      * 
      * @param params the filtering criteria 
      * @return a list of supplier entities 
      * @throws DataAccessException if there is an error during retrieval 
      */ 
     List<DomainSupplierEntity> findAll(SharedRequestParams params) throws DataAccessException; 
  
     /** 
      * Retrieves a specific supplier record by its unique ID. 
      * 
      * @param id the unique identifier of the supplier 
      * @return the supplier entity 
      * @throws DataAccessException if there is an error during retrieval or if the supplier is not found 
      */ 
     DomainSupplierEntity findById(Long id) throws DataAccessException; 
 }