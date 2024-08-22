package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedInventoryDomainEntity; 
 import ai.shreds.infrastructure.InfrastructureInventoryRepositoryPort; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
  
 import java.util.UUID; 
  
 @Repository 
 @RequiredArgsConstructor 
 public class DomainInventoryRepositoryImpl implements DomainInventoryRepositoryPort { 
  
     private final InfrastructureInventoryRepositoryPort infrastructureInventoryRepositoryPort; 
  
     @Override 
     public SharedInventoryDomainEntity findByProductId(UUID productId) { 
         return infrastructureInventoryRepositoryPort.findByProductId(productId); 
     } 
  
     @Override 
     @Transactional 
     public void save(SharedInventoryDomainEntity inventory) { 
         try { 
             infrastructureInventoryRepositoryPort.save(inventory); 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to save inventory", e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void deleteByProductId(UUID productId) { 
         try { 
             infrastructureInventoryRepositoryPort.deleteByProductId(productId); 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to delete inventory by product ID", e); 
         } 
     } 
 } 
 