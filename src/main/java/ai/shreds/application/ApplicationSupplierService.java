package ai.shreds.application; 
  
 import ai.shreds.domain.DomainSupplierEntity; 
 import ai.shreds.domain.DomainSupplierRepositoryPort; 
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.SharedSupplierDTO; 
 import ai.shreds.adapter.AdapterSupplierMapper; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
 import lombok.RequiredArgsConstructor; 
 import java.util.List; 
 import java.util.stream.Collectors; 
  
 @Service 
 @RequiredArgsConstructor 
 @Transactional 
 public class ApplicationSupplierService implements ApplicationGetAllSuppliersInputPort, ApplicationGetSupplierByIdInputPort { 
  
     private final DomainSupplierRepositoryPort domainSupplierRepositoryPort; 
     private final AdapterSupplierMapper adapterSupplierMapper; 
  
     @Override 
     public List<SharedSupplierDTO> getAllSuppliers(SharedRequestParams params) { 
         List<DomainSupplierEntity> suppliers = domainSupplierRepositoryPort.findAll(params); 
         return suppliers.stream() 
                 .map(adapterSupplierMapper::toDTO) 
                 .collect(Collectors.toList()); 
     } 
  
     @Override 
     public SharedSupplierDTO getSupplierById(Long id) { 
         DomainSupplierEntity supplier = domainSupplierRepositoryPort.findById(id); 
         if (supplier == null) { 
             throw new SupplierNotFoundException("Supplier not found."); 
         } 
         return adapterSupplierMapper.toDTO(supplier); 
     } 
 } 
  
 // Global Exception Handler 
 package ai.shreds.infrastructure; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
  
 @ControllerAdvice 
 public class GlobalExceptionHandler { 
  
     @ExceptionHandler(SupplierNotFoundException.class) 
     public ResponseEntity<String> handleSupplierNotFoundException(SupplierNotFoundException ex) { 
         return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleGeneralException(Exception ex) { 
         return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
  
 // SupplierNotFoundException 
 package ai.shreds.domain; 
  
 public class SupplierNotFoundException extends RuntimeException { 
     public SupplierNotFoundException(String message) { 
         super(message); 
     } 
 }