package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedSupplierDTO; 
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
 import lombok.ToString; 
  
 import javax.persistence.Column; 
 import javax.persistence.Entity; 
 import javax.persistence.GeneratedValue; 
 import javax.persistence.GenerationType; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import javax.persistence.Temporal; 
 import javax.persistence.TemporalType; 
 import javax.persistence.PrePersist; 
 import javax.persistence.PreUpdate; 
 import java.time.LocalDateTime; 
  
 @Entity 
 @Table(name = "supplier") 
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @ToString 
 public class DomainSupplierEntity { 
  
     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     private Long id; 
  
     @Column(nullable = false, length = 255) 
     private String name; 
  
     @Column(nullable = false, length = 255) 
     private String contact_info; 
  
     @Column(nullable = false, length = 255) 
     private String address; 
  
     @Column(nullable = false, updatable = false) 
     @Temporal(TemporalType.TIMESTAMP) 
     private LocalDateTime created_at; 
  
     @Column(nullable = false) 
     @Temporal(TemporalType.TIMESTAMP) 
     private LocalDateTime updated_at; 
  
     @PrePersist 
     protected void onCreate() { 
         this.created_at = LocalDateTime.now(); 
         this.updated_at = LocalDateTime.now(); 
     } 
  
     @PreUpdate 
     protected void onUpdate() { 
         this.updated_at = LocalDateTime.now(); 
     } 
  
     public SharedSupplierDTO toDTO() { 
         return new SharedSupplierDTO(id, name, contact_info, address, created_at.toString(), updated_at.toString()); 
     } 
 } 
 