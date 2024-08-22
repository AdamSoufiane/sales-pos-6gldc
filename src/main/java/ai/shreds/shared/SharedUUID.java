package ai.shreds.shared; 
  
 import lombok.Getter; 
 import lombok.ToString; 
 import java.util.UUID; 
 import java.util.Objects; 
  
 /** 
  * SharedUUID is a value object representing a UUID in the system. 
  * It encapsulates the UUID value and ensures that it is valid. 
  */ 
 @Getter 
 @ToString 
 public class SharedUUID { 
     private final String value; 
  
     public SharedUUID(String value) { 
         if (!isValidUUID(value)) { 
             throw new IllegalArgumentException("Invalid UUID format"); 
         } 
         this.value = value; 
     } 
  
     private boolean isValidUUID(String value) { 
         try { 
             UUID.fromString(value); 
             return true; 
         } catch (IllegalArgumentException e) { 
             return false; 
         } 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         SharedUUID that = (SharedUUID) o; 
         return Objects.equals(value, that.value); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(value); 
     } 
 } 
 