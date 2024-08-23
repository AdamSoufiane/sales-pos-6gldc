package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterUpdateSupplierRequest; 
 import ai.shreds.shared.SharedSupplierDTO; 
  
 /** 
  * Interface for updating a supplier in the system. 
  * This interface defines the contract for updating an existing supplier record. 
  */ 
 public interface ApplicationUpdateSupplierInputPort { 
     /** 
      * Updates an existing supplier record. 
      *  
      * @param id The ID of the supplier to be updated. 
      * @param request The request object containing the updated supplier details. 
      * @return The updated supplier details as a SharedSupplierDTO. 
      */ 
     SharedSupplierDTO updateSupplier(Long id, AdapterUpdateSupplierRequest request); 
 } 
 