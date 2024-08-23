package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterDeleteSupplierResponse; 
  
 /** 
  * Interface for deleting a supplier. 
  */ 
 public interface ApplicationDeleteSupplierInputPort { 
     /** 
      * Deletes a supplier by ID. 
      * 
      * @param id the ID of the supplier to delete 
      * @return the response after attempting to delete the supplier 
      */ 
     AdapterDeleteSupplierResponse deleteSupplier(Long id); 
  
     /** 
      * Handles exceptions specific to supplier deletion. 
      * 
      * @param e the exception to handle 
      */ 
     void handleDeleteSupplierException(Exception e); 
 } 
  
 // Implementation Note: Use Lombok annotations where applicable. 
 // Implementation Note: Ensure proper exception handling by defining specific exceptions for scenarios like supplier not found. 
 // Implementation Note: Unit tests should be created for this interface.