package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import java.io.Serializable; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "Inventory") 
 public class SharedInventoryDomainEntity implements Serializable { 
     @Id 
     private UUID productId; 
     private Integer quantity; 
     private Integer qteAlert; 
     private Timestamp lastUpdated; 
 } 
 