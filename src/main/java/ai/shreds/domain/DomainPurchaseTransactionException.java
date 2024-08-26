package ai.shreds.domain; 
  
 public class DomainPurchaseTransactionException extends RuntimeException { 
  
     public DomainPurchaseTransactionException() { 
         super(); 
     } 
  
     public DomainPurchaseTransactionException(String message) { 
         super(message); 
     } 
  
     public DomainPurchaseTransactionException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     public DomainPurchaseTransactionException(Throwable cause) { 
         super(cause); 
     } 
 } 
 