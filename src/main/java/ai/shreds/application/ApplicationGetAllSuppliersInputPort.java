package ai.shreds.application; 
  
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.SharedSupplierDTO; 
 import java.util.List; 
  
 /** 
  * Port interface for retrieving all suppliers with optional filtering criteria. 
  */ 
 public interface SupplierServicePort { 
     /** 
      * Retrieves a list of suppliers based on the provided filtering criteria. 
      * 
      * @param params the filtering criteria 
      * @return a list of suppliers matching the criteria 
      */ 
     List<SharedSupplierDTO> getAllSuppliers(SharedRequestParams params); 
 }