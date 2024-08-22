package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.Builder; 
  
 import javax.persistence.*; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
 import java.time.LocalDateTime; 
 import java.util.UUID; 
  
 @Entity 
 @Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "category_id"})}) 
 @Data 
 @Builder 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class DomainCategoryEntity { 
  
     @Id 
     @GeneratedValue(strategy = GenerationType.AUTO) 
     private UUID id; 
  
     @Column(name = "name", nullable = false) 
     @NotBlank(message = "Category name is mandatory") 
     private String name; 
  
     @Column(name = "description") 
     private String description; 
  
     @Column(name = "category_id") 
     private UUID categoryId; 
  
     @Column(name = "created_at", nullable = false, updatable = false) 
     private LocalDateTime createdAt; 
  
     @Column(name = "updated_at", nullable = false) 
     private LocalDateTime updatedAt; 
  
     @PrePersist 
     protected void onCreate() { 
         this.createdAt = LocalDateTime.now(); 
         this.updatedAt = LocalDateTime.now(); 
     } 
  
     @PreUpdate 
     protected void onUpdate() { 
         this.updatedAt = LocalDateTime.now(); 
     } 
 }