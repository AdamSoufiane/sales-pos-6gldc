package ai.shreds.infrastructure; 
  
 import lombok.Getter; 
 import lombok.Setter; 
  
 /** 
  * Exception class to handle infrastructure-related exceptions. 
  */ 
 @Getter 
 @Setter 
 public class InfrastructureException extends RuntimeException { 
     private String message; 
     private Throwable cause; 
  
     /** 
      * Default constructor for serialization/deserialization purposes. 
      */ 
     public InfrastructureException() { 
         // Default constructor for serialization/deserialization purposes. 
     } 
  
     /** 
      * Constructor with message parameter. 
      * 
      * @param message the detail message. 
      */ 
     public InfrastructureException(String message) { 
         super(message); 
         this.message = message; 
     } 
  
     /** 
      * Constructor with message and cause parameters. 
      * 
      * @param message the detail message. 
      * @param cause   the cause of the exception. 
      */ 
     public InfrastructureException(String message, Throwable cause) { 
         super(message, cause); 
         this.message = message; 
         this.cause = cause; 
     } 
  
     // Note: Use Lombok annotations for generating getters and setters to reduce boilerplate code. 
 }