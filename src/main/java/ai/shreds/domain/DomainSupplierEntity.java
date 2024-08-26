package ai.shreds.domain; 
  
 import lombok.Data; 
 import org.hibernate.annotations.CreationTimestamp; 
 import org.hibernate.annotations.UpdateTimestamp; 
 import javax.persistence.*; 
 import java.time.LocalDateTime; 
  
 @Data 
 @Entity 
 @Table(name = "Supplier") 
 public class DomainSupplierEntity { 
     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     private Integer id; 
  
     @Column(name = "name", nullable = false, length = 255) 
     private String name; 
  
     @Column(name = "contact_info", nullable = false, length = 255) 
     private String contact_info; 
  
     @Column(name = "address", nullable = false, length = 255) 
     private String address; 
  
     @CreationTimestamp 
     @Column(name = "created_at", nullable = false, updatable = false) 
     private LocalDateTime created_at; 
  
     @UpdateTimestamp 
     @Column(name = "updated_at", nullable = false) 
     private LocalDateTime updated_at; 
 }