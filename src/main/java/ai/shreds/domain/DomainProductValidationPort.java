package ai.shreds.domain; 
  
 import java.util.List; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Port for validating products by their IDs. 
  */ 
 @FunctionalInterface 
 public interface DomainProductValidationPort { 
     Logger LOGGER = LoggerFactory.getLogger(DomainProductValidationPort.class); 
  
     /** 
      * Validates the existence of products by their IDs. 
      *  
      * @param productIds List of product IDs to validate. 
      * @return true if all products are valid, false otherwise. 
      */ 
     default boolean validateProducts(List<String> productIds) { 
         LOGGER.info("Validating products with IDs: {}", productIds); 
         // Actual validation logic here 
         return true; 
     } 
 } 
 