package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
  
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedRequestParams { 
     protected String param1; 
     protected String param2; 
 } 
 // Note: Include Lombok annotations for getters and setters