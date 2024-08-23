package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCreateSupplierRequest; 
 import ai.shreds.adapter.AdapterUpdateSupplierRequest; 
 import ai.shreds.adapter.AdapterDeleteSupplierResponse; 
 import ai.shreds.shared.SharedSupplierDTO; 
 import ai.shreds.domain.DomainSupplierEntity; 
 import ai.shreds.domain.DomainSupplierService; 
 import ai.shreds.domain.DomainSupplierEntityMapper; 
 import ai.shreds.domain.DomainSupplierRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Service 
 @RequiredArgsConstructor 
 @Slf4j 
 public class ApplicationSupplierService implements ApplicationCreateSupplierInputPort, ApplicationUpdateSupplierInputPort, ApplicationDeleteSupplierInputPort { 
  
     private final DomainSupplierRepositoryPort domainSupplierRepositoryPort; 
     private final DomainSupplierEntityMapper domainSupplierEntityMapper; 
  
     @Override 
     public SharedSupplierDTO createSupplier(AdapterCreateSupplierRequest request) { 
         // Validate input data 
         validateCreateRequest(request); 
         // Map request to domain entity 
         DomainSupplierEntity supplierEntity = domainSupplierEntityMapper.toEntity(request); 
         // Save supplier entity 
         domainSupplierRepositoryPort.save(supplierEntity); 
         // Map saved entity to DTO 
         return domainSupplierEntityMapper.toDTO(supplierEntity); 
     } 
  
     @Override 
     public SharedSupplierDTO updateSupplier(Long id, AdapterUpdateSupplierRequest request) { 
         // Validate input data 
         validateUpdateRequest(request); 
         if (!domainSupplierRepositoryPort.existsById(id)) { 
             throw new SupplierNotFoundException("Supplier not found"); 
         } 
         // Map request to domain entity 
         DomainSupplierEntity supplierEntity = domainSupplierEntityMapper.toEntity(request); 
         supplierEntity.setId(id); 
         // Update supplier entity 
         domainSupplierRepositoryPort.save(supplierEntity); 
         // Map updated entity to DTO 
         return domainSupplierEntityMapper.toDTO(supplierEntity); 
     } 
  
     @Override 
     public AdapterDeleteSupplierResponse deleteSupplier(Long id) { 
         // Check if supplier exists 
         if (!domainSupplierRepositoryPort.existsById(id)) { 
             throw new SupplierNotFoundException("Supplier not found"); 
         } 
         // Delete supplier entity 
         domainSupplierRepositoryPort.deleteById(id); 
         // Return response 
         AdapterDeleteSupplierResponse response = new AdapterDeleteSupplierResponse(); 
         response.setStatus_code(200); 
         response.setData(null); 
         response.setError(null); 
         return response; 
     } 
  
     private void validateCreateRequest(AdapterCreateSupplierRequest request) { 
         // Detailed validation logic for create request 
         if (request.getName() == null || request.getName().isEmpty()) { 
             throw new IllegalArgumentException("Supplier name is required"); 
         } 
         if (request.getContact_info() == null || request.getContact_info().isEmpty()) { 
             throw new IllegalArgumentException("Contact information is required"); 
         } 
         if (request.getAddress() == null || request.getAddress().isEmpty()) { 
             throw new IllegalArgumentException("Address is required"); 
         } 
     } 
  
     private void validateUpdateRequest(AdapterUpdateSupplierRequest request) { 
         // Detailed validation logic for update request 
         if (request.getName() == null || request.getName().isEmpty()) { 
             throw new IllegalArgumentException("Supplier name is required"); 
         } 
         if (request.getContact_info() == null || request.getContact_info().isEmpty()) { 
             throw new IllegalArgumentException("Contact information is required"); 
         } 
         if (request.getAddress() == null || request.getAddress().isEmpty()) { 
             throw new IllegalArgumentException("Address is required"); 
         } 
     } 
 }