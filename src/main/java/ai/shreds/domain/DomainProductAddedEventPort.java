package ai.shreds.domain; 
  
 /** 
  * Port interface for processing 'ProductAdded' events. 
  * This interface defines the contract for handling 'ProductAdded' events, 
  * ensuring that the inventory is updated accordingly in the InventoryDB. 
  */ 
 public interface ProductAddedEventPort { 
     /** 
      * Processes the 'ProductAdded' event. 
      * @param event The event to be processed. 
      * @return The response after processing the event. 
      */ 
     DomainProductAddedEventResponse processEvent(DomainProductAddedEvent event); 
 } 
 