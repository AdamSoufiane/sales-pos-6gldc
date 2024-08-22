package ai.shreds.shared; 
  
 import java.time.LocalDateTime; 
 import java.util.Objects; 
  
 public final class SharedTimestamp { 
     private final LocalDateTime value; 
  
     public SharedTimestamp(LocalDateTime value) { 
         this.value = Objects.requireNonNull(value, "Timestamp value cannot be null"); 
     } 
  
     public LocalDateTime getValue() { 
         return value; 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         SharedTimestamp that = (SharedTimestamp) o; 
         return value.equals(that.value); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(value); 
     } 
  
     @Override 
     public String toString() { 
         return "SharedTimestamp{" + 
                 "value=" + value + 
                 '}'; 
     } 
 } 
  
 // Note: Consider using Lombok annotations for constructor, equals, hashCode, and toString methods to reduce boilerplate code.