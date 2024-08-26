package ai.shreds.adapter; 
  
 /** 
  * Custom exception class for handling exceptions specific to the 'ProductAdded' event in the adapter layer. 
  */ 
 public class AdapterProductAddedException extends RuntimeException { 
     private static final long serialVersionUID = 1L; 
  
     /** 
      * Constructs a new AdapterProductAddedException with the specified detail message. 
      *  
      * @param message the detail message 
      */ 
     public AdapterProductAddedException(String message) { 
         super(message); 
     } 
  
     /** 
      * Constructs a new AdapterProductAddedException with the specified detail message and cause. 
      *  
      * @param message the detail message 
      * @param cause the cause of the exception 
      */ 
     public AdapterProductAddedException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }