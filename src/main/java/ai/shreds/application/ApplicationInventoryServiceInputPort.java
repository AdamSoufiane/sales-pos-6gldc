package ai.shreds.application; 
  
 import ai.shreds.shared.SharedProductAddedEventDTO; 
 import ai.shreds.shared.SharedProductUpdatedEventDTO; 
 import ai.shreds.shared.SharedProductDeletedEventDTO; 
  
 /** 
  * Interface for handling inventory-related events in the application layer. 
  */ 
 public interface ApplicationInventoryServiceInputPort { 
     /** 
      * Processes the ProductAdded event. 
      * 
      * @param event the event data transfer object containing product details 
      */ 
     void processProductAddedEvent(SharedProductAddedEventDTO event); 
  
     /** 
      * Processes the ProductUpdated event. 
      * 
      * @param event the event data transfer object containing updated product details 
      */ 
     void processProductUpdatedEvent(SharedProductUpdatedEventDTO event); 
  
     /** 
      * Processes the ProductDeleted event. 
      * 
      * @param event the event data transfer object containing the product ID 
      */ 
     void processProductDeletedEvent(SharedProductDeletedEventDTO event); 
 } 
 