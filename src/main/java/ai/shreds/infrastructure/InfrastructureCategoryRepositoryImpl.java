package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.data.domain.PageRequest; 
 import org.springframework.data.domain.Pageable; 
 import org.springframework.stereotype.Repository; 
  
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.persistence.TypedQuery; 
 import java.sql.Timestamp; 
 import java.util.List; 
  
 @Repository 
 public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     public List<DomainCategoryEntity> findAll() { 
         try { 
             String jpql = "SELECT c FROM DomainCategoryEntity c"; 
             TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class); 
             return query.getResultList(); 
         } catch (Exception e) { 
             // Log the exception and rethrow or handle it as needed 
             throw new RuntimeException("Failed to retrieve all categories", e); 
         } 
     } 
  
     @Override 
     public DomainCategoryEntity findById(Long id) { 
         try { 
             return entityManager.find(DomainCategoryEntity.class, id); 
         } catch (Exception e) { 
             // Log the exception and rethrow or handle it as needed 
             throw new RuntimeException("Failed to retrieve category by ID", e); 
         } 
     } 
  
     @Override 
     public List<DomainCategoryEntity> findByNameContaining(String name) { 
         try { 
             String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.name LIKE :name"; 
             TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class); 
             query.setParameter("name", "%" + name + "%"); 
             return query.getResultList(); 
         } catch (Exception e) { 
             // Log the exception and rethrow or handle it as needed 
             throw new RuntimeException("Failed to search categories by name", e); 
         } 
     } 
  
     @Override 
     public List<DomainCategoryEntity> findByCategoryId(Long categoryId) { 
         try { 
             String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.categoryId = :categoryId"; 
             TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class); 
             query.setParameter("categoryId", categoryId); 
             return query.getResultList(); 
         } catch (Exception e) { 
             // Log the exception and rethrow or handle it as needed 
             throw new RuntimeException("Failed to search categories by parent category ID", e); 
         } 
     } 
  
     @Override 
     public List<DomainCategoryEntity> findByCreatedAtAfter(Timestamp createdAfter) { 
         try { 
             String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.createdAt > :createdAfter"; 
             TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class); 
             query.setParameter("createdAfter", createdAfter); 
             return query.getResultList(); 
         } catch (Exception e) { 
             // Log the exception and rethrow or handle it as needed 
             throw new RuntimeException("Failed to search categories by creation date", e); 
         } 
     } 
  
     @Override 
     public List<DomainCategoryEntity> findByUpdatedAtAfter(Timestamp updatedAfter) { 
         try { 
             String jpql = "SELECT c FROM DomainCategoryEntity c WHERE c.updatedAt > :updatedAfter"; 
             TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class); 
             query.setParameter("updatedAfter", updatedAfter); 
             return query.getResultList(); 
         } catch (Exception e) { 
             // Log the exception and rethrow or handle it as needed 
             throw new RuntimeException("Failed to search categories by update date", e); 
         } 
     } 
  
     public List<DomainCategoryEntity> findAll(Pageable pageable) { 
         try { 
             String jpql = "SELECT c FROM DomainCategoryEntity c"; 
             TypedQuery<DomainCategoryEntity> query = entityManager.createQuery(jpql, DomainCategoryEntity.class); 
             query.setFirstResult((int) pageable.getOffset()); 
             query.setMaxResults(pageable.getPageSize()); 
             return query.getResultList(); 
         } catch (Exception e) { 
             // Log the exception and rethrow or handle it as needed 
             throw new RuntimeException("Failed to retrieve paginated categories", e); 
         } 
     } 
 }