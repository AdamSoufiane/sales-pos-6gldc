package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainProductEntity { 
     private String id; 
     private String productId; 
     private double purchasePrice; 
     private int quantity; 
 } 
 // Implementation Note: Lombok annotations are used for boilerplate code generation such as getters, setters, constructors, and builder pattern.