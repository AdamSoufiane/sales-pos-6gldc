package ai.shreds.application; 
  
 import ai.shreds.shared.AdapterPurchaseRequestDTO; 
 import ai.shreds.shared.AdapterPurchaseResponseDTO; 
  
 /** 
  * ApplicationPurchaseServiceInputPort defines the contract for processing purchase transactions. 
  */ 
 public interface ApplicationPurchaseServiceInputPort { 
     /** 
      * Processes a purchase transaction. 
      * 
      * @param request the purchase request DTO containing transaction details 
      * @return the response DTO containing the result of the transaction processing 
      */ 
     AdapterPurchaseResponseDTO processPurchaseTransaction(AdapterPurchaseRequestDTO request); 
 } 
 