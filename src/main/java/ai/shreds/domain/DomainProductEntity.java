package ai.shreds.domain; 
  
 import lombok.Data; 
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.Column; 
 import javax.validation.constraints.NotNull; 
 import java.util.UUID; 
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
  
 @Data 
 @Entity 
 public class DomainProductEntity { 
     @Id 
     @NotNull 
     private UUID productId; 
  
     @Column(nullable = false) 
     @NotNull 
     private String name; 
  
     @Column(nullable = false) 
     @NotNull 
     private String description; 
  
     @Column(nullable = false) 
     @NotNull 
     private BigDecimal price; 
  
     @Column(nullable = false) 
     @NotNull 
     private UUID categoryId; 
  
     @Column(nullable = false, updatable = false) 
     @NotNull 
     private Timestamp createdAt; 
  
     @Column(nullable = false) 
     @NotNull 
     private Timestamp updatedAt; 
 }