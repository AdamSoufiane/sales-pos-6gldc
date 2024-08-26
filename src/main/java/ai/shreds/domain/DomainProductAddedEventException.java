package ai.shreds.domain; 
  
 import lombok.Getter; 
 import java.io.Serializable; 
  
 /** 
  * Exception thrown when an error occurs during the processing of a 'ProductAdded' event. 
  */ 
 @Getter 
 public class DomainProductAddedEventException extends RuntimeException implements Serializable { 
     private static final long serialVersionUID = 1L; 
     private final String message; 
     private final Throwable cause; 
  
     /** 
      * Constructs a new DomainProductAddedEventException with the specified detail message. 
      * 
      * @param message the detail message 
      */ 
     public DomainProductAddedEventException(String message) { 
         super(message); 
         this.message = message; 
         this.cause = null; 
     } 
  
     /** 
      * Constructs a new DomainProductAddedEventException with the specified detail message and cause. 
      * 
      * @param message the detail message 
      * @param cause the cause of the exception 
      */ 
     public DomainProductAddedEventException(String message, Throwable cause) { 
         super(message, cause); 
         this.message = message; 
         this.cause = cause; 
     } 
 }