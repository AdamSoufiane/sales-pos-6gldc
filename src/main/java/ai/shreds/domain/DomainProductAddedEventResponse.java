package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Represents the response for the 'ProductAdded' event in the domain layer. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainProductAddedEventResponse { 
     private String message; 
  
     /** 
      * Gets the response message. 
      *  
      * @return the response message 
      */ 
     public String getMessage() { 
         return message; 
     } 
  
     /** 
      * Sets the response message. 
      *  
      * @param message the response message to set 
      */ 
     public void setMessage(String message) { 
         this.message = message; 
     } 
 }