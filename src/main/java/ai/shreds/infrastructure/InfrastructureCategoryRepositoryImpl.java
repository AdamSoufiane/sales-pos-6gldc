package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import ai.shreds.domain.DomainCategoryException; 
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedTimestamp; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.GeneratedValue; 
 import javax.persistence.GenerationType; 
 import javax.persistence.Column; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
 import java.util.Optional; 
 import java.util.UUID; 
  
 @Repository 
 public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainCategoryEntity category) { 
         try { 
             if (category.getId() == null) { 
                 entityManager.persist(category); 
             } else { 
                 entityManager.merge(category); 
             } 
         } catch (Exception e) { 
             throw new DomainCategoryException("Error saving category", e); 
         } 
     } 
  
     @Override 
     public DomainCategoryEntity findById(UUID id) { 
         try { 
             return Optional.ofNullable(entityManager.find(DomainCategoryEntity.class, id)) 
                     .orElseThrow(() -> new DomainCategoryException("Category not found")); 
         } catch (Exception e) { 
             throw new DomainCategoryException("Error finding category", e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void deleteById(UUID id) { 
         try { 
             DomainCategoryEntity category = findById(id); 
             entityManager.remove(category); 
         } catch (Exception e) { 
             throw new DomainCategoryException("Error deleting category", e); 
         } 
     } 
 } 
  
 @Entity 
 @Data 
 @NoArgsConstructor 
 class DomainCategoryEntity { 
     @Id 
     @GeneratedValue(strategy = GenerationType.AUTO) 
     private UUID id; 
  
     @Column(nullable = false) 
     private String name; 
  
     private String description; 
  
     private UUID category_id; 
  
     @Column(nullable = false, updatable = false) 
     private SharedTimestamp created_at; 
  
     @Column(nullable = false) 
     private SharedTimestamp updated_at; 
 } 
 