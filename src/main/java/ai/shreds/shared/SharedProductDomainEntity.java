package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.persistence.Column; 
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 /** 
  * Represents a product in the system. 
  * This entity will be used across different layers of the application to maintain consistency and integrity of product data. 
  *  
  * Implementation Notes: 
  * - Uses Lombok annotations (@Data, @Builder, @NoArgsConstructor, @AllArgsConstructor) to reduce boilerplate code. 
  * - Mapped to the 'Product' table in the database using JPA annotations. 
  */ 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "Product") 
 public class SharedProductDomainEntity { 
  
     @Id 
     @Column(name = "productId", nullable = false, unique = true) 
     private UUID productId; 
  
     @Column(name = "name", nullable = false) 
     private String name; 
  
     @Column(name = "description") 
     private String description; 
  
     @Column(name = "price", nullable = false) 
     private BigDecimal price; 
  
     @Column(name = "categoryId", nullable = false) 
     private UUID categoryId; 
  
     @Column(name = "createdAt", nullable = false) 
     private Timestamp createdAt; 
  
     @Column(name = "updatedAt", nullable = false) 
     private Timestamp updatedAt; 
 } 
 