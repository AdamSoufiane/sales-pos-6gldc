package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainSupplierEntity; 
 import ai.shreds.domain.DomainSupplierRepositoryPort; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
  
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
 import java.util.Optional; 
  
 @Repository 
 @Slf4j 
 public class InfrastructureSupplierRepositoryImpl implements DomainSupplierRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainSupplierEntity supplier) { 
         try { 
             entityManager.persist(supplier); 
         } catch (Exception e) { 
             handleException(e); 
         } 
     } 
  
     @Override 
     public DomainSupplierEntity findById(Long id) { 
         try { 
             return Optional.ofNullable(entityManager.find(DomainSupplierEntity.class, id)) 
                     .orElseThrow(() -> new SupplierNotFoundException("Supplier not found")); 
         } catch (Exception e) { 
             handleException(e); 
             return null; 
         } 
     } 
  
     @Override 
     @Transactional 
     public void deleteById(Long id) { 
         try { 
             DomainSupplierEntity supplier = findById(id); 
             if (supplier != null) { 
                 entityManager.remove(supplier); 
             } 
         } catch (Exception e) { 
             handleException(e); 
         } 
     } 
  
     @Override 
     public boolean existsById(Long id) { 
         try { 
             return entityManager.find(DomainSupplierEntity.class, id) != null; 
         } catch (Exception e) { 
             handleException(e); 
             return false; 
         } 
     } 
  
     private void handleException(Exception e) { 
         log.error("Database operation failed", e); 
         throw new DatabaseOperationException("Database operation failed", e); 
     } 
 } 
  
 class SupplierNotFoundException extends RuntimeException { 
     public SupplierNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 class DatabaseOperationException extends RuntimeException { 
     public DatabaseOperationException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 