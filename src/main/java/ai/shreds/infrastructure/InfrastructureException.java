package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 public class InfrastructureException extends RuntimeException { 
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureException.class); 
  
     public InfrastructureException(String message) { 
         super(message); 
         logger.error(message); 
     } 
  
     public InfrastructureException(String message, Throwable cause) { 
         super(message, cause); 
         logger.error(message, cause); 
     } 
  
     public InfrastructureException(Throwable cause) { 
         super(cause); 
         logger.error(cause.getMessage(), cause); 
     } 
 }