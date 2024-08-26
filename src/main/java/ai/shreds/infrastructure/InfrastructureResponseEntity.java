package ai.shreds.infrastructure; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
  
 import java.time.LocalDateTime; 
  
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class InfrastructureResponseEntity<T> { 
     private T data; 
     private String status; 
     private String message; 
     private LocalDateTime timestamp; 
  
     public InfrastructureResponseEntity(T data, String status, String message) { 
         this.data = data; 
         this.status = status; 
         this.message = message; 
         this.timestamp = LocalDateTime.now(); 
     } 
 } 
 // Note: Ensure Lombok annotations (@Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor) are used for generating boilerplate code.