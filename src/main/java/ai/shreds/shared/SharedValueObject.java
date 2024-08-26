package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.EqualsAndHashCode; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 /** 
  * SharedValueObject is a simple value object that encapsulates a single attribute 'value' of a generic type. 
  * This class is immutable and is used across different layers to represent a value in a standardized way. 
  */ 
 @Getter 
 @AllArgsConstructor 
 @EqualsAndHashCode 
 @ToString 
 public class SharedValueObject<T> { 
     private final T value; 
 }