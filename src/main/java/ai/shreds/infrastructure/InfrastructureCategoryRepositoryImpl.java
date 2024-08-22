package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
  
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.persistence.TypedQuery; 
 import java.util.List; 
 import java.util.Optional; 
 import java.util.UUID; 
  
 @Slf4j 
 @Repository 
 public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional(readOnly = true) 
     public Optional<DomainCategoryEntity> findById(UUID id) { 
         try { 
             DomainCategoryEntity category = entityManager.find(DomainCategoryEntity.class, id); 
             return Optional.ofNullable(category); 
         } catch (Exception e) { 
             log.error("Error finding category by ID: {}", id, e); 
             return Optional.empty(); 
         } 
     } 
  
     @Override 
     @Transactional(readOnly = true) 
     public List<DomainCategoryEntity> findAll() { 
         try { 
             TypedQuery<DomainCategoryEntity> query = entityManager.createQuery("SELECT c FROM DomainCategoryEntity c", DomainCategoryEntity.class); 
             return query.getResultList(); 
         } catch (Exception e) { 
             log.error("Error finding all categories", e); 
             return List.of(); 
         } 
     } 
 }