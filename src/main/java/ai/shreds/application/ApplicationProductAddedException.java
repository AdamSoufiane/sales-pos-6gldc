package ai.shreds.application; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Exception class for handling exceptions that occur during the processing of 'ProductAdded' events. 
  */ 
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class ApplicationProductAddedException extends RuntimeException { 
     private static final Logger logger = LoggerFactory.getLogger(ApplicationProductAddedException.class); 
     private String message; 
     private Throwable cause; 
  
     /** 
      * Constructor with message only. 
      * @param message the exception message 
      */ 
     public ApplicationProductAddedException(String message) { 
         super(message); 
         this.message = message; 
     } 
  
     /** 
      * Constructor with message and cause. 
      * @param message the exception message 
      * @param cause the underlying cause of the exception 
      */ 
     public ApplicationProductAddedException(String message, Throwable cause) { 
         super(message, cause); 
         this.message = message; 
         this.cause = cause; 
     } 
  
     /** 
      * Handles the exception by logging the error details. 
      * @param cause the underlying cause of the exception 
      */ 
     public void handleException(Throwable cause) { 
         // Log the exception details 
         logger.error("Exception occurred: {}", cause.getMessage(), cause); 
     } 
 }